package com.library.repository;

public class BookRepository {

    public String getBookById(int id) {
        return "Book[id=" + id + ", title=Effective Java]";
    }

    public void saveBook(String title) {
        System.out.println("BookRepository: Saved book -> " + title);
    }
}