/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import entities.Reclamation;
import services.UserSevice;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.controlsfx.control.Notifications;
import services.ReclamationService;



/**
 * FXML Controller class
 *
 * @author X
 */
public class AjouterReclamationController implements Initializable {

    private String path;

   
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton image;
    @FXML
    private TextField NomTXFLD;
    @FXML
    private TextField PrenomTXFLD;
    @FXML
    private TextField EmailTXFLD;
    @FXML
    private TextField TelTXFLD;

    @FXML
    private TextField SujetTXFLD;
    @FXML
    private JFXTextArea DescriptionTXFLD;
   
   
   
    Notifications n;
    String erreur;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView telCheckMark;
    @FXML
    private ImageView prenomCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML

    java.sql.Timestamp timestamp = null;
    
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
UserSevice s1=new UserSevice();
       int userconnecte=0;
        try {
            userconnecte = s1.getloginint();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ID du user connecté interface ajouter reclamation =" +userconnecte);
String username="";
        try {
            username = s1.getloginusername();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         String mail="";
        try {
           
            mail=s1.getloginmail();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (userconnecte != 0) {
            NomTXFLD.setText(username);
          
            EmailTXFLD.setText(mail);

            NomTXFLD.setDisable(true);
           PrenomTXFLD.setText(FXMLMyeventController.E_nom_selection);
            EmailTXFLD.setDisable(true);
            //TelTXFLD.setDisable(true);
        }

       

        
    }
    
    
    
  


    
    @FXML
    private void ajouterReclamationGUI(ActionEvent event) throws SQLException {

        Reclamation r;
UserSevice s1=new UserSevice();
    
        // UserSevice s1=new UserSevice();
       String ss= s1.getlogin() ;
       String username= NomTXFLD.getText();
       String mail= EmailTXFLD.getText();
       String ev=PrenomTXFLD.getText();
       int userconnecte = Integer.parseInt(ss);
       String sujet=SujetTXFLD.getText();
       String des =DescriptionTXFLD.getText();
               System.out.println(sujet);
       Reclamation rec=new Reclamation();
        if (  userconnecte != 0  || userconnecte == 0 && testSaisie()) //si un memebre ne test pas les donnes , sinon fait un test de siaisie
        {

            if (userconnecte != 0) {
               rec.setId_user(userconnecte);
               rec.setMail(mail);
               rec.setUsername(username);
               rec.setContenu(des);
               rec.setSujet(sujet);
               rec.setEvent(ev);
               rec.setEtat("Non traiter");
            } 
            ReclamationService ms = new ReclamationService();

            ms.ajouterReclamarion(rec);
            System.out.println("ajouté avec succes");
            n = Notifications.create()
                    .title("Succes")
                    .text("Reclamation envoyé avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            n.showInformation();

            
            
        }

    }

    private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
       
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }

        

        return testMail() && testTel()   && testNom() && testPrenom();
    }

   
    @FXML
    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (EmailTXFLD.getText() == null) {
            return false;
        }

        if (pat.matcher(EmailTXFLD.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
        }
        return true;

    }

//   
    @FXML
    private Boolean testTel() {
        if (TelTXFLD.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < TelTXFLD.getText().trim().length(); i++) {
                char ch = TelTXFLD.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                telCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
                return true;
            } else {
                telCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (TelTXFLD.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            telCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
            return false;
        }

        return true;

    }

   

    @FXML
    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < NomTXFLD.getText().trim().length(); i++) {
            char ch = NomTXFLD.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && NomTXFLD.getText().trim().length() >= 3) {
            nomCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    @FXML
    private Boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < PrenomTXFLD.getText().trim().length(); i++) {
            char ch = PrenomTXFLD.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && PrenomTXFLD.getText().trim().length() >= 3) {
            prenomCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
            return true;
        } else {
            prenomCheckMark.setImage(new Image("file:C:\\Users\\HP\\Desktop\\gestioneventjava\\pidev\\enAttente.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    @FXML
    private void recaptcha(MouseEvent event) {


    }

  
    
    
   

}
