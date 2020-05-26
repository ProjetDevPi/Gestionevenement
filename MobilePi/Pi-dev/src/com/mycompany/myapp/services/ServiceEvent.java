/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.FosUser;
import com.mycompany.myapp.entities.Participant;
import com.mycompany.myapp.entities.login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceEvent {
    public ArrayList<Evenement> evenement;
    public ArrayList<Participant> part;
    public static ServiceEvent instance=null;
      
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvent() {
         req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    
    public ArrayList<Evenement> parseEvent(String jsonText){
        try {
            evenement=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenement t = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setType(obj.get("type").toString());
                t.setNomimage(obj.get("nomImage").toString());
                float nbr = Float.parseFloat(obj.get("nbrpart").toString());
                t.setNbrpart((int)nbr);
                
                evenement.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return evenement;
    }
    public ArrayList<Evenement> getAllEvent(){
        String url = "http://localhost/dev/web/app_dev.php/all";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenement = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenement;
    }
      

   /////Afficher levent selon le user connecter
    public ArrayList<Participant> getAllMyevent(){
      ServiceUser s1= new ServiceUser();               
 login l =new login();
              l=s1.login();
        String url = "http://localhost/dev/web/app_dev.php/nom/"+l.getId_user();
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                part = parseParticipant(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return part;
    }
          
 public ArrayList<Participant> parseParticipant(String jsonText){
        try {
            part=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
             
            for(Map<String,Object> obj : list){
                
                 Participant p = new Participant();
                  //-----------------------------------CATEGORY----------------------------------------------------
                Map<String, Object> listRecupprod = null;

              Evenement c = new Evenement();
                if (obj.get("evenement") != null) {

                    listRecupprod = (Map<String, Object>) obj.get("evenement");

                    p.setEvenement((int) Float.parseFloat(listRecupprod.get("id").toString()));
                   
                   
                }
             
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                float quantite= Float.parseFloat(obj.get("nbr").toString());
                p.setNbr((int)quantite);
               

                part.add(p);
            }
            
        } catch (IOException ex) {
            
        }
        return part;
    }
 //////taaw bech nee5ou el event eli cheereek fiih paryicipant moiu3aayen
  public Evenement getevent(int id){
        String url = "http://localhost/dev/web/app_dev.php/find/"+id;
        req.setUrl(url);
        req.setPost(false);
             System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenement = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(evenement.get(0));
        return evenement.get(0);
    }
  ////////////////////Supprimrt participation
  public void supprimerpart(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/dev/web/app_dev.php/supprimerparticipant/" + id;
        System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone

    }
//////////////////////bech tab3aath mail
  public void mail(int idparticipant){
      
            ConnectionRequest con = new ConnectionRequest();
                   
                    con.setUrl("http://localhost/dev/web/app_dev.php/mail2/"+idparticipant
                     
                            );


                 

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                         
                 
                    
                          
        


                        }
  
  
}
