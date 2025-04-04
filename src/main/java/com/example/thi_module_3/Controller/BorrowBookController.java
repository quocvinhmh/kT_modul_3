package com.example.thi_module_3.Controller;

import com.example.thi_module_3.model.Book;
import com.example.thi_module_3.model.BorrowCard;
import com.example.thi_module_3.service.BorrowCardService;
import com.example.thi_module_3.service.IBookService;
import com.example.thi_module_3.service.BookService;
import com.example.thi_module_3.service.IBorrowCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "BookController", urlPatterns = {"/books"})
public class BorrowBookController extends HttpServlet {
    private IBookService bookService = new BookService();
    private IBorrowCardService borrowCardService = new BorrowCardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listBooks(request, response);
                break;
            case "borrow":
                showBorrowForm(request, response);
                break;
            case "return":
                processReturn(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "processBorrow":
                processBorrow(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/views/bookList.jsp").forward(request, response);
    }

    private void showBorrowForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        int bookId = Integer.parseInt(bookIdStr);
        Book book = bookService.getBookById(bookId);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/views/borrowBook.jsp").forward(request, response);
    }

    private void processBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        int bookId = Integer.parseInt(bookIdStr);
        String borrowCode = request.getParameter("borrowCode");
        String borrowDateStr = request.getParameter("borrowDate");
        String returnDateStr = request.getParameter("returnDate");  // định dạng dd/MM/yyyy
        String studentIdStr = request.getParameter("studentId");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilBorrowDate = sdf.parse(borrowDateStr);
            java.util.Date utilReturnDate = sdf.parse(returnDateStr);

            // Kiểm tra ngày trả không được trước ngày mượn
            if (utilReturnDate.before(utilBorrowDate)) {
                request.setAttribute("errorMessage", "Ngày trả không được trước ngày mượn.");
                showBorrowForm(request, response);
                return;
            }

            java.sql.Date borrowDate = new java.sql.Date(utilBorrowDate.getTime());
            java.sql.Date returnDate = new java.sql.Date(utilReturnDate.getTime());
            int studentId = Integer.parseInt(studentIdStr);

            BorrowCard borrowCard = new BorrowCard();
            borrowCard.setBorrowCode(borrowCode);
            borrowCard.setBookId(bookId);
            borrowCard.setStudentId(studentId);
            borrowCard.setStatus(true);
            borrowCard.setBorrowDate(borrowDate);
            borrowCard.setReturnDate(returnDate);

            borrowCardService.save(borrowCard);


            bookService.decreaseQuantity(bookId);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Định dạng ngày không hợp lệ.");
            showBorrowForm(request, response);
            return;
        }


        response.sendRedirect(request.getContextPath() + "/books?action=list");
    }


    private void processReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String borrowCardIdStr = request.getParameter("borrowCardId");
        String bookIdStr = request.getParameter("bookId");
        System.out.println("borrowCardId: " + borrowCardIdStr);
        System.out.println("bookId: " + bookIdStr);

        if(borrowCardIdStr == null || bookIdStr == null || borrowCardIdStr.trim().isEmpty() || bookIdStr.trim().isEmpty()){
            request.setAttribute("errorMessage", "Thiếu tham số để thực hiện trả sách.");
            listBorrowedBooks(request, response);
            return;
        }

        int borrowCardId = Integer.parseInt(borrowCardIdStr);
        int bookId = Integer.parseInt(bookIdStr);

        borrowCardService.returnBook(borrowCardId);
        bookService.increaseQuantity(bookId);

        response.sendRedirect(request.getContextPath() + "/books?action=statistics");
    }


    private void listBorrowedBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BorrowCardService borrowCardService = new BorrowCardService();
        List<BorrowCard> borrowCards = borrowCardService.getAllBorrowedBooks();
        request.setAttribute("borrowCards", borrowCards);
        request.getRequestDispatcher("/views/borrowedBooks.jsp").forward(request, response);

    }


}
