package com.ilu.enigpus;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.ilu.enigpus.enums.Period;
import com.ilu.enigpus.impls.InventoryServiceImpl;
import com.ilu.enigpus.models.Book;
import com.ilu.enigpus.models.Magazine;
import com.ilu.enigpus.models.Novel;
import com.ilu.enigpus.utils.Helpers;

public class Menu {

    public static InventoryServiceImpl inventory = new InventoryServiceImpl();

    public static void run() {
        init();
        showMenu();
    }

    public static void showMenu() {
        System.out.println("-".repeat(50));
        System.out.println("Welcome to Enigpus");
        System.out.println("-".repeat(50));
        System.out.println("1. Add Book");
        System.out.println("2. Search Book by Title");
        System.out.println("3. Search Book by Code");
        System.out.println("4. Delete Book by Code");
        System.out.println("5. Get All Book");
        System.out.println("6. Exit");
        System.out.println("-".repeat(50));

        String option;
        do {
            System.out.print("Choose an option (1 - 4): ");
            option = Helpers.inputString();

            if (!Helpers.isNumeric(option)) {
                option = "-1";
                continue;
            }

            if (Helpers.lessThan(option, "1") || Helpers.greaterThan(option, "6")) {
                option = "-1";
                continue;
            }
        } while (option.equals("-1"));
        System.out.println("-".repeat(50));

        switch (option) {
            case "1":
                System.out.println();
                System.out.println("-".repeat(50));
                System.out.println("Add Book");
                System.out.println("-".repeat(50));
                addBook();
                System.out.println("-".repeat(50));
                System.out.println();
                showMenu();
                break;

            case "2":
                System.out.println();
                System.out.println("-".repeat(50));
                System.out.println("Search Book By Title");
                System.out.println("-".repeat(50));
                searchBookByTitle();
                System.out.println("-".repeat(50));
                System.out.println();
                showMenu();
                break;

            case "3":
                System.out.println();
                System.out.println("-".repeat(50));
                System.out.println("Search Book By Code");
                System.out.println("-".repeat(50));
                searchBookByCode();
                System.out.println("-".repeat(50));
                System.out.println();
                showMenu();
                break;

            case "4":
                System.out.println();
                System.out.println("-".repeat(50));
                System.out.println("Delete Book By Code");
                System.out.println("-".repeat(50));
                deleteBookByCode();
                System.out.println("-".repeat(50));
                System.out.println();
                showMenu();
                break;

            case "5":
                System.out.println();
                System.out.println("-".repeat(50));
                System.out.println("Get All Book");
                System.out.println("-".repeat(50));
                getAllBooks();
                System.out.println("-".repeat(50));
                System.out.println();
                showMenu();
                break;

            case "6":
                exitMenu();
                break;

            default:
                break;
        }
    }

    public static void deleteBookByCode() {
        if (inventory.getBooksLength() == 0) {
            System.out.println("Empty inventory");
            return;
        }
        
        System.out.print("Enter code: ");
        String code = Helpers.inputString();
        if (inventory.deleteBookByCode(code)) {
            System.out.println("Book deleted");
        } else {
            System.out.println("Book not found");
        }
    }

    public static void searchBookByCode() {
        if (inventory.getBooksLength() == 0) {
            System.out.println("Empty inventory");
            return;
        }

        System.out.print("Enter code: ");
        String code = Helpers.inputString();
        ArrayList<Book> books = inventory.searchBookByCode(code);
        if (books == null || books.isEmpty()) {
            System.out.println("Book not found");
        } else {
            printBooks(books);
        }
    }

    public static void searchBookByTitle() {
        if (inventory.getBooksLength() == 0) {
            System.out.println("Empty inventory");
            return;
        }
        System.out.print("Enter title: ");
        String title = Helpers.inputString();
        ArrayList<Book> book = inventory.searchBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found");
        } else {
            printBooks(book);
        }
    }

    public static void getAllBooks() {
        if (inventory.getBooksLength() == 0) {
            System.out.println("Empty inventory");
            return;
        }
        
        ArrayList<Book> books = inventory.getAllBook();

        printBooks(books);
    }

    public static void printBooks(ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));

            if (i < books.size() - 1) {
                System.out.println();
            }
        }
    }

    public static void addBook() {

        String type;
        do {
            System.out.print("Add Novel(n/N) or Magazine(m/M)?");
            type = Helpers.inputString();

            if (type.equals("n") || type.equals("N") || type.equals("m") || type.equals("M")) {
                continue;
            }

            type = "-1";
        } while (type.equals("-1"));

        type = type.toLowerCase();

        boolean isSuccess = false;
        if (type.equals("n")) {
            novelForm();
            isSuccess = true;
        }

        if (type.equals("m")) {
            magazineForm();
            isSuccess = true;
        }

        if (!isSuccess) {
            System.out.println("Failed to add book. Try again");
        } else {
            System.out.println("Book added successfully");
        }

    }

    public static void magazineForm() {
        String title = mandatoryForm("Title");

        String period;
        do {
            System.out.print("Period (weekly/monthly): ");
            period = Helpers.inputString();

            if (Period.isValid(period)) {
                continue;
            }

            period = "-1";
        } while (period.equals("-1"));

        String year = yearForm();
        Magazine newMagazine = new Magazine(title, period, Helpers.toBigDecimal(year));
        inventory.addBook(newMagazine);
    }

    public static void novelForm() {
        String title = mandatoryForm("Title");

        String publisher = mandatoryForm("Publisher");

        String year = yearForm();

        String author = mandatoryForm("Author");

        Novel newNovel = new Novel(title, publisher, author, Helpers.toBigDecimal(year));
        inventory.addBook(newNovel);

    }

    public static String mandatoryForm(String msg) {
        String input;
        do {
            System.out.print(msg + ": ");
            input = Helpers.inputString();
        } while (Helpers.isBlankOrEmpty(input));

        return input;
    }

    public static String yearForm() {
        String input;
        do {
            System.out.print("Year: ");
            input = Helpers.inputString();
        } while (Helpers.isBlankOrEmpty(input) || !Helpers.isNumeric(input) || Helpers.lessThan(input, "0"));

        return input;
    }

    public static void exitMenu() {
        System.out.println("Exiting program...");
        System.exit(0);
    }

    public static void init() {
        Book newBook = new Novel("test title", "rusdi", "rusdi juga", new BigDecimal(222));
        inventory.addBook(newBook);
        Book newBook2 = new Novel("test title2", "rusdi", "rusdi juga", new BigDecimal(200));
        inventory.addBook(newBook2);
        Book newBook3 = new Magazine("test title3", "rusdi", new BigDecimal(2010));
        inventory.addBook(newBook3);
        Book newBook4 = new Magazine("test title4", "rusdi", new BigDecimal(2011));
        inventory.addBook(newBook4);
    }
}
