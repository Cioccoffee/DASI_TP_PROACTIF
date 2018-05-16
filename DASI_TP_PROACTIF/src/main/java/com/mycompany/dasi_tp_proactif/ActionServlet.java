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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Animal;
import modele.Client;
import modele.Employe;
import modele.Intervention;
import modele.Livraison;
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
    
    //attributs qui doivent être gérés à l'échelle de l'application
    static Client connectedClient;
    static Employe connectedEmploye;
    
    
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

        String action = request.getParameter("action");
        switch(action){
            case "connecterClient" :
                
                
                try{
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    
                    connectedClient = AuthentificationClient(login, password);
                    
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
                    
                    connectedEmploye = AuthentificationEmploye(login, password);
                    
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
            
            case "getNomClient":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                printAnswerNomClient(out);
                out.close();
                break;
                
            case "getHistoriqueClient":
                List<Intervention> li = connectedClient.getInterventionsDemandees();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                printAnswerHistorique(out,li);
                out.close();
                break;
                
            case "inscrireClient":
                System.out.println("Inscription: ");
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String civilite = request.getParameter("civilite");
                String date = request.getParameter("date");
                System.out.println(date);
                String pays = request.getParameter("pays");
                String rue = request.getParameter("rue");
                String ville = request.getParameter("ville");
                String codePostal = request.getParameter("codePostal");
                String telephone = request.getParameter("telephone");
                String mail = request.getParameter("mail");
                String password = request.getParameter("password");

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date d = sf.parse(date);
                String resDate = df.format(d);


                Client newClient = new Client(nom, prenom, 
                            civilite, resDate, telephone, codePostal,
                            mail, password);

                InscriptionClient(newClient);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out = response.getWriter();
                printAnswerInscription(out);
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
    
    public static void printAnswerConnexion(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        jo.addProperty("res","");
        
        JsonObject container = new JsonObject();
        container.add("res",jo);
        out.println(gson.toJson(container));
    }
    
    public static void printAnswerNomClient(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
        if(connectedClient != null){
            jo.addProperty("nom",connectedClient.getNom());
            jo.addProperty("prenom",connectedClient.getPrenom());
        }else{
            jo.addProperty("nom","nom");
            jo.addProperty("prenom","prenom");
        }
        
        JsonObject container = new JsonObject();
        container.add("client",jo);
        
        out.println(gson.toJson(container));
    }
    
    public static void printAnswerHistorique(PrintWriter out, List<Intervention> li){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray ja = new JsonArray();
        for(Intervention i : li){
            JsonObject jo = new JsonObject();
            //JsonObject debut = new JsonObject();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh'h'mm");
            String debutstr = sdf.format(i.getDateDeDebut());
            jo.addProperty("debut",debutstr);
            //nb of ms since jan 1st 1970, GMT
            jo.addProperty("description",i.getDescription());
            jo.addProperty("rapport",i.getRapport());
            
            if(i instanceof Animal){
                jo.addProperty("type","Animal");
                jo.addProperty("animal",((Animal) i).getType());
            }else if(i instanceof Livraison){
                jo.addProperty("type","Livraison");
                jo.addProperty("objet",((Livraison)i).getObjet());
                jo.addProperty("prestataire",((Livraison) i).getPrestataire());
            }else{
                jo.addProperty("type","Incident");
            }
            //pas de propriétés particulières pour indicent = c'est juste une intervention
            
            ja.add(jo);
        }
        
        JsonObject container = new JsonObject();
        container.add("historique",ja);
        out.println(gson.toJson(container));
    }
    
    public static void printAnswerInscription(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        jo.addProperty("Reussie","");
        
        JsonObject container = new JsonObject();
        container.add("inscription: ",jo);
        out.println(gson.toJson(container));
    }

}
