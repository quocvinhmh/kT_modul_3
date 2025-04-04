package com.example.thi_module_3.repository;

import com.example.thi_module_3.model.Book;
import com.example.thi_module_3.ulit.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection connection = BaseRepository.getConnection();
        try {
            // Sử dụng tên bảng chính xác "Books"
            String SELECT_ALL = "SELECT * FROM books";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                // Giả sử lớp Book có constructor: Book(int id, String code, String name, String author, String description, int quantity)
                Book book = new Book(id, code, name, author, description, quantity);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        Book book = null;
        Connection connection = BaseRepository.getConnection();
        try {
            String SELECT_BY_ID = "SELECT * FROM Books WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                book = new Book(id, code, name, author, description, quantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return book;
    }
    @Override
    public void decreaseQuantity(int bookId) {
        Connection connection = BaseRepository.getConnection();
        try {
            String sql = "UPDATE Books SET quantity = quantity - 1 WHERE id = ? AND quantity > 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try { connection.close(); } catch (SQLException e) { throw new RuntimeException(e); }
        }
    }

    @Override
    public void increaseQuantity(int bookId) {
        Connection connection = BaseRepository.getConnection();
        try {
            String sql = "UPDATE Books SET quantity = quantity + 1 WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try { connection.close(); } catch (SQLException e) { throw new RuntimeException(e); }
        }
    }
}
