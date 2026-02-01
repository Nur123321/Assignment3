package com.assignment3.utils;

import java.util.Comparator;
import java.util.List;

public final class SortingUtils {
    private SortingUtils() {
    }

    public static <T> void sortBy(List<T> items, Comparator<T> comparator) {
        items.sort(comparator);
    }
}
