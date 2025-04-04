package com.example.thi_module_3.repository;

import com.example.thi_module_3.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    Student findById(int id);
}
