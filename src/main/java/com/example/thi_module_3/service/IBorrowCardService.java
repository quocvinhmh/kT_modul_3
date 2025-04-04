package com.example.thi_module_3.service;

import com.example.thi_module_3.model.BorrowCard;

import java.util.List;

public interface IBorrowCardService {
    void save(BorrowCard borrowCard);
    void returnBook(int borrowCardId);
    List<BorrowCard> getAllBorrowedBooks();
}
