/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//            String usermail = "abdelkader.jouini@esprit.tn";
//            String passmail = "SALIHsonia22";
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utils.MailSend;


/**
 * FXML Controller class
 *
 * @author X
 */
public class RepondreReclamationController implements Initializable {

    @FXML
    private TextArea messageTextField;
    @FXML
    private TextField sujetTextField;
    @FXML
    private Button envoyerMessageButton;
    @FXML
    private TextField emailTXFLD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sujetTextField.setText("Reponse Reclamation");
       emailTXFLD.setText(AfficherReclamationController.E_prenom_selection);
        messageTextField.setText("Bonjour Mr/Mme " + AfficherReclamationController.E_nom_selection+ " Merci de nous avoir contactés,");
        
        // TODO
    }    
 
    @FXML
    private void envoyerMessage(ActionEvent event) {
        String to;
       
        if (emailTXFLD.getText().equals(""))
          to = AfficherReclamationController.selectionedReclamation.getMail().trim();
        else
            to=emailTXFLD.getText();
         
            String subject = sujetTextField.getText();
            String message = messageTextField.getText();
           
MailSend m=new MailSend();
           m.sendMail("mayssa.zaouali@esprit.tn",to, subject, message);
            
            Notifications n = Notifications.create()
                    .title("succès")
                    .text("Email envoyé avec succès")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
    }
    
}
