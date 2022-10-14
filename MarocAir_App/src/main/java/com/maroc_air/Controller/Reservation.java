package com.maroc_air.Controller;
import com.maroc_air.DAO.Dao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Reservation", value = "/talata",urlPatterns="/reservation")
public class Reservation extends HttpServlet {
    public Reservation() {
        Dao ReservationDao = new Dao("clients");
    }
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case "/reservation":
                this.showFormReservation(request,response);
                break;
            case "/reservation/insert":
                this.insertReservation(request,response);
                break;
            case "/reservation/Delete":
                break;
            default:
                break;
        }
    }
    private void showFormReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void insertReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
