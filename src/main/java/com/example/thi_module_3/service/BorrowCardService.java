package com.example.thi_module_3.service;

import com.example.thi_module_3.model.BorrowCard;
import com.example.thi_module_3.repository.BorrowCardRepository;

import java.util.List;

public class BorrowCardService implements IBorrowCardService{
    private BorrowCardRepository borrowCardRepository = new BorrowCardRepository();

    @Override
    public void save(BorrowCard borrowCard) {
        borrowCardRepository.save(borrowCard);
    }

    @Override
    public void returnBook(int borrowCardId) {
        BorrowCard card = borrowCardRepository.findById(borrowCardId);
        if (card != null && card.isStatus()) {
            card.setStatus(false);
            borrowCardRepository.update(card);
        }
    }

    @Override
    public List<BorrowCard> getAllBorrowedBooks() {
        // Lấy danh sách các thẻ mượn có trạng thái đang mượn (status = true)
        return borrowCardRepository.findAllBorrowedBooks();
    }
}
