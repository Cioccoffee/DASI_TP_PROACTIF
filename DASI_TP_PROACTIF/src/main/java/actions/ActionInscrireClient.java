/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import modele.Client;
import static service.ServiceAppli.InscriptionClient;

/**
 *
 * @author alaoui
 */
public class ActionInscrireClient {
    
    public static void execute(HttpServletRequest request) throws ParseException{
        
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
        String adressePostale = rue + ", " + ville;

        Client newClient = new Client(nom, prenom, 
                    civilite, resDate, telephone, adressePostale,
                    mail, password);

        InscriptionClient(newClient);
        
    }
}
