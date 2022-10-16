package com.maroc_air.Modelles;

import com.maroc_air.Controller.Vol;
import com.maroc_air.DAO.TableTest;

import java.time.LocalTime;
import java.util.Locale;

public class Reservation {
    private int nbr_adulte;
    private int nbr_enfant;
    private int prixReservation;
    private boolean archive;
    private Vol vol;


    @TableTest(tableName = "reservation")
    public Reservation(int nbr_adulte, int nbr_enfant, int prixReservation, boolean archive,Vol vol) {
        this.nbr_adulte = nbr_adulte;
        this.nbr_enfant = nbr_enfant;
        this.prixReservation = prixReservation;
        this.archive = archive;
    }

}
