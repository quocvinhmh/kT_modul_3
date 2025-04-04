package com.example.thi_module_3.model;

public class Book {
    private int id;
    private String code;
    private String name;
    private String author;
    private String description;
    private int quantity;

    public Book() {
    }

    public Book(int id, String code, String name, String author, String description, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
