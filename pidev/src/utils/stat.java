/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.BorderLayout;
import static java.awt.Component.TOP_ALIGNMENT;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import utils.MyConnection;

/**
 *
 * @author HP
 */
public class stat {
    private static stat instancia;
    public static stat getInstancia()
    {  
        if (instancia==null)
        {
            instancia=new stat();
        
        }
        return instancia;
    
    }
     private Connection cnx;
     public String col="";
      private Statement st;
    private PreparedStatement pre;

    public stat() {
         cnx = MyConnection.getInstance().getCnx();
    }

    public  void get() throws SQLException, IOException {
     
     
        String req = "select * from evenement";
        
     st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            System.out.println("zaouali");
            DefaultPieDataset dataset=new DefaultPieDataset();
            while(rs.next())
            {
            dataset.setValue(rs.getString("nom"),Integer.parseInt(rs.getString("nbrpart")));
                System.out.println(dataset);
            
            int     ancho=560;
            int alto=380;
           // File f=new File("graphique.png");
            //ChartUtilities.saveChartAsPNG(f, chart,ancho, alto);
            
            
            }
            System.out.println("mesmesmezyena");JFreeChart chart=ChartFactory.createPieChart("Nbr de participation par evenement",dataset,true,true,false);
            PiePlot p=(PiePlot)chart.getPlot();
            //p.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame=new ChartFrame("Pie chart",chart);
            frame.setVisible(true);
            frame.setSize(450,500);
    }
     public  void part() throws SQLException, IOException {
    
      System.out.println("zaouali");
        String req = "SELECT evenement_id,SUM(nbr) FROM participant GROUP BY evenement_id";
         System.out.println("kk");
     st = cnx.createStatement();
         System.out.println("hs");
            ResultSet rs = st.executeQuery(req);
            System.out.println("zaouali");
            DefaultPieDataset dataset=new DefaultPieDataset();
            while(rs.next())
               
            { 
            dataset.setValue(findbynomevent(rs.getInt("evenement_id")),Integer.parseInt(rs.getString("SUM(nbr)")));
               System.out.println(dataset);
            
            int     ancho=560;
            int alto=380;
           // File f=new File("graphique.png");
            //ChartUtilities.saveChartAsPNG(f, chart,ancho, alto);
            
            
            }
            System.out.println("mesmesmezyena");JFreeChart chart=ChartFactory.createPieChart("Nobre de participant par evenement !",dataset,true,true,false);
            PiePlot p=(PiePlot)chart.getPlot();
            //p.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame=new ChartFrame("Pie chart",chart);
            frame.setVisible(true);
            frame.setSize(450,500);
    }
      public String findbynomevent(int user ) throws SQLException{
                  
String req = "SELECT * FROM evenement ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
            
      String  name= res.getString("nom");
                   
            return name;
              }
            }
            return null;
              }/*/
    public  void part() throws SQLException, IOException {
    DefaultCategoryDataset dt=new  DefaultCategoryDataset();
     String req = "select count(nbr),evenement_id from participant groupby evenement_id";
      
     st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            System.out.println("myssamezyeeneet");
            
            while(rs.next())
            {
            dt.setValue(rs.getInt("nbr"),findbynomevent(rs.getInt("evenement_id")),rs.getString("type"));
                System.out.println(dt);
                
            
            int     ancho=560;
            int alto=380;
           // File f=new File("graphique.png");
            //ChartUtilities.saveChartAsPNG(f, chart,ancho, alto);
           
            }
            JFreeChart barchart;
        barchart = ChartFactory.createBarChart("hgsghhsgds","pikettii","mesmessti",dt,PlotOrientation.VERTICAL,false,true,false);
      
            int     ancho=560;
            int alto=380;
            File f=new File("graphique.png");
            ChartUtilities.saveChartAsPNG(f, barchart,ancho, alto);
        
          CategoryPlot bar=barchart.getCategoryPlot();
        //bar.setRangeGridlinePaint(Color.BLUE);
        ChartPanel panel=new ChartPanel(barchart);
        panel.removeAll();
        panel.add(panel,BorderLayout.CENTER);
        panel.validate();
        
        
    }/*/
}
