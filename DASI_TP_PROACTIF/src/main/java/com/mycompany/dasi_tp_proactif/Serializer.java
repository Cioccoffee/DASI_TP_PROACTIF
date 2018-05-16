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
import static com.mycompany.dasi_tp_proactif.ActionServlet.connectedClient;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import modele.Animal;
import modele.Intervention;
import modele.Livraison;

/**
 *
 * @author Lucie
 */
public class Serializer {
    
    //InscriptionCLient
    
    public static void printAnswerInscription(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        jo.addProperty("Reussie","");
        
        JsonObject container = new JsonObject();
        container.add("inscription: ",jo);
        out.println(gson.toJson(container));
    }
    
    //Connexion - Déconnexion
    
    public static void printAnswerConnexion(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        jo.addProperty("res","");
        
        JsonObject container = new JsonObject();
        container.add("res",jo);
        out.println(gson.toJson(container));
    }
    
    //MenuClient
    
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
    
}
