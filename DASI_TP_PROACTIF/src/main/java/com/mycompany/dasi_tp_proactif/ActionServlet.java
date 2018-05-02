/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dasi_tp_proactif;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Client;
import modele.Employe;
import static service.ServiceAppli.AuthentificationClient;
import static service.ServiceAppli.AuthentificationEmploye;

/**
 *
 * @author aelomarial
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //attributs qui doivent être gérés à l'échelle de l'application
    Client connectedClient;
    Employe connectedEmploye;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ActionServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String action = request.getParameter("action");
        switch(action){
            case "connecterClient" :
                
                
                try{
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    
                    Client connectedClient = AuthentificationClient(login, password);
                    
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    if(connectedClient != null){
                        printAnswerConnexion(out);
                    }
                    out.close();
                }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
                }
                
                break;
                
            case "connecterEmploye":
                try{
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    
                    Employe connectedEmploye = AuthentificationEmploye(login, password);
                    
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    if(connectedEmploye != null){
                        printAnswerConnexion(out);
                    }
                    out.close();
                }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
                }
                break;
                
            case "deconnecterClient":
                connectedClient = null;
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                printAnswerConnexion(out);
                out.close();
                break;
                
            case "deconnecterEmploye":
                connectedClient = null;
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                printAnswerConnexion(out);
                out.close();
                break;
                
            default :
                break;
        }
    }
    
    /*
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.destroy();
    }
    
    public static void printAnswerConnexion(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        jo.addProperty("res","");
        
        JsonObject container = new JsonObject();
        container.add("res",jo);
        out.println(gson.toJson(container));
    }

}
