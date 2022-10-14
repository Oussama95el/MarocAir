package com.maroc_air.Database;

public class Config {
    private final static String URL = "jdbc:postgresql://localhost:5432/maroc_air";
    private  final static String USER = "postgres";
    private final static String PASSWORD = "papa";

    public static String getUrl(){
        return URL;
    }
    public static String getUser(){
        return USER;
    }
    public  static String getPassword(){
        return PASSWORD;
    }
}
