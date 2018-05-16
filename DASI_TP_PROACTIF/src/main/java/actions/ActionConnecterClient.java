/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import modele.Client;
import static service.ServiceAppli.AuthentificationClient;

/**
 *
 * @author Lucie
 */
public class ActionConnecterClient {
    
    public static Client execute(HttpServletRequest request) throws ServletException{
        
        Client c = null;
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            c = AuthentificationClient(login, password);
            
        }catch(Exception ex){
                    throw new ServletException("Data access problem",ex);
        }
        
        return c;
    }
    
}
