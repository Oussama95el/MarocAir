package com.maroc_air.Controller;
import com.maroc_air.DAO.Dao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name ="Reservation", value = "/Reservation")
public class Reservation extends HttpServlet {
    public Reservation() {
        Dao ReservationDao = new Dao("reservation");
    }
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/insert" -> this.insertReservation(request, response);
            case "/show" -> this.showFormReservation(request, response);
            case "/Delete" -> this.deleteReservation(request, response);
            default -> {
            }
        }
    }
    private void showFormReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("reservation.jsp");
        dispatcher.forward(request,response);
    }
    private void insertReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        com.maroc_air.Modelles.Reservation newReservation = new com.maroc_air.Modelles.Reservation(1,1,35,true);
        Dao ReservationDao = new Dao("reservation");
        // insert in method dao
        response.sendRedirect("list");
    }
    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Dao ReservationDao = new Dao("reservation");
        // method to delete reservation by id
        //and return response in a list affichage
        response.sendRedirect("list");
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
