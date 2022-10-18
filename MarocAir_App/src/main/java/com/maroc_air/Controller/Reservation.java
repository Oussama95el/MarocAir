package com.maroc_air.Controller;
import com.maroc_air.DAO.DaoManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name ="Reservation", value = "/Reservation")
public class Reservation extends HttpServlet {
    public Reservation() {

    }
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        switch (action){
            case "/insert":
                this.insertReservation(request,response);
                break;
            case "/show":
                this.showFormReservation(request,response);
                break;
            case "/Delete":
                this.deleteReservation(request,response);
                break;
            default:
                break;
        }
    }
    private void showFormReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("reservation.jsp");
        dispatcher.forward(request,response);
    }
    private void insertReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        com.maroc_air.Modelles.Reservation newReservation = new com.maroc_air.Modelles.Reservation(1,1,35,true);
//         insert in method dao
        response.sendRedirect("list");
    }
    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        // method to delete reservation by id
        //and return response in a list affichage
        response.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
