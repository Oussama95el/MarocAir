package com.maroc_air.Controller;

import com.maroc_air.DAO.Dao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DataReservation", value = "/")
public class DataReservation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dao daoDataReservation = new Dao("vol");


        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>form example</title>");
        out.println("<head>");
        out.println("<body>");
        out.println(daoDataReservation.getAll()+ "test");
        out.println("</body>");
        out.println("</html>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
