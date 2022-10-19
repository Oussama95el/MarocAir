package com.maroc_air.Modelles;

import com.maroc_air.DAO.Column;
import com.maroc_air.DAO.Table;


import java.sql.Date;


@Table(tableName = "vol")
public class Vol{
    @Column
    private int idvol;
    @Column
    private String villedepart;
    @Column
    private String villearrive;
    @Column
    private int nbrplace;
    @Column
    private String datedepart;
    @Column
    private String datearrive;
    @Column(type = Airline.class)
    private Airline airline;
    @Column
    private String prixvol;


    public Vol(int idvol,String villedepart, String villearrive, int nbrplace, String datedepart, String datearrive, Airline airline,String prixvol) {
        this.idvol = idvol;
        this.villedepart = villedepart;
        this.villearrive = villearrive;
        this.nbrplace = nbrplace;
        this.datedepart = datedepart;
        this.datearrive = datearrive;
        this.airline = airline;
        this.prixvol= prixvol;
    }
    public Vol(){}


    public static void main(String[] args) {

    }

    public int getIdvol() {
        return idvol;
    }

    public String getVilledepart() {
        return villedepart;
    }

    public String getVillearrive() {
        return villearrive;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public String getDatedepart() {
        return datedepart;
    }

    public String getDatearrive() {
        return datearrive;
    }

    public Airline getAirline() {
        return airline;
    }

    public String getPrixvol() {
        return prixvol;
    }
}
