package com.example.thi_module_3.repository;

import com.example.thi_module_3.model.BorrowCard;
import java.util.List;

public interface IBookRepositoryImpl {
    void save(BorrowCard borrowCard);
    List<BorrowCard> findAllBorrowedBooks();
    BorrowCard findById(int id);
    boolean update(BorrowCard borrowCard);
}
