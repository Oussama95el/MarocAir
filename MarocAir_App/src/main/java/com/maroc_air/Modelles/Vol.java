package com.maroc_air.Modelles;

import com.maroc_air.DAO.Dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class Vol extends Dao{

    private static final Dao dao = new Dao("vol");
    private String villeDepart;
    private String villeArrive;
    private int nbrPlace;
    private Date dateDepart;
    private Date dateArrive;
    private Airline airLine;

    public Vol(String villeDepart, String villeArrive, int nbrPlace, Date dateDepart, Date dateArrive, Airline airLine) {
        super("vol");
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.nbrPlace = nbrPlace;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        this.airLine = airLine;
    }
    // getters
    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public Airline getAirLine() {
        return airLine;
    }
    // Setters
    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setAirLine(Airline airLine) {
        this.airLine = airLine;
    }

    public static ResultSet getAllVols(){
        return dao.getAll();
    }



    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(22,10,16);
        System.out.println(formatter.format(date));
        Vol vol = new Vol("Casa","Tanger",40,date,date,Airline.RYANAIR);
        System.out.println(vol.save());


    }
}
