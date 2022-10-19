package com.maroc_air.Controller;

import com.maroc_air.DAO.DaoManager;
import com.maroc_air.Modelles.Reservation;
import com.maroc_air.Modelles.Vol;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;


@WebServlet(name = "VolDispoServlet", value = "/VolDispoServlet")
public class VolDispoServlet extends HttpServlet {
    private final DaoManager<Vol> dao = DaoManager.create(Vol.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate datedepart = LocalDate.parse(request.getParameter("datedepart"));
        String  lieuDepart = request.getParameter("lieuDepart");
        String  lieuArrever =request.getParameter("lieuArrever");
        String[] fields = new String[]{"villedepart","villearrive"};
        String[] values = new String[]{lieuDepart,lieuArrever};
        System.out.println(values);
        Vol[] vols = dao.findAll(fields,values);
        response.addHeader("Content-Type", "application/json");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("vol", vols);
        map.put("count", vols.length);
        String nextJSP = "/volDisponible.jsp";
        request.setAttribute("data", map);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
