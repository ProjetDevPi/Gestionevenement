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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
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

import org.controlsfx.control.Notifications;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;

import services.ReclamationService;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author X
 */
public class AfficherReclamationModifierController implements Initializable {

    ObservableList<String> listRoles = FXCollections.observableArrayList("Bon Plan", "Compte", "Deal", "Autre");
    private ComboBox<String> typeReclamationCBX;
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
    private TextField iduser;
    @FXML
    private JFXTimePicker TempsDispoTimePicker;
    @FXML
    private TextField SujetTXFLD;
    @FXML
    private JFXTextArea DescriptionTXFLD;
    @FXML
    private JFXDatePicker DateDispoTimePicker;
    @FXML
    private ImageView screenshotView;
    @FXML
    private ImageView checkMarkImage;
    File selectedFile;
    int selectedBonplanID;
    private String path;
    @FXML
    private TextArea bonPlanText;
    @FXML
    private Label nomImageLabel;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton repondreBTN;
    @FXML
    private ComboBox<String> statusComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserSevice u=new UserSevice();
        String ss = null;
        try {
             ss=u.getlogin();
             System.out.println(ss);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
            
         
            repondreBTN.setDisable(false);
            repondreBTN.setVisible(true);
            ajouter.setDisable(false);
            ajouter.setVisible(true);
            
        
     int idus= AfficherReclamationController.E_id_use;
       
        // E_id_selection
        NomTXFLD.setText(AfficherReclamationController.ev);//nom event
       PrenomTXFLD.setText(AfficherReclamationController.E_nom_selection);///usernme
        EmailTXFLD.setText(AfficherReclamationController.E_prenom_selection);//mail
       
        SujetTXFLD.setText(AfficherReclamationController.sujet);
        DescriptionTXFLD.setText(AfficherReclamationController.contenu);
       
        statusComboBox.getItems().setAll("En attente", "En Traitement", "Traité");
        int id1= AfficherReclamationController.E_id_selection;
           
        TelTXFLD.setText(String.valueOf(id1));//id de la reclamation

iduser.setText(String.valueOf(idus));
      

    }
 
    Notifications n;
    
    @FXML
    private void modififerReclamationGUI(ActionEvent event) throws SQLException, IOException {
       
         String nomm =  NomTXFLD.getText();//nomevent
        String desc = DescriptionTXFLD.getText();
        String sujet=SujetTXFLD.getText();
        String mail=EmailTXFLD.getText();
        String etat=statusComboBox.getValue();
        System.out.println(etat);
       int idd = Integer.parseInt(TelTXFLD.getText());//id de la reeclamation
         int iduserr = Integer.parseInt(iduser.getText());
          String nomuser =  PrenomTXFLD.getText();
        Reclamation p =new Reclamation();
     p.setId(idd);
        p.setUsername(nomm);
        p.setContenu(desc);
        p.setEtat(etat);
        p.setMail(mail);
       p.setSujet(sujet);
         p.setId_user(iduserr);
       
        p.setEvent(nomm);
                 ReclamationService ps = new  ReclamationService();

            ps.Update(p);
            System.out.println(p.getEtat());
            n = Notifications.create()
                    .title("Succes")
                    .text("Reclamation Traité avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showInformation();

        Parent root2 = FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
     stage2.show();
           
        
       

    }

    
public static String EditTable="";
 public static String E_nom_selection;
    public static String E_prenom_selection;
    public static String contenu;
    public static String sujet;
    public static String etat;
    @FXML
    private void RepondreReclamation(ActionEvent event) throws IOException {
           Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
       
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("Traiter")) {
           
            E_nom_selection = NomTXFLD.getText();
            E_prenom_selection = EmailTXFLD.getText();
            
            sujet = SujetTXFLD.getText();
            
         
        } 
        
      Stage stage = new Stage();
                    Parent root;
                   
                        root = FXMLLoader.load(getClass().getResource("RepondreReclamation.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
           
        }

      

}
