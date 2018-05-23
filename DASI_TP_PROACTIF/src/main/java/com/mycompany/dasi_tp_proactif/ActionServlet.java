/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dasi_tp_proactif;





import actions.ActionConnecterClient;
import actions.ActionConnecterEmploye;
import actions.ActionInscrireClient;
import actions.ActionInterventionAnimal;
import actions.ActionInterventionIncident;
import actions.ActionInterventionLivraison;
import actions.ActionValiderIntervention;
import dao.JpaUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Employe;
import modele.Intervention;
import modele.Livraison;
import service.ServiceAppli;

import static service.ServiceAppli.AuthentificationClient;
import static service.ServiceAppli.AuthentificationEmploye;
import static service.ServiceAppli.InscriptionClient;

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
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
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

        //permet de gérer les attributs qui existent à l'échelle de l'application
        HttpSession session = request.getSession(true);
        
        String action = request.getParameter("action");
        switch(action){
            case "connecterClient" :
                
                try{
                    Client c = ActionConnecterClient.execute(request);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    if(c != null){
                        session.setAttribute("client", c);
                        Serializer.printAnswerConnexion(out);
                    }
                    out.close();
                    
                }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
                }
                
                break;
                
            case "connecterEmploye":
                try{
                    Employe e = ActionConnecterEmploye.execute(request);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    if(e != null){
                        session.setAttribute("employe", e);
                        Serializer.printAnswerConnexion(out);
                    }
                    out.close();
                }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
                }
                break;
                
            case "deconnecterClient":
                session.setAttribute("client", null);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                Serializer.printAnswerConnexion(out);
                out.close();
                break;
                
            case "deconnecterEmploye":
                session.setAttribute("employe", null);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerConnexion(out);
                out.close();
                break;
            
            case "getNomClient":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerNomClient(out, session);
                out.close();
                break;
                
            case "getHistoriqueClient":
                Client cHisto = ServiceAppli.AuthentificationClient(
                        ((Client)session.getAttribute("client")).getAdresseElectronique(),
                        ((Client)session.getAttribute("client")).getMdp());
                List<Intervention> li = (cHisto.getInterventionsDemandees());
                //List<Intervention> li = ((Client)session.getAttribute("client")).getInterventionsDemandees());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerHistorique(out,li);
                out.close();
                break;
                
            // ces actions retournent un employé
            case "demanderInterventionAnimal":
                Employe e = ActionInterventionAnimal.execute(request,(Client)session.getAttribute("client"));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerIntervention(out,e);
                out.close();
                break;
                
            case "demanderInterventionLivraison":
                e = ActionInterventionLivraison.execute(request,(Client)session.getAttribute("client"));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerIntervention(out,e);
                out.close();
                break;
                
            case "demanderInterventionIncident":
                e = ActionInterventionIncident.execute(request,(Client)session.getAttribute("client"));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerIntervention(out,e);
                out.close();
                break;
                
            case "inscrireClient":
                ActionInscrireClient.execute(request);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerInscription(out);
                out.close();
                break;
                
            case "getEmploye":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerNomEmploye(out,session);
                out.close();
                break;
                
            case "getAdresse":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerAdresse(out, session);
                out.close();
                break;
                
            case "getDemande":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerDemande(out, session);
                out.close();
                break;
                
            case "getClient":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerClient(out, session);
                out.close();
                break;
                
            case "getType":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerType(out, session);
                out.close();
                break;
                
            case "getAnimal":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerAnimal(out, session);
                out.close();
                break;
                
            case "getLivraison":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerLivraison(out, session);
                out.close();
                break;
                
            case "getDescription":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerDescription(out, session);
                out.close();
                break;
                
            case "validerIntervention":
                boolean valider = ActionValiderIntervention.execute(request, session);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerValiderIntervention(out, valider);
                out.close();
                break;
                
            case "getInterventionsJour":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                Serializer.printAnswerListInterventions(out);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
