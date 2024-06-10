package com.ilu.enigpus.models;

import java.math.BigDecimal;

import com.ilu.enigpus.utils.Helpers;

public class Novel extends Book {
    private String publisher, author;
    private static int counter = 0;

    public Novel(String title, String publisher, String author, BigDecimal year) {
        // super.setCode(code);
        // super.setTitle(title);
        // super.setCode(Helpers.generateCodeBook(year, "novel"));
        if (getCode() == null) {
            this.setCode(Helpers.generateCodeBook(year, "novel"));
        }
        this.setTitle(title);
        this.setYear(year);
        this.publisher = publisher;
        this.author = author;
    }

    public Novel(String title, String publisher, String author, BigDecimal year, boolean hasCode) {
        // super.setCode(code);
        // super.setTitle(title);
        // super.setCode(Helpers.generateCodeBook(year, "novel"));
        this.setTitle(title);
        this.setYear(year);
        this.publisher = publisher;
        this.author = author;
    }

    @Override
    public String getCode() {
        return super.code;
    }

    @Override
    public String getTitle() {
        return super.title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getYear() {
        return super.year;
    }

    public static int incrementCounter() {
        return ++counter;
    }

    public static void setCounter(int counter) {
        Novel.counter = counter;
    }

    @Override
    public String toString() {
        return "Novel => " +
                "Kode: " + getCode() + "\n" +
                "Judul: " + getTitle() + "\n" +
                "Penerbit: " + getPublisher() + "\n" +
                "Penulis: " + getAuthor() + "\n" +
                "Tahun Terbit: " + getYear();

    }

}
