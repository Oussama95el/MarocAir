package com.maroc_air.Modelles;

import com.maroc_air.DAO.Column;

public class Clients {
    @Column
    private int  codeclient ;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String email;
    @Column
    private String phone;

    public Clients(int codeclient, String nom, String prenom, String email, String phone) {
        this.codeclient = codeclient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
    }

    public Clients() {
    }
}
