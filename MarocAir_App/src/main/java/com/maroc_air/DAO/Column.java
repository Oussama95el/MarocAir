package com.maroc_air.DAO;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name() default "";

    Class<?> type() default Object.class;
}
