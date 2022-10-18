package com.maroc_air.Modelles;

import com.maroc_air.DAO.Column;
import com.maroc_air.DAO.Table;


import java.sql.Date;


@Table(tableName = "vol")
public class Vol{
    @Column
    private String villeDepart;
    @Column
    private String villeArrive;
    @Column
    private int nbrPlace;
    @Column
    private Date dateDepart;
    @Column
    private Date dateArrive;
    @Column
    private Airline airLine;


    public Vol(String villeDepart, String villeArrive, int nbrPlace, Date dateDepart, Date dateArrive, Airline airLine) {
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.nbrPlace = nbrPlace;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        this.airLine = airLine;
    }
    public Vol(){

    }

}
