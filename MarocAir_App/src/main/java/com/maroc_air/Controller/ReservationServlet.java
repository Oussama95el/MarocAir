package com.maroc_air.Controller;
import com.maroc_air.DAO.DaoManager;
import com.maroc_air.Modelles.Reservation;
import com.maroc_air.Utils.Json;
import com.maroc_air.Utils.email.SimpleEmail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
@WebServlet(name ="Reservation", value = "/Reservation")
public class ReservationServlet extends HttpServlet {
    private final DaoManager<Reservation> dao = DaoManager.create(Reservation.class);
    public ReservationServlet() {}
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation[] reservations = dao.find();
        response.addHeader("Content-Type", "application/json");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("reservations", reservations);
        map.put("count", reservations.length);
        String nextJSP = "/volDisponible.jsp";
        request.setAttribute("data", map);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }
    // save reservation by POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idclient =1;
        int idvol = Integer.parseInt(request.getParameter("idvol"));
        int nbrAdulte = Integer.parseInt(request.getParameter("nbr_adulte"));
        int nbr_enfant = Integer.parseInt(request.getParameter("nbr_anfant"));
        Reservation reservation = new Reservation(nbrAdulte,nbr_enfant,idclient,idvol);
//       Reservation reservation = Json.parse(request.getReader(), Reservation.class);
//        print a message to client to send a email confirmation

            boolean saved = dao.save(reservation);
            HashMap<String, Object> res = new HashMap<>();
            if (saved) {
                SimpleEmail.sendSimpleEmail("oussamaelbechari@gmail.com","Reservation Ticket", "<h1>Reservation Confirmer :</h1><br>" +
                        "<p>Voicie l'ID de votre vol reserver : <B>"+ idvol+" </B></p>"+
                        "<p>Voicie le nombre enfant de votre vol reserver : <B>"+ idvol+" </B></p>"+
                        "<p>Voicie le nombre adulte de votre vol reserver : <B>"+ idvol+" </B></p>");
                res.put("status", "success");
                res.put("message", "reservation saved");
                res.put("extra", reservation);
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
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //set ID for put request
//        int id = Integer.parseInt(req.getParameter("id"));
//        if (id < 0) {  // handle id errors
//            resp.setStatus(400);
//            resp.getWriter().println("Bad request");
//            return;
//        }
//        Reservation reservation = dao.findByPrimary(id); // find reservation by id
//        if (reservation == null) { // handle null value if found
//            resp.setStatus(404);
//            resp.getWriter().println("Extra not found");
//            return;
//        }
//        ReservationServlet extraFromRequest = Json.parse(req.getReader(), ReservationServlet.class);
//        Helper.copyNonEmptyProperties(extraFromRequest, reservation);
//        boolean updated = dao.save(reservation);
//        HashMap<String, Object> res = new HashMap<>();
//        if (updated) {
//            res.put("status", "success");
//            res.put("message", "Extra updated");
//            res.put("extra", reservation);
//            resp.setStatus(200);
//        } else {
//            res.put("status", "error");
//            res.put("message", "Extra not updated");
//            resp.setStatus(500);
//        }
//        resp.setHeader("Content-Type", "application/json"); // set header to application/json
//        resp.getWriter().println(Json.stringify(res)); // Print writer object of result
//    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//        if (id < 0) {
//            resp.setStatus(400);
//            resp.getWriter().println("Bad request");
//            return;
//        }
//        Reservation reservation = dao.findByPrimary(id);
//        if (reservation == null) {
//            resp.setStatus(404);
//            resp.getWriter().println("Extra not found");
//            return;
//        }
//        boolean deleted = dao.delete(reservation);
//        HashMap<String, Object> res = new HashMap<>();
//        if (deleted) {
//            res.put("status", "success");
//            res.put("message", "Extra deleted");
//            resp.setStatus(200);
//        } else {
//            res.put("status", "error");
//            res.put("message", "Extra not deleted");
//            resp.setStatus(500);
//        }
//        resp.setHeader("Content-Type", "application/json");
//        resp.getWriter().println(Json.stringify(res));
//    }
}
