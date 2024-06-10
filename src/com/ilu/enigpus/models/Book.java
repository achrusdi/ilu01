package com.ilu.enigpus.models;

import java.math.BigDecimal;

public abstract class Book {
    public String code, title;
    public BigDecimal year;

    public abstract String getCode();

    public abstract String getTitle();

    public abstract BigDecimal getYear();

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }
    
}
