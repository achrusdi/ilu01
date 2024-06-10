package com.ilu.enigpus.services;

import java.util.ArrayList;

import com.ilu.enigpus.models.Book;

public interface InventoryService {
    void addBook(Book book);
    ArrayList<Book> searchBookByTitle(String title);
    ArrayList<Book> searchBookByCode(String code);
    boolean deleteBookByCode(String code);
    ArrayList<Book> getAllBook();
}
