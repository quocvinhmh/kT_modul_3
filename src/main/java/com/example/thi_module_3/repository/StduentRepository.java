package com.example.thi_module_3.repository;

import com.example.thi_module_3.model.Student;
import com.example.thi_module_3.ulit.BaseRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StduentRepository implements IStudentRepository {

    @Override
    public List<Student> findAll() {
        Connection conn = BaseRepository.getConnection();
        List<Student> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Students";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                list.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * \n" +
                "FROM BorrowCards bc \n" +
                "JOIN Books b ON bc.bookId = b.id\n" +
                "JOIN Students s ON bc.studentId = s.id\n" +
                "WHERE bc.status = true\n" +
                "  AND b.name LIKE ? \n" +
                "  AND s.name LIKE ?\n";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    return student;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
