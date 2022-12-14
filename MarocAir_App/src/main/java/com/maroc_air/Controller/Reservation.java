package com.maroc_air.Controller;
import com.maroc_air.DAO.DaoManager;
import com.maroc_air.Utils.Helper;
import com.maroc_air.Utils.Json;
import com.maroc_air.Utils.email.SimpleEmail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;

@WebServlet(name ="Reservation", value = "/Reservation")
public class Reservation extends HttpServlet {
    private final DaoManager<Reservation> dao = DaoManager.create(Reservation.class);
    public Reservation() {

    }
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation[] reservations = dao.find();
        response.addHeader("Content-Type", "application/json");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("reservations", reservations);
        map.put("count", reservations.length);
        response.getWriter().println(Json.stringify(map));
    }

    // save reservation by POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = Json.parse(request.getReader(), Reservation.class);
        boolean saved = dao.save(reservation);
        HashMap<String, Object> res = new HashMap<>();
        if (saved) {
            res.put("status", "success");
            res.put("message", "reservation saved");
            res.put("extra", reservation);
            SimpleEmail.sendSimpleEmail("oussamaelbechari@gmail.com","Reservation Ticket", "<h1>Reservation Confirmer :</h1><br>"+reservation);
            response.setStatus(201);
        } else {
            res.put("status", "error");
            res.put("message", "reservation not saved");
            response.setStatus(500);
        }
        response.setHeader("Content-Type", "application/json");
        response.getWriter().println(Json.stringify(res));
    }

    //    PUT /reservation?id=1
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //set ID for put request
        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 0) {  // handle id errors
            resp.setStatus(400);
            resp.getWriter().println("Bad request");
            return;
        }
        Reservation reservation = dao.findByPrimary(id); // find reservation by id
        if (reservation == null) { // handle null value if found
            resp.setStatus(404);
            resp.getWriter().println("Extra not found");
            return;
        }
        Reservation extraFromRequest = Json.parse(req.getReader(), Reservation.class);
        Helper.copyNonEmptyProperties(extraFromRequest, reservation);
        boolean updated = dao.save(reservation);
        HashMap<String, Object> res = new HashMap<>();
        if (updated) {
            res.put("status", "success");
            res.put("message", "Extra updated");
            res.put("extra", reservation);
            resp.setStatus(200);
        } else {
            res.put("status", "error");
            res.put("message", "Extra not updated");
            resp.setStatus(500);
        }
        resp.setHeader("Content-Type", "application/json"); // set header to application/json
        resp.getWriter().println(Json.stringify(res)); // Print writer object of result
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 0) {
            resp.setStatus(400);
            resp.getWriter().println("Bad request");
            return;
        }
        Reservation reservation = dao.findByPrimary(id);
        if (reservation == null) {
            resp.setStatus(404);
            resp.getWriter().println("Extra not found");
            return;
        }
        boolean deleted = dao.delete(reservation);
        HashMap<String, Object> res = new HashMap<>();
        if (deleted) {
            res.put("status", "success");
            res.put("message", "Extra deleted");
            resp.setStatus(200);
        } else {
            res.put("status", "error");
            res.put("message", "Extra not deleted");
            resp.setStatus(500);
        }
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(Json.stringify(res));
    }
}
