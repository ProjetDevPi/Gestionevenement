/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Gui.FXMLEvenementController;
import Gui.FXMLParticipantController;
import entities.Notif;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author HP
 */
public class NotifService {
    
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public NotifService() {
         cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouter_notif() throws SQLException
{    
    
    
     UserSevice s1=new UserSevice();
    
    String last=FXMLEvenementController.E_nom_selection;
        String username= s1.getloginusername();
     try {
            
            
            String req3 = "INSERT INTO `notif`(`nom_user`,`nom_event`) "
                    + "VALUES ('"+username+"','"+last+"') ";
            
         
             st = cnx.createStatement();
             st.executeUpdate(req3);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
 
}

    public void deletenotif() {
        try {
         
 
            
            String req = "DELETE FROM `notif` ";
          st = cnx.createStatement();

            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
public List<Notif> afficherNotif() {

        List<Notif> listC = new ArrayList<>();

        try {

            String req = "SELECT *  FROM notif";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                
                {
                    
                Notif p = new Notif();
                p.setId(Integer.parseInt(res.getString("id")));
               p.setNom_user(res.getString("nom_user"));
                p.setNom_event(res.getString("nom_event"));
              
                
             
                listC.add(p);
            }
            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
   
}
