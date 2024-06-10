package com.ilu.enigpus.impls;

import java.util.ArrayList;

import com.ilu.enigpus.models.Book;
import com.ilu.enigpus.services.InventoryService;

public class InventoryServiceImpl implements InventoryService {

    private ArrayList<Book> books = new ArrayList<Book>();

    // public InventoryServiceImpl() {
    // books = new ArrayList<Book>();
    // }

    @Override
    public void addBook(Book book) {
        // TODO Auto-generated method stub
        books.add(book);
    }

    @Override
    public ArrayList<Book> searchBookByTitle(String title) {
        ArrayList<Book> result = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Book> searchBookByCode(String code) {
        ArrayList<Book> result = new ArrayList<Book>();

        for (Book book : books) {
            if (book.getCode().toLowerCase().equals(code.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public boolean deleteBookByCode(String code) {
        for (Book book : books) {
            if (book.getCode().toLowerCase().equals(code.toLowerCase())) {
                books.remove(book);
                return true;
            }
        }

        return false;
    }

    @Override
    public ArrayList<Book> getAllBook() {
        return books;
    }

    public int getBooksLength() {
        return books.size();
    }
}
