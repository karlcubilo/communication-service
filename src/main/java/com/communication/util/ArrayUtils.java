package com.communication.util;

import java.util.List;

public class ArrayUtils {

    private ArrayUtils() {

    }

    public static String[] convertListToArray(List<String> strings) {
        return strings.toArray(String[]::new);
    }
}
