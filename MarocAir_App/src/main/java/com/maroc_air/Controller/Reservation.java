package com.maroc_air.Controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(name = "Reservation", value = "/talata",urlPatterns="/reservation")
public class Reservation extends HttpServlet {
 /*   public Reservation() {
        Dao ReservationDao = new Dao("clients");
    }*/
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case "/reservation":
                this.showFormReservation(request,response);
                break;
            case "/reservation/update":
                break;
            case "/reservation/Delete":
                break;
            default:
                break;

        }
    }
    private void showFormReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("reservation.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
