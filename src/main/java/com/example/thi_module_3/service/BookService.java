package com.example.thi_module_3.service;

import com.example.thi_module_3.model.Book;
import com.example.thi_module_3.repository.BookRepository;
import com.example.thi_module_3.repository.IBookRepository;

import java.util.List;

public class BookService implements IBookService {
    private static BookRepository bookRepository = new BookRepository();

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public void decreaseQuantity(int bookId) {
        bookRepository.decreaseQuantity(bookId);
    }

    @Override
    public void increaseQuantity(int bookId) {
        bookRepository.increaseQuantity(bookId);
    }
}
