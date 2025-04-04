package com.example.thi_module_3.repository;

import com.example.thi_module_3.model.BorrowCard;
import com.example.thi_module_3.ulit.BaseRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowCardRepository implements IBookRepositoryImpl{
    @Override
    public void save(BorrowCard borrowCard) {
        Connection conn = BaseRepository.getConnection();
        try {
            BookRepository bookRepository = new BookRepository();

            String sql = "INSERT INTO BorrowCards (borrowCode, bookId, studentId, status, borrowDate, returnDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, borrowCard.getBorrowCode());
            preparedStatement.setInt(2, borrowCard.getBookId());
            preparedStatement.setInt(3, borrowCard.getStudentId());
            preparedStatement.setBoolean(4, borrowCard.isStatus());
            preparedStatement.setDate(5, borrowCard.getBorrowDate());
            preparedStatement.setDate(6, borrowCard.getReturnDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<BorrowCard> findAllBorrowedBooks() {
        List<BorrowCard> borrowCards = new ArrayList<>();
        Connection connection = BaseRepository.getConnection();
        try {
            String sql = "SELECT bc.*, b.name AS bookName, s.name AS studentName " +
                    "FROM BorrowCards bc " +
                    "JOIN Books b ON bc.bookId = b.id " +
                    "JOIN Students s ON bc.studentId = s.id " +
                    "WHERE bc.status = true";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBoolean(1, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BorrowCard borrowCard = new BorrowCard();
                borrowCard.setId(resultSet.getInt("id"));
                borrowCard.setBorrowCode(resultSet.getString("borrowCode"));
                borrowCard.setBookId(resultSet.getInt("bookId"));
                borrowCard.setStudentId(resultSet.getInt("studentId"));
                borrowCard.setStatus(resultSet.getBoolean("status"));
                borrowCard.setBorrowDate(resultSet.getDate("borrowDate"));
                borrowCard.setReturnDate(resultSet.getDate("returnDate"));
                borrowCards.add(borrowCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return borrowCards;
    }

    @Override
    public BorrowCard findById(int id) {
        BorrowCard borrowCard = null;
        Connection connection = BaseRepository.getConnection();
        try {
            String sql = "SELECT * FROM BorrowCards WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                borrowCard = new BorrowCard();
                borrowCard.setId(resultSet.getInt("id"));
                borrowCard.setBorrowCode(resultSet.getString("borrowCode"));
                borrowCard.setBookId(resultSet.getInt("bookId"));
                borrowCard.setStudentId(resultSet.getInt("studentId"));
                borrowCard.setStatus(resultSet.getBoolean("status"));
                borrowCard.setBorrowDate(resultSet.getDate("borrowDate"));
                borrowCard.setReturnDate(resultSet.getDate("returnDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hoặc ném ngoại lệ: throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return borrowCard;
    }

    @Override
    public boolean update(BorrowCard borrowCard) {
        boolean rowUpdated = false;
        Connection connection = BaseRepository.getConnection();
        try {
            String sql = "UPDATE BorrowCards SET borrowCode = ?, bookId = ?, studentId = ?, status = ?, borrowDate = ?, returnDate = ? " +
                    "WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, borrowCard.getBorrowCode());
            preparedStatement.setInt(2, borrowCard.getBookId());
            preparedStatement.setInt(3, borrowCard.getStudentId());
            preparedStatement.setBoolean(4, borrowCard.isStatus());
            preparedStatement.setDate(5, borrowCard.getBorrowDate());
            preparedStatement.setDate(6, borrowCard.getReturnDate());
            preparedStatement.setInt(7, borrowCard.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Hoặc ném ngoại lệ: throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowUpdated;
    }
}

