package com.mycompany.myapp.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.BrowserComponent;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Participant;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceUser;

import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class ListMyevent extends Form{
   
     ServiceEvent serviceTask = new ServiceEvent();
            ArrayList<Participant> lis = serviceTask.getAllMyevent();
            Container cCenter = new Container();
            
                     
    public ListMyevent(Form previous) throws ParseException {
        
        
      super(); 
        setTitle("My events  ");
       
      
        Toolbar toolBar = getToolbar();
        toolBar.addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
                       

            
          try {
              populateScreen(cCenter);
              
               
              cCenter.setScrollableY(true);
                  
              cCenter.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
            cCenter.getUnselectedStyle().setBackgroundGradientEndColor(0xFFBCCA);
          cCenter.getUnselectedStyle().setBackgroundGradientStartColor(0xFFBCCA);
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
         serviceTask.getAllMyevent();
                    
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
     
       for(Participant c : lis) {
           cnt.addComponent(new AccrClient4(c));
           cnt.setScrollableY(true);
       }
       cnt.repaint();
    }
  
    }
 class AccrClient4 extends Accordion {
     
       Form current;
      ServiceEvent serviceTask = new ServiceEvent();
      private Resources theme=UIManager.initFirstTheme("/theme_2");

        Participant cp;
    
    
    
        Container cCenter = new Container();
        MultiButton mb;
         Container IMG = new Container();
          Container c2 = new Container();
          int test=0;
            TextField tfquantitecmd;
          
       Container c3 = new Container();
        public AccrClient4(Participant cp) throws ParseException {
            super();
            this.cp = cp;
            addContent(
                this.c2 = createHeader(cp), createDetail(cp)
            );
        }
        
        
       ServiceEvent s1= new ServiceEvent();

Evenement e = new Evenement();




private Container createHeader(Participant p) {
            e=s1.getevent(p.getEvenement()) ;
             System.out.println(e);
    ServiceUser s2 = new ServiceUser();
           login l = new login();
ServiceEvent s1= new ServiceEvent ();
l=s2.login(); //traja3l

       
  IMG.setPreferredSize(new Dimension(300, 300));
             
 EncodedImage placeholder=EncodedImage.createFromImage(theme.getImage("load.gif"),true);
                                
                //System.out.println(c.getNom_image());
                                       URLImage Urlimg=URLImage.createToStorage(placeholder,"http://localhost/dev/web/imageEvenement/"+ e.getNomimage(),"http://localhost/dev/web/imageEvenement/"+ e.getNomimage());
   
                                       ImageViewer img=new ImageViewer( Urlimg);
                                       IMG.add(img);
                                       IMG.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            img.getParent().revalidate();
               
               
              
        
            //c2.add(tfrecherche);
          

           
           Label s = new Label("Participation de :"+l.getUsername());
           
                s.getAllStyles().setFgColor(0x041105) ;
             

           
           c2.add(IMG);
          c2.add(s);
        
           return  c2;
          //return mbt;
            
            
        }


////////////
  private Container createDetail(Participant p) throws ParseException {
         
        
                //e=s1.getevent(cp.getEvenement());   
                
                TextField modpart= new TextField("","Nouveau place");
                modpart.setVisible(true);
       
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
               
                        new Label("Event:        "),
                       new Label("" +e.getNom()),
                        new Label("Description:            "),
                         new Label("" +e.getType()),
                       
                        new Label("Place reserver:            "),
                        new Label("" + cp.getNbr()),
                      new Label("Modifier place:"),
                       modpart
                         
                        
            )
                    
                        
                
            );
              TextField tf_nbr = new TextField("", "Nombre de place à reserver");
              
               Container cButtons = new Container(new GridLayout(1,3));
                EncodedImage mod=EncodedImage.createFromImage(theme.getImage("editnote_pencil_edi_6175.png"),true);
                 EncodedImage supp=EncodedImage.createFromImage(theme.getImage("supprimer2.png"),true);
                  EncodedImage pdff=EncodedImage.createFromImage(theme.getImage("pdf.png"),true);
           // cButtons.addComponent(new Button("Print PDF"));
          Button edit=new Button("Modifier");
           Button part=new Button("Supprimer");
            Button pdf=new Button("print");
          edit.setIcon(mod);
          part.setIcon(supp);
          pdf.setIcon(pdff);
            cButtons.addComponent(edit);
            
            
            cButtons.addComponent(part);
        cButtons.addComponent(pdf);
         pdf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
           Display.getInstance().setProperty("WebLoadingHidden", "true");

  
  BrowserComponent  browser = new BrowserComponent();

 
        browser.setURL("http://localhost/dev/web/app_dev.php/PDFmobile/"+cp.getId());
           Display.getInstance().execute(browser.getURL());


                    }});
               part.addPointerReleasedListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                             s1.supprimerpart(cp.getId());
                             removeAll();
                     Dialog.show("Success","Participation AnnulÃ©e",new Command("OK"));
                    serviceTask.getAllMyevent();
                            

                         }
                     });
           
            edit.addActionListener(new ActionListener() {
                 
            @Override
            public void actionPerformed(ActionEvent evt) {


                    modpart.setVisible(true);
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/dev/web/app_dev.php/updatepart?"
                            +"id="+cp.getId()
                            +"&nbr="+modpart.getText()
                        
                    );
                    System.out.println(con.getUrl());
                     
             con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                        serviceTask.getAllMyevent();
                        ToastBar.showMessage("Participation modifiÃ©e avec succÃ©es.",FontImage.MATERIAL_DONE);
                            
                        s1.mail(cp.getId());
                                
                                
                                
                                //serviceTask.consultercommande();
                            
                         

                        
                        }
                    });
             
           
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    
               }

            
                });
                 
                        cCenter.add(tf_nbr);
            cDetail.addComponent(BorderLayout.SOUTH, cButtons);
            return cDetail;
  }


        public Participant getClient() {
            return this.cp;
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