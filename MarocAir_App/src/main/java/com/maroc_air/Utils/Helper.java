package com.maroc_air.Utils;

public class Helper {
    /**
     * copy field property using generic type variables
     * @param source generic
     * @param target generic
     * @param <T> generic type
     */
    public static <T> void copyProperties(T source, T target) {
        try {
            for (java.lang.reflect.Field field : source.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(source);
                field.set(target, value);
                field.setAccessible(false);
            }
        } catch (Exception e) {
            System.out.println("Error while copying properties");
            e.printStackTrace();
        }
    }

    /**
     * Copy the value of empty property for query
     * @param source generic
     * @param target generic
     * @param <T> generic type
     */
    public static <T> void copyNonEmptyProperties(T source, T target) {
        try {
            for (java.lang.reflect.Field field : source.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(source);
                if (value != null && !value.toString().isEmpty() && !value.toString().equals("0") && !value.toString()
                        .equals("0.0")) {
                    field.set(target, value);
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            System.out.println("Error while copying properties");
            e.printStackTrace();
        }

    }
}
