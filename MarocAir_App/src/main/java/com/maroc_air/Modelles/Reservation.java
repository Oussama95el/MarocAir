package com.maroc_air.Modelles;

import com.maroc_air.Controller.Vol;
import com.maroc_air.DAO.Column;
import com.maroc_air.DAO.Table;
import java.time.LocalTime;
import java.util.Locale;

@Table(tableName = "reservation")
public class Reservation {
    @Column
    private int nbr_adulte;
    @Column
    private int nbr_enfant;
    @Column
    private int prixReservation;
    @Column
    private boolean archive;
    @Column
    private int id_vol;
    @Column
    private int id_client;


    //Constructor
    public Reservation(int nbr_adulte, int nbr_enfant, int prixReservation, boolean archive,int id_client,int id_vol) {
        this.nbr_adulte = nbr_adulte;
        this.nbr_enfant = nbr_enfant;
        this.prixReservation = prixReservation;
        this.archive = archive;
        this.id_vol = id_vol;
        this.id_client = id_client;
    }

    public Reservation(){}
}
