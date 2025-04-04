package com.example.thi_module_3.model;

public class Student {
    private int id;
    private String studentCode; // Mã học sinh
    private String name;        // Họ tên
    private String className;   // Lớp

    public Student() {}

    public Student(int id, String studentCode, String name, String className) {
        this.id = id;
        this.studentCode = studentCode;
        this.name = name;
        this.className = className;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStudentCode() {
        return studentCode;
    }
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
}
