/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domain.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmMainStudent;
import view.form.util.FormMode;

/**
 *
 * @author Vladimir
 */
public class MainStudentController {
    private FrmMainStudent frmStudent;
    public MainStudentController() {
        
    }

    public MainStudentController(FrmMainStudent frmMainStudent) {
       frmStudent = frmMainStudent;
    }
    
    
    

    public void openForm() {
        addPic();
        populateForm();
        
        
        addActionListeners();
        
        
        
    }

    private void addActionListeners(){
        
        frmStudent.addActionListerAddNewPlan(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openFormAddNewPlan(ae);
            }

            private void openFormAddNewPlan(ActionEvent ae) {
                
               
                       
                MainCordinator.getInstance().openPlanForm(FormMode.FORM_ADD);

                
            }
          
           
            
            
            
        });
        
        frmStudent.addActionListenerAbout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                JOptionPane.showMessageDialog(null, "This application is made for students to keep track of their university projects");
            }
        });
    
        frmStudent.addActionListenerLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                MainCordinator.getInstance().addParam(Constants.CURRENT_STUDENT, null);
                MainCordinator.getInstance().openLoginForm();
                frmStudent.dispose();
                
                
                
            }
        });
        
        
        
        frmStudent.addActionListenerView(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openViewForm(ae);
            }

            private void openViewForm(ActionEvent ae) {
                
                MainCordinator.getInstance().openViewPlans();
                
                
                
                
            }
            
            
            
            
            
            
            
            
        });
        
    
    
    
    }

    private void populateForm() {
        frmStudent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmStudent.setLocationRelativeTo(null);
        Student s = (Student) MainCordinator.getInstance().getParam(Constants.CURRENT_STUDENT);
        //frmStudent.setTitle(MainCordinator.getInstance().getParam("Welcome " + Constants.CURRENT_STUDENT).toString());
        //System.out.println(MainCordinator.getInstance().getParam("Welcome " + Constants.CURRENT_STUDENT).toString()); 
        frmStudent.setTitle("Welcome " + s.toString());
        frmStudent.setVisible(true);
    }

    private void addPic() {
        
        
        
        
    }
 
    
    
    
}
