package com.maroc_air.Modelles;
import com.maroc_air.DAO.Column;
import com.maroc_air.DAO.Table;

@Table(tableName = "reservation")
public class Reservation {
    @Column
    private int id;
    @Column
    private int id_client;
    @Column
    private int id_vol;
    @Column
    private int nbr_adulte;
    @Column
    private int nbr_enfant;



    //Constructor
    public Reservation(int nbr_adulte, int nbr_enfant,int id_client,int id_vol) {
        this.nbr_adulte = nbr_adulte;
        this.nbr_enfant = nbr_enfant;
        this.id_vol = id_vol;
        this.id_client = id_client;
    }

    public Reservation(){}
}
