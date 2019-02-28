package com.neeraj.microservices.movies.springbatch.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class BasicConversion {

    public static final String EMPTY_VALUE = "\\N";

    public String checkEmptyValue(String str) {
        if (str.trim().equalsIgnoreCase(EMPTY_VALUE)) {
            return "";
        }
        return str.trim();
    }

    public List<String> splitValue(String string) {
        if (string.equalsIgnoreCase(EMPTY_VALUE)) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(string.split(","));
        }
    }

    public boolean getBooleanValue(String string) {
        return string.trim().equalsIgnoreCase("1");
    }

    public int getIntegerValue(String string) {
        if (string.trim().equalsIgnoreCase(EMPTY_VALUE)) {
            return 0;
        }
        return Integer.parseInt(string.trim());
    }

    public Double getDoubleValue(String string) {
        if (string.trim().equalsIgnoreCase(EMPTY_VALUE)) {
            return 0.0;
        }
        return Double.parseDouble(string.trim());
    }
}
