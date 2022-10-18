package com.maroc_air.Controller;
import com.maroc_air.DAO.Dao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="Reservation", value = "/Reservation")
public class Reservation extends HttpServlet {
    public Reservation() {
        Dao ReservationDao = new Dao("reservation");
    }
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        response.setContentType("text/plain");


        String TypeReservation = request.getParameter("typereservation");
        String nAdulte = request.getParameter("Nadulte");
        String nenfant = request.getParameter("nenfant");
        String lieuDepart= request.getParameter("lieuDepart");
        String lieuArrever= request.getParameter("lieuArrever");
        String datedepart = request.getParameter("datedepart");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>form example</title>");
        out.println("<head>");
        out.println("<body>");
        out.println(TypeReservation +  " "+nAdulte+"  "+nenfant+"   "+lieuArrever+"  "+lieuDepart+"  "+datedepart);
        out.println("</body>");
        out.println("</html>");

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
