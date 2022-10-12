package com.maroc_air.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Reservation", value = "/reservation")
public class Reservation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] tests = {"hahah","efefef","azerzrz","azreaz fvff"};
        request.setAttribute("tests",tests);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reservation.jsp");
        dispatcher.forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
