package com.neeraj.microservice.movies.movieservice.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public interface StringToClassMapper {

    Object convertToObject(String[] input);

    //    Class getObjectType();
    Object saveObject(Object object);

    default String checkEmptyValue(String str) {
        if (str.trim().equalsIgnoreCase("N")) {
            return "";
        }
        return str.trim();
    }

    default List<String> splitValue(String string) {
        if (string.equalsIgnoreCase("N")) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(string.split(","));
        }
    }

    default boolean getBooleanValue(String string) {
        return string.trim().equalsIgnoreCase("1");
    }

    default int getIntegerValue(String string) {
        if (string.trim().equalsIgnoreCase("N")) {
            return 0;
        }
        return Integer.parseInt(string.trim());
    }

    default Double getDobleValue(String string) {
        if (string.trim().equalsIgnoreCase("N")) {
            return 0.0;
        }
        return Double.parseDouble(string.trim());
    }
}
