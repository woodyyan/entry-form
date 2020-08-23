package com.huawei.refactoring.form;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Validators {
    public static List<String> couldBeNull(List<String> list) {
        if (list == null) {
            return Collections.emptyList();
        } else {
            return list;
        }
    }

    public static int notNegative(int value, String message) {
        if (value < 0) throw new IllegalArgumentException(message);
        return value;
    }

    public static double notNegative(double value, String message) {
        if (value < 0) throw new IllegalArgumentException(message);
        return value;
    }

    public static LocalDate afterToday(LocalDate birthday) {
        if (birthday.isAfter(LocalDate.now())) throw new IllegalArgumentException("birthday should before today");
        return birthday;
    }

    public static LocalDate notNull(LocalDate localDate, String message) {
        if (localDate == null) throw new IllegalArgumentException(message);
        return localDate;
    }

    public static String couldBeNull(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }

    public static String notNull(String value, String message) {
        if (value == null || "".equals(value)) throw new IllegalArgumentException(message);
        return value;
    }
}
