package com.ilu.enigpus.impls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.ilu.enigpus.models.Book;
import com.ilu.enigpus.models.Magazine;
import com.ilu.enigpus.models.Novel;
import com.ilu.enigpus.services.InventoryService;
import com.ilu.enigpus.utils.Helpers;

public class InventoryServiceImpl implements InventoryService {
    private String database = "src/com/ilu/enigpus/connections/database.txt";

    private ArrayList<Book> books = new ArrayList<Book>();

    public InventoryServiceImpl() {
        try {
            readBooksFromFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void addBook(Book book) {
        // TODO Auto-generated method stub
        books.add(book);

        try {
            writeBooksToFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
                // return true;
                try {
                    writeBooksToFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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

    private void writeBooksToFile() throws IOException {
        File file = new File(database);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        try {
            for (Book book : books) {
                if (book instanceof Novel) {
                    writer.write("novel," + book.getCode() + "," + book.getTitle() + "," + ((Novel) book).getPublisher()
                            + ","
                            + ((Novel) book).getAuthor() + "," + book.getYear() + "\n");

                }

                if (book instanceof Magazine) {
                    writer.write("magazine," + book.getCode() + "," + book.getTitle() + ","
                            + ((Magazine) book).getPeriod() + ","
                            + book.getYear()
                            + "\n");
                }
            }
        } finally {
            writer.close();
        }
    }

    private void readBooksFromFile() throws IOException {
        File file = new File(database);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        ArrayList<Book> localBooks = new ArrayList<Book>();

        String novelCounter = "0";
        String magazineCounter = "0";

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse data dari setiap baris
                String[] data = line.split(","); // Asumsikan format CSV sederhana dengan pemisah ","
                String type = data[0];

                Book book;
                if (type.equals("novel")) {
                    book = new Novel(data[2], data[3], data[4], new BigDecimal(data[5]), false);
                    book.setCode(data[1]);

                    String[] code = book.getCode().split("-");

                    if (Helpers.lessThan(novelCounter, code[2])) {
                        novelCounter = Helpers.toBigDecimal(code[2]).toString();
                    }
                } else if (type.equals("magazine")) {

                    book = new Magazine(data[2], data[3], new BigDecimal(data[4]), false);
                    book.setCode(data[1]);

                    String[] code = book.getCode().split("-");

                    if (Helpers.lessThan(magazineCounter, code[2])) {
                        magazineCounter = Helpers.toBigDecimal(code[2]).toString();
                    }
                } else {
                    book = null;
                }

                localBooks.add(book);
            }

            Novel.setCounter(Integer.parseInt(novelCounter));
            Magazine.setCounter(Integer.parseInt(magazineCounter));
            books = localBooks;
        } finally {
            reader.close();
        }
    }

}
