package com.maroc_air.Modelles;

public class Reservation {
    private int nbr_adulte;
    private int nbr_enfant;
    private int prixReservation;
    private boolean archive;

    public Reservation(int nbr_adulte, int nbr_enfant, int prixReservation, boolean archive) {
        this.nbr_adulte = nbr_adulte;
        this.nbr_enfant = nbr_enfant;
        this.prixReservation = prixReservation;
        this.archive = archive;
    }
}
