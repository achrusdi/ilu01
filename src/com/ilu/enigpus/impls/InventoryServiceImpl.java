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

public class InventoryServiceImpl implements InventoryService {

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
        try {
            writeBooksToFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return books;
    }

    public int getBooksLength() {
        return books.size();
    }

    private void writeBooksToFile() throws IOException {
        File file = new File("database.txt");
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
        File file = new File("database.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse data dari setiap baris
                String[] data = line.split(","); // Asumsikan format CSV sederhana dengan pemisah ","
                String type = data[0];

                Book book;
                if (type.equals("novel")) {
                    // writer.write("novel," + book.getCode() + "," + book.getTitle() + "," + ((Novel) book).getPublisher()
                    //         + ","
                    //         + ((Novel) book).getAuthor() + "," + book.getYear() + "\n");

                    // book = new Novel(code, title);
                    book = new Novel(data[2], data[3], data[4], new BigDecimal(data[5]));
                } else if (type.equals("magazine")) {
                    // writer.write("magazine," + book.getCode() + "," + book.getTitle() + ","
                    //         + ((Magazine) book).getPeriod() + ","
                    //         + book.getYear()
                    //         + "\n");

                    // book = new Magazine(code, title);

                    book = new Magazine(data[2], data[3], new BigDecimal(data[4]));
                } else {
                    // Tangani jenis buku yang tidak valid
                    book = null;
                }

                books.add(book);
            }
        } finally {
            reader.close();
        }
    }

}
