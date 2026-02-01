package com.assignment3.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class ReflectionUtils {
    private ReflectionUtils() {
    }

    public static List<String> listFieldNames(Object target) {
        List<String> fields = new ArrayList<>();
        for (Field field : target.getClass().getDeclaredFields()) {
            fields.add(field.getName());
        }
        return fields;
    }

    public static String describeClass(Object target) {
        Class<?> clazz = target.getClass();
        return clazz.getSimpleName() + " in package " + clazz.getPackageName();
    }
}
