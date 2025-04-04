package com.example.thi_module_3.service;

import com.example.thi_module_3.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id);
}
