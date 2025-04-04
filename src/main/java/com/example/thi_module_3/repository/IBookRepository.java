package com.example.thi_module_3.repository;

import com.example.thi_module_3.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookRepository {
    List<Book> getAllBooks();
    Book getBookById(int id) throws SQLException;
    void decreaseQuantity(int bookId);
    void increaseQuantity(int bookId);
}
