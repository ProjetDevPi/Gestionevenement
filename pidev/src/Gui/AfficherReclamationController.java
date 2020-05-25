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
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.EvenementService;
import services.ReclamationService;
import services.UserSevice;


/**
 * FXML Controller class
 *
 * @author X
 */
public class AfficherReclamationController implements Initializable {

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
     ////////////////////////////MENU///////////////////////
      @FXML
    private JFXButton btnevent;

    @FXML
    private JFXButton btnsponsor;

    @FXML
    private JFXButton btncontrat;

    @FXML
    private JFXButton btnpart;

    @FXML
    private Label labelti;
    
    @FXML
    private Label laala;
 @FXML
    void event(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void participant(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipationback.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void sponsor(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLSponsor.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }
       @FXML
    void contrat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLContrat.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }
    @FXML
    private ProgressBar bar;
       @FXML
    void nomevent(ActionEvent event) {
        int value=10;
        
       

    }
  ////////////////endMenu////////////
    //////////DELETERECLAMATION/
     @FXML
    void deletes(Event event) {
        if (!TableViewReclamation.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Event ");
            alert.setHeaderText("Are you sure you want to delete this event"
                    + TableViewReclamation.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
 ReclamationService ms = new  ReclamationService();
        ObservableList< Reclamation> ll, ttmission;
        ttmission = TableViewReclamation.getItems();
        // ta3tina les lignes selectionnés 
        ll = TableViewReclamation.getSelectionModel().getSelectedItems();

        for ( Reclamation m : ll) {
            ttmission.remove(m);//refresh tableview
            ms.DeleteReclamation(m.getId());
        }
        JOptionPane.showMessageDialog(null, "supprimer");

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un evenement  ");

            Optional<ButtonType> result = alert.showAndWait();
        }

    }
     @FXML
    private TextField filterfield;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
detaill();
        nom.setCellValueFactory(new PropertyValueFactory<>("username"));
      
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
    
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
       
        objetColumn.setCellValueFactory(new PropertyValueFactory<>("sujet"));
 
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        
        eventt.setCellValueFactory(new PropertyValueFactory<>("event"));
     ///////////taswiret el etat
   
             
         ReclamationService rc = new ReclamationService();
        ArrayList arrayList;
   ObservableList<Reclamation> data = FXCollections.observableArrayList();
        
        data = FXCollections.observableArrayList(rc.afficherAll());
     
        TableViewReclamation.setItems(data);
         
///rechercheeeeee**********************
         // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reclamation> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Reclamation.getEvent().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Reclamation.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
                                    
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TableViewReclamation.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TableViewReclamation.setItems(sortedData);

      
       
      
    }        
public static String EditTable = "";
 public static int E_id_selection;
 public static int E_id_use;
    public static String E_nom_selection;
    public static String E_prenom_selection;
    public static String contenu;
    public static String sujet;
    public static String etat;
    public static String status;
     public static String ev;
     //detail
        @FXML
    private JFXTextField namedetail;

    @FXML
    private JFXTextField typedetail;

    @FXML
    private JFXTextField datedetail;

    @FXML
    private JFXTextField capacitydetail;
      private void detaill(){
        TableViewReclamation.setOnMouseClicked((MouseEvent e) -> {
            Reclamation p = TableViewReclamation.getItems().get(TableViewReclamation.getSelectionModel().getSelectedIndex());
           namedetail.setText(p.getSujet());
           typedetail.setText(p.getContenu());
           datedetail.setText(p.getEvent());
           capacitydetail.setText(p.getUsername());
            
  
          
        });
        
        
    } 
    @FXML
    private void ModifierReclamation(ActionEvent event) throws IOException {
        
      
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
       status = TableViewReclamation.getSelectionModel().getSelectedItem().getEtat();
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("Edit") && !status.equals("Traité")) {
            E_id_selection = TableViewReclamation.getSelectionModel().getSelectedItem().getId();
            E_nom_selection = TableViewReclamation.getSelectionModel().getSelectedItem().getUsername();
            E_prenom_selection = TableViewReclamation.getSelectionModel().getSelectedItem().getMail();
            contenu = TableViewReclamation.getSelectionModel().getSelectedItem().getContenu();
            sujet = TableViewReclamation.getSelectionModel().getSelectedItem().getSujet();
             etat = TableViewReclamation.getSelectionModel().getSelectedItem().getEtat();
             ev = TableViewReclamation.getSelectionModel().getSelectedItem().getEvent();
         
 E_id_use = TableViewReclamation.getSelectionModel().getSelectedItem().getId_user();
  Stage stage = new Stage();
                    Parent root;
                   
                        root = FXMLLoader.load(getClass().getResource("AfficherReclamationModifierFXML.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
        } 
        
     
                        else
                        { Notifications n;
     n = Notifications.create()
                    .title("Succes")
                    .text("The claim has already been dealt with.")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showInformation();
                                }
        
           
        }

    

    
}
