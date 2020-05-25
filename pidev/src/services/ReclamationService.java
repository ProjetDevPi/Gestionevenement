package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.Evenement;
import entities.FosUser;
import entities.Reclamation;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.UserSevice;
import utils.MyConnection;

/**
 *
 * @author HP
 */
public class ReclamationService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public ReclamationService() {
         cnx = MyConnection.getInstance().getCnx();
    }
     public void ajouterReclamarion(Reclamation r) throws SQLException {
              UserSevice s1=new UserSevice();
                String username= s1.getloginmail();
                 String mail= s1.getloginusername();
                 int id=s1.getloginint();
               /*/  EvenementService es=new EvenementService();
                 String eventname=es.getloginmail();/*/
        if (id!=0 && mail!=null && username!=null)
        {
            //Reclamation r=new Reclamation() ;
                try { 
           String e=s1.getloginmail();
           String em=s1.getloginusername();
           int id_user=s1.getloginint();
         //String event=es.getloginmail();
            String req = "INSERT INTO `reclamationevent`(`contenu`,`sujet`,`etat`,`id_user`,`username`,`mail`,`event`) "
                    + "VALUES ('"+r.getContenu()+"','"+r.getSujet()+"','"+r.getEtat()+"','"+id_user+"','"+em+"','"+e+"','"+r.getEvent()+"') ";
            
             
            
          st = cnx.createStatement();

            st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            }
       public ArrayList<Reclamation> afficherAll() {

        ArrayList<Reclamation> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM reclamationevent";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               Reclamation p = new Reclamation();

               
                System.out.println(res.getString("etat"));
if(res.getString("etat").equals("Traité"))
{
 Image image = new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\traite.png",70, 70, true, true);

ImageView photo= new ImageView(image);
 p.setPhoto(photo);
}
else if(res.getString("etat").equals("En traitement"))
{
Image image = new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enTraitement.png",70, 70, true, true);

ImageView photo= new ImageView(image);
 p.setPhoto(photo);
}
else        
{
Image image = new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png",70, 70, true, true);

ImageView photo= new ImageView(image);
 p.setPhoto(photo);}           
                p.setId(res.getInt("id"));
                p.setContenu(res.getString("contenu"));
                p.setEtat(res.getString("etat"));
               p.setId_user(res.getInt("id_user"));
               p.setSujet(res.getString("sujet"));
               p.setMail(res.getString("mail"));
               p.setEvent(res.getString("event"));
                p.setUsername(res.getString("username"));
                //base attention
                
                System.out.println(p.toString());

                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
        public ArrayList<Reclamation> afficher(int user) {

        ArrayList<Reclamation> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM reclamation";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               Reclamation p = new Reclamation();

               if (res.getInt("id_user")==user){

                System.out.println(res.getString("etat"));
if(res.getString("etat").equals("Traité"))
{
 Image image = new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\traite.png",70, 70, true, true);

ImageView photo= new ImageView(image);
 p.setPhoto(photo);
}
else if(res.getString("etat").equals("En traitement"))
{
Image image = new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enTraitement.png",70, 70, true, true);

ImageView photo= new ImageView(image);
 p.setPhoto(photo);
}
else        
{
Image image = new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png",70, 70, true, true);

ImageView photo= new ImageView(image);
 p.setPhoto(photo);}           
                p.setId(res.getInt("id"));
                p.setContenu(res.getString("contenu"));
                p.setEtat(res.getString("etat"));
               p.setId_user(res.getInt("id_user"));
               p.setSujet(res.getString("sujet"));
               p.setMail(res.getString("mail"));
               p.setEvent(res.getString("event"));
                p.setUsername(res.getString("username"));
                //base attention
                
                System.out.println(p.toString());

                listP.add(p);
            }
            
            System.out.println(listP);

        } 
        }catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
       
         public void Update(Reclamation P) throws SQLException {
 
                    pre = cnx.prepareStatement("update reclamationevent set contenu=?,sujet=?,etat=?,id_user=?,username=?,mail=?,event=? where id=?");

                    pre.setString(1, P.getContenu());
                    pre.setString(2, P.getSujet());
                     pre.setString(3, P.getEtat());
                   pre.setString(5, P.getUsername());
                    pre.setString(6, P.getMail());
                      pre.setInt(4, P.getId_user());
                       pre.setString(7, P.getEvent());
                    pre.setInt(8, P.getId());
                    
                   
                    pre.executeUpdate();
   
       
        }
         public void DeleteReclamation(int id) {
        try {
            String sql = "delete from reclamationevent WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Claim Done!");
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
             
}
