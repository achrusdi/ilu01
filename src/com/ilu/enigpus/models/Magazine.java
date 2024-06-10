package com.ilu.enigpus.models;

import java.math.BigDecimal;

import com.ilu.enigpus.utils.Helpers;

public class Magazine extends Book {
    private String period;
    public static int counter = 0;

    public Magazine(String title, String period, BigDecimal year) {
        // this.setCode(Helpers.generateCodeBook(year, "magazine"));
        if (getCode() == null) {
            this.setCode(Helpers.generateCodeBook(year, "magazine"));
        }
        this.setTitle(title);
        this.setYear(year);
        this.period = period;
    }

    public Magazine(String title, String period, BigDecimal year, boolean hasCode) {
        // this.setCode(Helpers.generateCodeBook(year, "magazine"));
        this.setTitle(title);
        this.setYear(year);
        this.period = period;
    }

    @Override
    public String getCode() {
        return super.code;
    }

    @Override
    public String getTitle() {
        return super.title;
    }

    @Override
    public BigDecimal getYear() {
        return super.year;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public static int incrementCounter() {
        return ++counter;
    }

    public static void setCounter(int counter) {
        Magazine.counter = counter;
    }

    @Override
    public String toString() {
        return "Majalah => " +
                "Kode: " + getCode() + "\n" +
                "Judul: " + getTitle() + "\n" +
                "Periode: " + getPeriod() + "\n" +
                "Tahun Terbit: " + getYear();
    }
    
}
