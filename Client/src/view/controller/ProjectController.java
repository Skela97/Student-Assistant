/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import domain.Project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmProject;
import view.form.FrmSubject;
import view.form.util.FormMode;
import view.models.ProjectModel;
import view.models.SubjectModel;

/**
 *
 * @author Vladimir
 */
public class ProjectController {
    
    private FrmProject frmProject;

    public ProjectController() {
    
    
    
    }

    public ProjectController(FrmProject frmProject) {
        this.frmProject = frmProject;
    }
    
    
    

    public void openForm(FormMode fm) {
        populateForm();
        addActionListeners();
        setupForm(fm);
    }

    private void populateForm() {
        frmProject.setLocationRelativeTo(null);
        frmProject.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
         frmProject.setVisible(true);
         frmProject.setTitle("Working with project");
       
    }

    private void setupForm(FormMode fm) {
        switch(fm){
        case FORM_ADD: 
            frmProject.getTxtDeadline().setEnabled(true);
            frmProject.getTxtDescription().setEnabled(true);
            frmProject.getTxtMax().setEnabled(true);
            frmProject.getTxtName().setEnabled(true);
            
            frmProject.getBtnCancel().setEnabled(true);
            frmProject.getBtnEdit().setEnabled(false);
            frmProject.getBtnEnable().setEnabled(false);
            frmProject.getBtnSave().setEnabled(true);
            break;
        
        case FORM_VIEW:
             frmProject.getTxtDeadline().setEnabled(false);
            frmProject.getTxtDescription().setEnabled(false);
            frmProject.getTxtMax().setEnabled(false);
            frmProject.getTxtName().setEnabled(false);
            
            frmProject.getBtnCancel().setEnabled(true);
            frmProject.getBtnEdit().setEnabled(false);
            frmProject.getBtnEnable().setEnabled(true);
            frmProject.getBtnSave().setEnabled(false);
            
            Project p = (Project) MainCordinator.getInstance().getParam(Constants.CURRENT_PROJECT);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            frmProject.getTxtDeadline().setText(sdf.format(p.getDeadline()));
            frmProject.getTxtDescription().setText(p.getDescription());
            frmProject.getTxtMax().setText(String.valueOf(p.getMaxPoints()));
            frmProject.getTxtName().setText(p.getName());
            
            break;
            
        case FORM_EDIT:
            
            frmProject.getTxtDeadline().setEnabled(true);
            frmProject.getTxtDescription().setEnabled(true);
            frmProject.getTxtMax().setEnabled(true);
            frmProject.getTxtName().setEnabled(true);
            
            frmProject.getBtnCancel().setEnabled(true);
            frmProject.getBtnEdit().setEnabled(true);
            frmProject.getBtnEnable().setEnabled(false);
            frmProject.getBtnSave().setEnabled(false);
            
            
        
        }
    }
    
    public void addActionListeners(){
    
    
        frmProject.addActionListenerCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frmProject.dispose();
            }
        });
        
        frmProject.addActionListenerSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try {
                    if(frmProject.getTxtDeadline().getText().isEmpty() || frmProject.getTxtDescription().getText().isEmpty() || frmProject.getTxtMax().getText().isEmpty() || frmProject.getTxtName().getText().isEmpty()){
                        
                        JOptionPane.showMessageDialog(null, "You have to fill all fields");
                        return;
                    }
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    
                    Project p = new Project();
                    
                    p.setDeadline(sdf.parse(frmProject.getTxtDeadline().getText()));
                    p.setDescription(frmProject.getTxtDescription().getText());
                    p.setMaxPoints(Integer.parseInt(frmProject.getTxtMax().getText()));
                    p.setName(frmProject.getTxtName().getText());
                   
                    ProjectModel pm = (ProjectModel) MainCordinator.getInstance().getParam(Constants.CURRENT_PROJECT_MODEL);
                    pm.add(p);
                    JOptionPane.showMessageDialog(null, "Project saved!");
                    
                    frmProject.getTxtDeadline().setText("");
                    frmProject.getTxtDescription().setText("");
                    frmProject.getTxtMax().setText("");
                    frmProject.getTxtName().setText("");
                            
                    
                    
                   
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Insert correct form of date / correct form of max points!");
                    return;
                }
                
                
                
                
                
            }
        });
        frmProject.addActionListenerEnable(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setupForm(FormMode.FORM_EDIT);
            }
        });
    
        
        frmProject.addActionListenerEdit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                saveProject();
            }

            private void saveProject() {
                  try {
                    if(frmProject.getTxtDeadline().getText().isEmpty() || frmProject.getTxtDescription().getText().isEmpty() || frmProject.getTxtMax().getText().isEmpty() || frmProject.getTxtName().getText().isEmpty()){
                        
                        JOptionPane.showMessageDialog(null, "You have to fill all fields");
                        return;
                    }
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    
                    Project p = (Project) MainCordinator.getInstance().getParam(Constants.CURRENT_PROJECT);
                    
                    p.setDeadline(sdf.parse(frmProject.getTxtDeadline().getText()));
                    p.setDescription(frmProject.getTxtDescription().getText());
                    p.setMaxPoints(Integer.parseInt(frmProject.getTxtMax().getText()));
                    p.setName(frmProject.getTxtName().getText());
                   
                    ProjectModel pm = (ProjectModel) MainCordinator.getInstance().getParam(Constants.CURRENT_PROJECT_MODEL);
                    int row = (int) MainCordinator.getInstance().getParam(Constants.CURRENT_PROJECT_ROW);
                    
                    
                    
                    
                    
                    pm.refreshh();
                    JOptionPane.showMessageDialog(null, "Edit saved!");
                    
                    frmProject.dispose();
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Insert correct form of date / correct form of max points!");
                }
            }
            });
        
            
    
    
    }
    
    
    
}
