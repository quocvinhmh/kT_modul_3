package com.example.thi_module_3.model;

import java.sql.Date;

public class BorrowCard {
    private int id;
    private String borrowCode;
    private int bookId;
    private int studentId;
    private boolean status;
    private Date borrowDate;
    private Date returnDate;
    private String bookName;
    private String studentName;

    public BorrowCard(int id, String borrowCode, int bookId, int studentId, boolean status, Date borrowDate, Date returnDate, String bookName, String studentName) {
        this.id = id;
        this.borrowCode = borrowCode;
        this.bookId = bookId;
        this.studentId = studentId;
        this.status = status;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.bookName = bookName;
        this.studentName = studentName;
    }

    public BorrowCard() {

    }

    // Getters và setters
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStatus(boolean b) {
        this.status = b;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowCode() {
        return borrowCode;
    }

    public int getBookId() {
        return bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
