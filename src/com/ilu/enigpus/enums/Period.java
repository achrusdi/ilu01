package com.ilu.enigpus.enums;

public enum Period {
    weekly, monthly;

    public static boolean isValid(String period) {
        for (Period p : Period.values()) {
            if (p.name().equalsIgnoreCase(period)) {
                return true;
            }
        }

        return false;
    }
}
