/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceUser;

import java.util.ArrayList;








/**
 *
 * @author HP
 */
public class ListEvent extends Form{
     
       
              ServiceEvent serviceTask = new ServiceEvent();
            ArrayList<Evenement> lis = serviceTask.getAllEvent(); //liste des events
            Container cCenter = new Container();
            
    public ListEvent(Form previous) throws ParseException {
        
        
      super(); 
        setTitle("Our Events ");
      
        Toolbar toolBar = getToolbar();
        toolBar.addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
                       
     
          
          try {
              populateScreen(cCenter);
          } catch (ParseException ex) {
              
          }
            
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
       
        populateScreen(cCenter);
        

        

    }
  private void populateScreen(Container cnt) throws ParseException {
       cnt.removeAll();
       cnt.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       cnt.setScrollableY(true);
     
       for(Evenement c : lis) {
           cnt.addComponent(new AccrClient2(c));
       }
       cnt.repaint();
    }
  
    }
 class AccrClient2 extends Accordion {
     
                      Form current;
 public static Evenement ev = new Evenement();
      private Resources theme=UIManager.initFirstTheme("/theme_2");

        Evenement c;
        MultiButton mb;
         Container IMG = new Container();
          Container c2 = new Container();
          
       Container c3 = new Container();
        public AccrClient2(Evenement c) throws ParseException {
            super();
            this.c = c;
            addContent(
                this.c2 = createHeader(c), createDetail(c)
            );
        }
        
        public Evenement getClient() {
            return this.c;
        } 
        
        private Container createHeader(Evenement c) {
       
             
                                        IMG.setPreferredSize(new Dimension(300, 300));
             
                                       EncodedImage placeholder=EncodedImage.createFromImage(theme.getImage("load.gif"),true);
                                       URLImage Urlimg=URLImage.createToStorage(placeholder,"http://localhost/dev/web/imageEvenement/"+ c.getNomimage(),"http://localhost/dev/web/imageEvenement/"+ c.getNomimage());
                                       ImageViewer img=new ImageViewer( Urlimg);
                                       IMG.add(img);
                                       IMG.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                                       img.getParent().revalidate();
                                       Label s = new Label(""+c.getNom());
                                       s.getAllStyles().setFgColor(0x01010D) ;
                                       c2.add(IMG);
                                       c2.add(s);
            
                                    return  c2;
                                 
            
            
        }
        
        private Container createDetail(Evenement c) throws ParseException {
         
  
                     
                      
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
               
                        new Label("Event:        "),
                       new Label("" +c.getNom()),
                        new Label("Description:            "),
                         new Label("" +c.getType())
                     
                       
                    
                )            
            );
            
            Container cButtons = new Container(new GridLayout(1,3));
           // cButtons.addComponent(new Button("Print PDF"));
           EncodedImage partt=EncodedImage.createFromImage(theme.getImage("ticket1.png"),true);
            EncodedImage out=EncodedImage.createFromImage(theme.getImage("out.png"),true);
           Button part=new Button("Partcipate");
           part.setIcon(partt);
           if(c.getNbrpart() !=0)
           { part.setVisible(true);
            cButtons.addComponent(part);
        
               part.addPointerReleasedListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                             ev = c;
                               
                          
                                 new addevent(c).show();
                            

                         }
                     });
           }
           else{ cButtons.addComponent(part);part.setVisible(false);
           Label L=new Label("Pas de place");
          
           L.setIcon(out);
          cButtons.addComponent(L);
           L.getAllStyles().setFgColor(0xE4212A) ;
           
           
           }
            cDetail.addComponent(BorderLayout.SOUTH, cButtons);
            
            return cDetail;
        }
        
      
        
        public boolean isInteger(String s) {
            try { 
                Integer.parseInt(s); 
            } catch(NumberFormatException | NullPointerException e) { 
                return false; 
            }
            // only got here if we didn't return false
            return true;
        }        

      
    
 }   
        
                
  //////////////////scroll/////////////
        

        
        