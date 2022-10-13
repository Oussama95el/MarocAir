package com.maroc_air.Controller;
import com.maroc_air.DAO.DAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "Reservation", value = "/reservation")
public class Reservation extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     DAO clients = new DAO("clients");
     ResultSet testData = clients.getAll();
        System.out.println(testData);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
