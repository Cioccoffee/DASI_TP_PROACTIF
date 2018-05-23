/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.Employe;
import static service.ServiceAppli.ValiderIntervention;

/**
 *
 * @author alaoui
 */
public class ActionValiderIntervention {
    
    public static boolean execute (HttpServletRequest request, HttpSession session){
        Employe connectedEmploye = (Employe)session.getAttribute("employe");
        int heure;
        int minute;
        try{
            heure = Integer.parseInt(request.getParameter("champHeure"));
            minute = Integer.parseInt(request.getParameter("champMinute"));
        }catch(NumberFormatException nfe){
            GregorianCalendar calendar = new GregorianCalendar();
            heure = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE); 
        }
        
        String commentaire = request.getParameter("champCommentaire");
        System.out.println("Execute: "+heure+minute+commentaire);
        return ValiderIntervention(connectedEmploye, heure, minute, commentaire);
    } 
    
}
