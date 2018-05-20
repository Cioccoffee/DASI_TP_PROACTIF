/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import modele.Employe;
import static service.ServiceAppli.AuthentificationEmploye;

/**
 *
 * @author Lucie
 */
public class ActionConnecterEmploye {
    
    public static Employe execute(HttpServletRequest request) throws ServletException{
        
        Employe e = null;
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            e = AuthentificationEmploye(login, password);
            
        }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
        }
        
        return e;
    }
}
