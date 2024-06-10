package com.ilu.enigpus.utils;

import java.math.BigDecimal;
import java.util.Scanner;

import com.ilu.enigpus.models.Magazine;
import com.ilu.enigpus.models.Novel;

public class Helpers {
    private static Scanner sc = new Scanner(System.in);

    public static String generateCodeBook(BigDecimal year, String type) {
        String prefix = year + "-";
        int counter = 0;
        if (type.equals("novel")) {
            counter = Novel.incrementCounter();
            prefix += "A-";
        } else if (type.equals("magazine")) {
            counter = Magazine.incrementCounter();
            prefix += "B-";
        } else {
            return null;
        }

        String counterString = String.format("%04d", counter);

        return prefix + counterString;

    }

    public static String inputString() {
        return sc.nextLine();
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static BigDecimal toBigDecimal(String str) {
        if (isNumeric(str)) {
            return new BigDecimal(str);
        } else {
            throw new NumberFormatException("String cannot be converted to BigDecimal: " + str);
        }
    }

    public static boolean lessThan(String str1, String str2) {
        return toBigDecimal(str1).compareTo(toBigDecimal(str2)) < 0;
    }

    public static boolean greaterThan(String str1, String str2) {
        return toBigDecimal(str1).compareTo(toBigDecimal(str2)) > 0;
    }

    
    public static boolean isBlankOrEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

}
