/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import entities.FosUser;
import entities.Participant;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.EvenementService;
import services.ParticipantService;
import services.ReclamationService;
import services.UserSevice;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLMyeventController implements Initializable {
     
     @FXML
    private AnchorPane parent;
     @FXML
    private ImageView notif_empty;

    @FXML
    private ImageView notif_1;
      @FXML
    private TableView<Participant> part;
  @FXML
    private TableColumn<?, ?> image2;
 @FXML
    private JFXButton rec;
    @FXML
    private TableColumn<?, ?> event;

    @FXML
    private TableColumn<?, ?> participant;

    @FXML
    private TableColumn<Participant,Integer> nbrplace;
      @FXML
    private TableColumn<Participant,Integer> iduser;

    @FXML
    private JFXTextField namedetail;

    @FXML
    private JFXTextField participantdeatil;

    @FXML
    private JFXTextField datedetail;
  @FXML
    private ImageView imageView;
    @FXML
    private JFXTextField nbrpace;
     @FXML
    private JFXButton btninsert1;
         @FXML
    private JFXButton envoyer;
          public static String EditTable = "";
     public static int E_id_selection;
    public static String E_nom_selection;
    public static String E_prenom_selection;
    public static int E_cap_selection;
    public static Stage MainStage;
@FXML
    private TableColumn<?,?> imageC;
  private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private Image image;
    /////////////////////////////////////Reclamation
     @FXML
    private TableView<Reclamation> TableViewReclamation;
    
    static Reclamation selectionedReclamation;
    @FXML
    private HBox hbox;
   
   
    
  @FXML
    private TableColumn<?, ?> eventt;
    @FXML
    private TableColumn<Reclamation,String> nom;

    @FXML
    private TableColumn<Reclamation,String> emailColumn;

    @FXML
    private TableColumn<?, ?> objetColumn;

    @FXML
    private TableColumn<?, ?> messageColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;
    

FosUser f;
     ObservableList<Reclamation> data = FXCollections.observableArrayList();
     private void showProductImage(String nomm) throws SQLException{
        cnx = MyConnection.getInstance().getCnx();
        st=cnx.createStatement();
        try {
           String req="Select * from evenement";
            
            
       ResultSet     rs ;
       rs=st.executeQuery(req);
     
    
            while(rs.next()) {
                
                if (rs.getString("nom").equals(nomm))
                {                 
                
                    System.out.println(rs.getString("nom"));
                String a=rs.getString("nom_image");
                
                
                
                image = new Image("file:"+a+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                    System.out.println(image);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
        }
      private void detaill(){
          ParticipantService ps=new ParticipantService();
        
        part.setOnMouseClicked((MouseEvent e) -> {
            Participant p = part.getItems().get(part.getSelectionModel().getSelectedIndex());
           namedetail.setText(p.getEvent());
           participantdeatil.setText(p.getType());
           //datedetail.setText(String.valueOf(p.getDateevent()));
           nbrpace.setText(String.valueOf(p.getNbr()));
          
               
  
          
        });
     }
      
    @FXML
    void reclamer(ActionEvent event) throws IOException {
         System.out.println("vghdv");
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("Reclamer")) {
            E_id_selection = part.getSelectionModel().getSelectedItem().getId();
            E_nom_selection = part.getSelectionModel().getSelectedItem().getEvent();
            System.out.println(E_nom_selection );
            E_prenom_selection = part.getSelectionModel().getSelectedItem().getType();
         

        } 
        
        Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
      
        
        
        stage.show();
        
    }
   @FXML
    void event(ActionEvent event) throws IOException {
          
        Parent root = FXMLLoader.load(getClass().getResource("FXMLeventfront.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
      
        
        
        stage.show();
         ((Node) event.getSource()).getScene().getWindow().hide();

    }
     ObservableList<Participant> listP = null;
     ObservableList<Reclamation> listR = null;
     ParticipantService ps = new ParticipantService();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        detaill();
          UserSevice s1=new UserSevice();
          String ss="";
         try {    
             ss= s1.getlogin() ;
         } catch (SQLException ex) {
             Logger.getLogger(FXMLMyeventController.class.getName()).log(Level.SEVERE, null, ex);
         }
         int result = Integer.parseInt(ss);
                
        nbrplace.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("nbr"));
        participant.setCellValueFactory(new PropertyValueFactory<>("type"));//participant une variable dans la classe participant
          event.setCellValueFactory(new PropertyValueFactory<>("event"));
          iduser.setCellValueFactory(new PropertyValueFactory<Participant,Integer>("user_id"));
             imageC.setCellValueFactory(new PropertyValueFactory<>("image")); 
             image2.setCellValueFactory(new PropertyValueFactory<>("photo"));
        try {
            listP = ps.afficher(result);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        part.setItems(listP);
          //////////////////////affichage des reclamation
     nom.setCellValueFactory(new PropertyValueFactory<>("username"));
      
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
    
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
       
        objetColumn.setCellValueFactory(new PropertyValueFactory<>("sujet"));
  eventt.setCellValueFactory(new PropertyValueFactory<>("event"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
     ///////////taswiret el etat
   
             
         ReclamationService rc = new ReclamationService();
        ArrayList arrayList;
   ObservableList<Reclamation> data = FXCollections.observableArrayList();
        
        data = FXCollections.observableArrayList(rc.afficher(result));
     
        TableViewReclamation.setItems(data);
    }
  
}
