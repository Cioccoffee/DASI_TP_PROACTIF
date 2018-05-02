/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dasi_tp_proactif;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static service.ServiceAppli.AuthentificationClient;

/**
 *
 * @author aelomarial
 */
@WebServlet(name = "ActionServletAccueil", urlPatterns = {"/ActionServletAccueil"})
public class ActionServletAccueil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ActionServletAccueil</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ActionServletAccueil at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String action = request.getParameter("action");
        switch(action){
            case "connecterClient" :
                
                
                try{
                    //lp = s.consulterListePersonnes();
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    AuthentificationClient(login, password);
                }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                //PrintWriter out = response.getWriter();
                //printListPeople(out, lp);
                //out.close();
                break;
                
            case "connecterEmploye":
                try{
                    //lp = s.consulterListePersonnes();
                }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                //PrintWriter out = response.getWriter();
                //printListPeople(out, lp);
                //out.close();    
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
    

}
