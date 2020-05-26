/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.MenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceUser;

import java.io.IOException;




/**
 *
 * @author HP
 */
public class addevent  extends Form{
    Container cCenter = new Container();
 private Resources theme=UIManager.initFirstTheme("/theme_2");

   public addevent(Evenement events) {
          
      super(); 
        setTitle("Our Events ");
      
        Toolbar toolBar = getToolbar();
        toolBar.addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
                       
     
          
         
            
        });
        
         toolBar.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_LOGOUT, 6f, e -> {
                new Menu().show();
        });
           
                
        Button btnClose = new Button("Sair");
        btnClose.setIcon(
            FontImage.createMaterial(
                FontImage.MATERIAL_EXIT_TO_APP,
                UIManager.getInstance().getComponentStyle("Button")
            )            
        );
        btnClose.addActionListener
                ((ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    
                    Display.getInstance().exitApplication();
                    
        });
 
       
        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, cCenter);
        addComponent(BorderLayout.SOUTH, btnClose);
       
        Container IMG = new Container();
       TextField tf_age = new TextField("", "Nombre de place à reserver"); 
   Container cDetail= BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
               
                        new Label("Event:        "),
                       new Label("" +events.getNom()),
                        new Label("Description:            "),
                         new Label("" +events.getType()),
                          new Label("Place à réserver: " ),tf_age
                     
                       
                    
                )            
            );
    

   EncodedImage ajouter=EncodedImage.createFromImage(theme.getImage("ins.png"),true);
 
                                        IMG.setPreferredSize(new Dimension(300, 300));
             
                                       EncodedImage placeholder=EncodedImage.createFromImage(theme.getImage("load.gif"),true);
                                       URLImage Urlimg=URLImage.createToStorage(placeholder,"http://localhost/dev/web/imageEvenement/"+ events.getNomimage(),"http://localhost/dev/web/imageEvenement/"+ events.getNomimage());
                                       ImageViewer img=new ImageViewer( Urlimg);
                                       IMG.add(img);
                                       IMG.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                       img.getParent().revalidate();
                                       Label s = new Label(""+events.getNom());
                                       s.getAllStyles().setFgColor(0xf20d0d) ;
                                       cCenter.add(IMG);
                                       cCenter.add(s);
  cCenter.add(cDetail);
   
  Button add = new Button("Ajouter");
       
       add.setIcon(ajouter);
        ServiceUser s1= new ServiceUser();
        //=======================================================================================
        //=====================================TRAITEMENT========================================
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
             
 login l =new login();
              l=s1.login();
             
                System.out.println(l.getId_user());
                System.out.println(events.getId());
                  
                  int nbr = Integer.parseInt(tf_age.getText());
                  System.out.println(nbr);
                  System.out.println(events.getNbrpart());
                  if(events.getNbrpart()>= nbr)
                     
                   {  
                       ConnectionRequest con = new ConnectionRequest();
                       con.setUrl("http://localhost/dev/web/app_dev.php/newpartmobile/"+events.getId()+"/"+l.getId_user()+"?"
                            
                            + "evenement_id="+events.getId()
                            + "&user_id="+l.getId_user()
                            + "&nbr="+tf_age.getText()
                        
                            
                            );


                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {

                            System.out.println("Participation a été ajouté avec succées.");
                            
                            ToastBar.showMessage("Participation ajouté avec succées.",FontImage.MATERIAL_DONE);
                           

                        }
                    });

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                 


                
                        }
                   
                  else {
                      Dialog.show("Désoler", "Il reste que"+events.getNbrpart(), new Command("OK"));
                      System.out.println("mesmesss mezyeenet");}
            }
      
            
        });
         
     
//   cCenter.add(tf_age);
   cCenter.add(add);
  
   }
    Container c2 = new Container();
 
}



    
