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
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import modele.Animal;
import modele.Client;
import modele.Employe;
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
        JsonObject container = new JsonObject();
        container.add("inscription: ",jo);
        out.println(gson.toJson(container));
    }
    
    //Menu Employe
    
    public static void printAnswerNomEmploye(PrintWriter out, HttpSession session){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
        Employe connectedEmploye = (Employe)session.getAttribute("employe");
        if(connectedEmploye != null){
            jo.addProperty("nom",connectedEmploye.getNom());
            jo.addProperty("prenom",connectedEmploye.getPrenom());
        }else{
            jo.addProperty("nom","nom");
            jo.addProperty("prenom","prenom");
        }
        
        JsonObject container = new JsonObject();
        container.add("employe",jo);
        
        out.println(gson.toJson(container));
    }
    
    public static void printAnswerAdresse(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
//        if(connectedClient != null){
//            //jo.addProperty("adresse",.getAdresse());
//        }else{
            jo.addProperty("adresse","adresse");
        //}
        out.println(gson.toJson(jo));
    }
    
    public static void printAnswerDemande(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
//        if(connectedClient != null){
//            //jo.addProperty("adresse",.getAdresse());
//        }else{
            jo.addProperty("adresse","adresse");
        //}
        out.println(gson.toJson(jo));
    }
    
    public static void printAnswerClient(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
//        if(connectedClient != null){
//            //jo.addProperty("adresse",.getAdresse());
//        }else{
            jo.addProperty("adresse","adresse");
        //}
        out.println(gson.toJson(jo));
    }
    
    public static void printAnswerType(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
//        if(connectedClient != null){
//            //jo.addProperty("adresse",.getAdresse());
//        }else{
            jo.addProperty("adresse","adresse");
        //}
        out.println(gson.toJson(jo));
    }
    
    public static void printAnswerAnimal(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
//        if(connectedClient != null){
//            //jo.addProperty("adresse",.getAdresse());
//        }else{
            jo.addProperty("adresse","adresse");
        //}
        out.println(gson.toJson(jo));
    }
    
    public static void printAnswerDescription(PrintWriter out){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
//        if(connectedClient != null){
//            //jo.addProperty("adresse",.getAdresse());
//        }else{
            jo.addProperty("adresse","adresse");
        //}
        out.println(gson.toJson(jo));
    }
    
    public static void printAnswerValiderIntervention(PrintWriter out, 
            boolean valider){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
        if(valider == true){
            jo.addProperty("Intervention","Validée");
        }else{
            jo.addProperty("Intervention","Non validée");
        }
        out.println(gson.toJson(jo));
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
    
    public static void printAnswerNomClient(PrintWriter out, HttpSession session){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jo = new JsonObject();
        Client connectedClient = (Client)session.getAttribute("client");
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
        List<Intervention> liTriee = triInterventions(li);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray ja = new JsonArray();
        for(Intervention i : liTriee){
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
    
    //dit si l'intervention a bien été attribuée
    public static void printAnswerIntervention(PrintWriter out, Employe e){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jo = new JsonObject();
        if(e==null){
            jo.addProperty("success","false");
        }else{
            jo.addProperty("success","true");
        }
        
        JsonObject container = new JsonObject();
        container.add("res",jo);
        out.println(gson.toJson(container));
    }
    
    public static List<Intervention> triInterventions(List<Intervention> li){
        List<Intervention> liTriee = new LinkedList<Intervention>();
        while(!li.isEmpty()){
            long max = li.get(0).getDateDeDebut().getTime();
            //car insertion en queue d'après mes tests
            int index = 0;
            for(int i = 1; i < li.size(); i++){
                if(max < li.get(i).getDateDeDebut().getTime()){
                    max = li.get(i).getDateDeDebut().getTime();
                    index = i;
                }
            }
            liTriee.add(li.get(index));
            li.remove(index);
        }
        return liTriee;
    }
}
