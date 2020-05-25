package Gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.MyConnection;




public class UIController implements Initializable {

@FXML
private JFXButton btnconges;
 @FXML
    private JFXButton btnsponsor;
private final ObservableList<PieChart.Data> details= FXCollections.observableArrayList();
private PieChart pieChart;
    
    private ResultSet rs=null,rs1=null;

    private PreparedStatement pst,pst1;
    @FXML
    PieChart piechart3;
    ObservableList<PieChart.Data> piechartdata33;
ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<String> cat=new ArrayList<String>();
private Connection cnx;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
 
 @FXML
    private JFXButton btnevents;

    

    @FXML
    void events(ActionEvent event) {
         try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        cnx = MyConnection.getInstance().getCnx();
    try {
        Piechart33();
    } catch (SQLException ex) {
        Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
    }
        piechart3.setData(piechartdata33);
    }    
    private void Piechart33() throws SQLException{
        piechartdata33=FXCollections.observableArrayList();
    
        
        pst=cnx.prepareStatement("select * from evenement");
           
      
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=cnx.prepareStatement("SELECT count(*) as nbrpart FROM participant WHERE evenement_id='"+rs.getString("id")+"'");
        rs1=pst1.executeQuery();
           
        while(rs1.next())
        {
            int i=Integer.valueOf(rs1.getString("nbrpart"));
            piechartdata33.add(new PieChart.Data(rs.getString("nom"),i));
            np.add(i);
            cat.add(rs.getString("nom"));
        }
        }
   
   
   
       
    }

 @FXML
    void reclamatuin(ActionEvent event) throws IOException {
          Parent AnchorPane = FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
               ((Node) event.getSource()).getScene().getWindow().hide();

    }
    @FXML
    void CONGE(ActionEvent event) throws IOException{
        try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
    
    @FXML
    void sponsor(ActionEvent event) throws IOException  {
         try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLSponsor.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    }

                 
    




    

