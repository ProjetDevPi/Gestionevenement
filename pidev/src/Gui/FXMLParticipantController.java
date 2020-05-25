/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Contrat;
import entities.Evenement;
import entities.Participant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.ContratService;
import services.EvenementService;
import services.NotifService;
import services.ParticipantService;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLParticipantController implements Initializable {

     @FXML
    private AnchorPane parent;


    @FXML
    private TextField id;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXTextField eventnam;

    @FXML
    private JFXTextField eventid;
      @FXML
    private JFXTextField type;

    @FXML
    private JFXTextField eventname;

    @FXML
    private JFXButton btnparticiper;

  public static String nomevent="";
  Notifications n;
    @FXML
    void participer(Event event) throws SQLException {
       
        int nb=Integer.valueOf(id.getText());//le nbr de place reserver par le user  
        int ide=Integer.valueOf(eventid.getText());//recuperer leevent
        //Recuperation du user connecte
       UserSevice s1=new UserSevice();
       String ss= s1.getlogin() ;                 //prendre l id du user connecté
       int result = Integer.parseInt(ss);			
       System.out.println(ss);
       String notif=s1.getnotification();
        Participant P = new Participant();
       
           EvenementService es=new EvenementService();   
           Evenement e=es.Eventfindbyid(ide);//retourne levent qui a lid passer en parametre
           
           System.out.println("mesmes");
           System.out.println(ide);
           if(e.getNbrpart()<nb){
             //JOptionPane.showMessageDialog(null, "Erreur! Quantite de la commande dépasse le stock");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Add Participation ");
            alert.setHeaderText("Il reste que  :  "
                    + e.getNbrpart() + "?");
               Optional<ButtonType> resul = alert.showAndWait();
           }
           else{
        int neweventcapacity= e.getNbrpart()-nb;
        System.out.println(e.getNbrpart());
   
         P.setEvenement_id(ide);
         P.setUser_id(result);
          P.setNbr(nb);
          //System.out.println(nb);
          es.edit(neweventcapacity,e.getId());
          //System.out.println(neweventcapacity);
   n = Notifications.create()
                    .title("Succes")
                    .text("Participation added!!")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            n.showInformation();
 ParticipantService ps = new ParticipantService();
ps.addParticipant(P);
          NotifService s3=new NotifService();
           
 	s3.ajouter_notif();
           
                    Notifications notificationBuilder=Notifications.create()
                   .title("part")
                   .text(FXMLEvenementController.E_nom_selection)
                   .graphic(null)
                   .hideAfter(Duration.seconds(5))
                   .position(Pos.CENTER)
                   .onAction(new EventHandler<ActionEvent>()
                   {
                   @Override
                   public void handle(ActionEvent event)
                   { System.out.println("Clicked on notification"); }
                   }
                   
                             );
              
           
           
           
           }

 

    }
    
     @FXML
    void events(ActionEvent event) throws IOException {
  Stage stage = new Stage();

                                ((Node) event.getSource()).getScene().getWindow().hide();
        
                                Parent root = FXMLLoader.load(getClass().getResource("FXMLeventfront.fxml"));
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                 ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void myevent(ActionEvent event) throws IOException {
          Stage stage = new Stage();

                                ((Node) event.getSource()).getScene().getWindow().hide();
        
                                Parent root = FXMLLoader.load(getClass().getResource("FXMLMyevent.fxml"));
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                 ((Node) event.getSource()).getScene().getWindow().hide();

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int id1= FXMLEvenementController.E_id_selection;
 eventid.setText(String.valueOf(id1));
          eventnam.setText(FXMLEvenementController.E_nom_selection);
          type.setText(FXMLEvenementController.E_prenom_selection);
    }    
    
}
