/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domain.Project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmMainAdmin;
import view.form.util.FormMode;

/**
 *
 * @author Vladimir
 */
public class MainAdminController {
    FrmMainAdmin frmMainAdmin;

    public MainAdminController(FrmMainAdmin frmMainAdmin) {
        this.frmMainAdmin = frmMainAdmin;
    }

    public void openForm() {
       
        populateForm();
        frmMainAdmin.setVisible(true);
        
    }

    private void populateForm() {
         frmMainAdmin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
         frmMainAdmin.setLocationRelativeTo(null);
         frmMainAdmin.setVisible(true);
         frmMainAdmin.setTitle("Welcome");
         addActionListeners();
    }

    private void addActionListeners() {
       
        frmMainAdmin.addActionListenerView(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                MainCordinator.getInstance().openViewSubjects();
            }
        });
        
        frmMainAdmin.addActionListenerAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<Project> removedProjects = new ArrayList<>();
                
                MainCordinator.getInstance().addParam(Constants.REMOVED_PROJECTS, removedProjects);
                MainCordinator.getInstance().openSubjectForm(FormMode.FORM_ADD);
            }
        });
        
        frmMainAdmin.addActionListenerLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                MainCordinator.getInstance().addParam(Constants.CURRENT_ADMIN, null);
                MainCordinator.getInstance().openLoginForm();
                frmMainAdmin.dispose();
                
                
                
            }
        });
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
