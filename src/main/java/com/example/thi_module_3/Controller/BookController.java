package com.example.thi_module_3.Controller;

import com.example.thi_module_3.model.Book;
import com.example.thi_module_3.model.BorrowCard;
import com.example.thi_module_3.model.Student;
import com.example.thi_module_3.service.IBookService;
import com.example.thi_module_3.service.IBorrowCardService;
import com.example.thi_module_3.service.IStudentService;
import com.example.thi_module_3.service.BookService;
import com.example.thi_module_3.service.BorrowCardService;
import com.example.thi_module_3.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "BookController", urlPatterns = {"/books"})
public class BookController extends HttpServlet {

    private IBookService bookService = new BookService();
    private IStudentService studentService = new StudentService();
    private IBorrowCardService borrowCardService = new BorrowCardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số action; mặc định hiển thị danh sách sách
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
            case "statistics":
                listBorrowedBooks(request, response);
                break;
            case "return":
                processReturn(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    // Xử lý các request POST, ví dụ khi submit form mượn sách
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

    // Hiển thị danh sách sách (trang chính)
    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/views/bookList.jsp").forward(request, response);
    }

    // Hiển thị form mượn sách
    private void showBorrowForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        int bookId = Integer.parseInt(bookIdStr);
        Book book = bookService.getBookById(bookId);
        if(book == null || book.getQuantity() <= 0) {
            request.setAttribute("errorMessage", "Số lượng sách đã hết hoặc sách không tồn tại.");
            listBooks(request, response);
            return;
        }
        List<Student> students = studentService.getAllStudents();
        request.setAttribute("book", book);
        request.setAttribute("students", students);
        request.getRequestDispatcher("/views/borrowBook.jsp").forward(request, response);
    }

    // Xử lý form mượn sách
    private void processBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String borrowCode = request.getParameter("borrowCode");
        if (!borrowCode.matches("MS-\\d{4}")) {
            request.setAttribute("errorMessage", "Mã mượn sách không hợp lệ. Định dạng phải là MS-XXXX");
            showBorrowForm(request, response);
            return;
        }
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String borrowDateStr = request.getParameter("borrowDate");  // dd/MM/yyyy
        String returnDateStr = request.getParameter("returnDate");  // dd/MM/yyyy
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilBorrowDate = sdf.parse(borrowDateStr);
            java.util.Date utilReturnDate = sdf.parse(returnDateStr);
            if (utilReturnDate.before(utilBorrowDate)) {
                request.setAttribute("errorMessage", "Ngày trả không được trước ngày mượn.");
                showBorrowForm(request, response);
                return;
            }
            java.sql.Date borrowDate = new java.sql.Date(utilBorrowDate.getTime());
            java.sql.Date returnDate = new java.sql.Date(utilReturnDate.getTime());

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

    // Hiển thị danh sách thống kê các thẻ mượn sách đang "đang mượn"
    private void listBorrowedBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách các thẻ mượn có trạng thái đang mượn
        List<BorrowCard> borrowCards = borrowCardService.getAllBorrowedBooks();
        request.setAttribute("borrowCards", borrowCards);
        request.getRequestDispatcher("/views/borrowedBooks.jsp").forward(request, response);
    }

    // Xử lý trả sách
    private void processReturn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int borrowCardId = Integer.parseInt(request.getParameter("borrowCardId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        borrowCardService.returnBook(borrowCardId);
        bookService.increaseQuantity(bookId);
        response.sendRedirect(request.getContextPath() + "/books?action=statistics");
    }
}