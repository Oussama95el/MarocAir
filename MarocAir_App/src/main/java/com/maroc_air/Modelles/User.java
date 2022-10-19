package com.maroc_air.Modelles;


import com.maroc_air.DAO.Column;
import com.maroc_air.DAO.Table;

@Table(tableName = "clients", primaryKey = "codeclient")
public class User {
    @Column
    private int codeClient;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private long phone;

    //Constructor
    public User(int codeClient, String nom, String prenom, String email, String password, long phone) {
        this.codeClient = codeClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    public User(){

    }
}
