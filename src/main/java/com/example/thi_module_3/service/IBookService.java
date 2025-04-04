package com.example.thi_module_3.service;

import com.example.thi_module_3.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void decreaseQuantity(int bookId);
    void increaseQuantity(int bookId);
}
