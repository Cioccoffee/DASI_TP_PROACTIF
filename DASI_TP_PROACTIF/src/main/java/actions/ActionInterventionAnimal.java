/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import modele.Animal;
import modele.Client;
import modele.Employe;
import service.ServiceAppli;

/**
 *
 * @author Lucie
 */
public class ActionInterventionAnimal {
    
    public static Employe execute(HttpServletRequest request, Client c) throws ServletException{
        
        Employe e = null;
        try{
            String animal = request.getParameter("animal");
            String description = request.getParameter("description");
            
            Animal a = new Animal(c,description,animal);
            e = ServiceAppli.DemandeIntervention(a);
        }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
        }
        
        return e;
    }
}
