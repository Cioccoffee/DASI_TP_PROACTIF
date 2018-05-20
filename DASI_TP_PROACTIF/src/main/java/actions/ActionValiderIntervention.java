/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.servlet.http.HttpServletRequest;
import modele.Employe;
import static service.ServiceAppli.ValiderIntervention;

/**
 *
 * @author alaoui
 */
public class ActionValiderIntervention {
    
    public static boolean execute (HttpServletRequest request, Employe e){
        int heure = Integer.parseInt(request.getParameter("heure"));
        int minute = Integer.parseInt(request.getParameter("minute"));
        String commentaire = request.getParameter("commentaire");
        return ValiderIntervention(e, heure, minute, commentaire);
    } 
    
}
