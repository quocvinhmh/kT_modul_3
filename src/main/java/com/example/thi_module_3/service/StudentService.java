package com.example.thi_module_3.service;

import com.example.thi_module_3.model.Student;
import com.example.thi_module_3.repository.IStudentRepository;
import com.example.thi_module_3.repository.StduentRepository;

import java.util.List;

public class StudentService implements IStudentService {
private static StduentRepository stduentRepository = new StduentRepository()   ;
    @Override
    public List<Student> getAllStudents() {
        return stduentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return stduentRepository.findById(id);
    }
}
