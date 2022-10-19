package com.maroc_air.Controller;
import com.maroc_air.DAO.DaoManager;
import com.maroc_air.Modelles.Vol;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "Vol", value = "/Vol")
public class VolServlet extends HttpServlet {
    private final DaoManager<Vol> dao = DaoManager.create(Vol.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vol[] vol = dao.find();
        HashMap<String, Object> map = new HashMap<>();
        map.put("vol", vol);
        map.put("count", vol.length);
        String nextJSP = "/Pagereservation.jsp";
        request.setAttribute("data", map);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
