/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.UIManager.put;

/**
 *
 * @author noncowi
 */
@WebServlet(name = "RestServlet", urlPatterns = {"/api/quote/*"})
public class RestServlet extends HttpServlet {

    private Map<Integer, String> quotes = new HashMap() 
    {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    public String getParam(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        String parameter = null;
        if (parts.length == 5) {
            parameter = parts[4];
        }
        return parameter;
    }

    protected void makeResponse(String responseString, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(responseString);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = getParam(request);
        int id = Integer.parseInt(idString);
        String quote = quotes.get(id);
        JsonObject json = new JsonObject();
        json.addProperty("quote", quote);
//        json.addProperty("neger", "john");
        
        makeResponse(new Gson().toJson(json), response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = 1+quotes.size();
        
        quotes.put(id, getParam(request));
        
        JsonObject json = new JsonObject();
        
        System.out.println("kasper lugter af ost");
        
        json.addProperty("id", id);
        
        json.addProperty("quote", quotes.get(id));
        
        makeResponse(new Gson().toJson(json), response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
