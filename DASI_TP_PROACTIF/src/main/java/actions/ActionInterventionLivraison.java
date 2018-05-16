/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import modele.Livraison;
import modele.Client;
import modele.Employe;
import service.ServiceAppli;

/**
 *
 * @author Lucie
 */
public class ActionInterventionLivraison {
    
    public static Employe execute(HttpServletRequest request, Client c) throws ServletException{
        
        Employe e = null;
        try{
            String prestataire = request.getParameter("prestataire");
            String objet = request.getParameter("objet");
            String description = request.getParameter("description");
            
            Livraison l = new Livraison(c,description,objet,prestataire);
            e = ServiceAppli.DemandeIntervention(l);
        }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
        }
        
        return e;
    }
}
