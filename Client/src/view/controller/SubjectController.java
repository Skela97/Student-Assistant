/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.Admin;
import domain.Project;
import domain.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmSubject;
import view.form.util.FormMode;
import view.models.ProjectModel;
import view.models.SubjectModel;

/**
 *
 * @author Vladimir
 */
public class SubjectController {
 
    FrmSubject frmSubject;

    public SubjectController(FrmSubject frmSubject) {
        this.frmSubject = frmSubject;
    }

    public void openForm(FormMode fm) {
        setModel();
        setForm(fm);
        
        populateForm();
        addActionListeners();
    }

    private void populateForm() {
        frmSubject.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmSubject.setTitle("Working with subject");
        frmSubject.setLocationRelativeTo(null);
        frmSubject.setVisible(true);
       
        
    }

    private void setForm(FormMode fm) {
        switch(fm){
            case FORM_ADD: 
                
                frmSubject.getTxtDescription().setEnabled(true);
                frmSubject.getTxtEspb().setEnabled(true);
                frmSubject.getTxtName().setEnabled(true);
                frmSubject.getTxtSemester().setEnabled(true);
                
                frmSubject.getBtnAddProj().setEnabled(true);
                frmSubject.getBtnEditProj().setEnabled(true);
                frmSubject.getBtnRemoveProj().setEnabled(true);
                
                frmSubject.getBtnRemove().setEnabled(false);
                frmSubject.getBtnEdit().setEnabled(false);
                frmSubject.getBtncancel().setEnabled(true);
                
                frmSubject.getBtnEnable().setEnabled(false);
                frmSubject.getBtnSave().setEnabled(true);
                
                
                break;
            
            case FORM_VIEW:
                frmSubject.getTxtDescription().setEnabled(false);
                frmSubject.getTxtEspb().setEnabled(false);
                frmSubject.getTxtName().setEnabled(false);
                frmSubject.getTxtSemester().setEnabled(false);
                
                frmSubject.getBtnAddProj().setEnabled(false);
                frmSubject.getBtnEditProj().setEnabled(false);
                frmSubject.getBtnRemoveProj().setEnabled(false);
                
                frmSubject.getBtnRemove().setEnabled(false);
                frmSubject.getBtnEdit().setEnabled(false);
                frmSubject.getBtncancel().setEnabled(true);
                
                frmSubject.getBtnEnable().setEnabled(true);
                frmSubject.getBtnSave().setEnabled(false);
                frmSubject.getTblProjects().setEnabled(false);
                
                Subject s = (Subject) MainCordinator.getInstance().getParam(Constants.CURRENT_SUBJECT);
                
                ProjectModel pm = (ProjectModel) frmSubject.getTblProjects().getModel();
                pm.refresh(s.getProjects());
                
                frmSubject.getTxtDescription().setText(s.getDescription());
                frmSubject.getTxtEspb().setText(String.valueOf(s.getEspb()));
                frmSubject.getTxtName().setText(s.getName());
                frmSubject.getTxtSemester().setText(String.valueOf(s.getSemestar()));
                 
                break;
                
            case FORM_EDIT:
                 frmSubject.getTxtDescription().setEnabled(true);
                frmSubject.getTxtEspb().setEnabled(true);
                frmSubject.getTxtName().setEnabled(true);
                frmSubject.getTxtSemester().setEnabled(true);
                
                frmSubject.getBtnAddProj().setEnabled(true);
                frmSubject.getBtnEditProj().setEnabled(true);
                frmSubject.getBtnRemoveProj().setEnabled(true);
                
                frmSubject.getBtnRemove().setEnabled(true);
                frmSubject.getBtnEdit().setEnabled(true);
                frmSubject.getBtncancel().setEnabled(true);
                
                frmSubject.getBtnEnable().setEnabled(false);
                frmSubject.getBtnSave().setEnabled(false);
                frmSubject.getTblProjects().setEnabled(true);
                
            break;
        
        
        }
    }

    private void setModel() {
        ProjectModel pm = new ProjectModel();
        frmSubject.getTblProjects().setModel(pm);
        
        MainCordinator.getInstance().addParam(Constants.CURRENT_PROJECT_MODEL, pm);
        
        
    }
    
    private void addActionListeners(){
    
        frmSubject.addActionListenerAddProject(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                MainCordinator.getInstance().openProjectForm(FormMode.FORM_ADD);
                
                
            }
        });
    
        frmSubject.addActionListenerEditProject(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                EditProject(ae);
                
            }

            private void EditProject(ActionEvent ae) {
                
               int row = frmSubject.getTblProjects().getSelectedRow();
               
               if(row == -1){
                   JOptionPane.showMessageDialog(null, "Select project you want to delete");
                   return;
               
               }
               
               ProjectModel pm = (ProjectModel) frmSubject.getTblProjects().getModel();
               Project p = new Project();
               p = pm.getProject(row);
               
               MainCordinator.getInstance().addParam(Constants.CURRENT_PROJECT_ROW, row);
               MainCordinator.getInstance().addParam(Constants.CURRENT_PROJECT, p);
               MainCordinator.getInstance().openProjectForm(FormMode.FORM_VIEW);
               
               
                
                
                
            }
        });
        frmSubject.addActionListenerRemove(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeProject();
            }

            private void removeProject() {
                 int row = frmSubject.getTblProjects().getSelectedRow();
                 
                 if(row == -1){
                     JOptionPane.showMessageDialog(null, "Select row you want to delete");
                     return;
                 
                 }
                 
                 ProjectModel pm = (ProjectModel) frmSubject.getTblProjects().getModel();
                 List<Project> removedProjects = (List<Project>) MainCordinator.getInstance().getParam(Constants.REMOVED_PROJECTS);
                 removedProjects.add(pm.getProject(row));
                 pm.delete(row);
                 
                 JOptionPane.showMessageDialog(null, "Sucesfully deleted project");
                 
                 
                 
                 
                 
            }
        });
        
        frmSubject.addActionListenerCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frmSubject.dispose();
            }
        });
        
        frmSubject.addActionListenerSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addSubject();
            }

            private void addSubject() {
                try {
                    
                    
                    if(frmSubject.getTxtDescription().getText().isEmpty() || frmSubject.getTxtEspb().getText().isEmpty() || frmSubject.getTxtName().getText().isEmpty() || frmSubject.getTxtSemester().getText().isEmpty()){
                        
                        JOptionPane.showMessageDialog(null, "Fill the form!");
                        return;
                        
                    }
                    
                    Subject s = new Subject();
                    Admin a = (Admin) MainCordinator.getInstance().getParam(Constants.CURRENT_ADMIN);
                    
                    s.setAdmin(a);
                    s.setDescription(frmSubject.getTxtDescription().getText());
                    s.setEspb(Integer.parseInt(frmSubject.getTxtEspb().getText()));
                    s.setName(frmSubject.getTxtName().getText());
                    
                    
                    ProjectModel pm = (ProjectModel) frmSubject.getTblProjects().getModel();
                    
                    List<Project> projects = pm.getAll();
                    s.setProjects(projects);
                    
                    s.setSemestar(Integer.parseInt(frmSubject.getTxtSemester().getText()));
                    
                    Communication.getInstance().addSubject(s);
                    JOptionPane.showMessageDialog(frmSubject, "Subject is sucesfully saved!");
                    
                    frmSubject.dispose();
                    
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSubject, ex.getMessage(), "Saving subject error", JOptionPane.ERROR_MESSAGE);
                }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                }
                
                
                
                
                
                
            

        });
        
        frmSubject.addActionListenerEnable(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                setForm(FormMode.FORM_EDIT);
            }
        });
        
        
        frmSubject.addActionListenerEdit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                editSubject(ae);
                
            }

            private void editSubject(ActionEvent ae) {
              
                try {
                    Subject s = (Subject) MainCordinator.getInstance().getParam(Constants.CURRENT_SUBJECT);
                    ProjectModel pm = (ProjectModel) frmSubject.getTblProjects().getModel();
                    List<Project> projects = pm.getAll();
                    
                    if(frmSubject.getTxtDescription().getText().isEmpty() || frmSubject.getTxtEspb().getText().isEmpty() || frmSubject.getTxtName().getText().isEmpty() || frmSubject.getTxtSemester().getText().isEmpty()){
                        
                        JOptionPane.showMessageDialog(null, "Fill the form!");
                        return;
                        
                    }
                    
                    s.setDescription(frmSubject.getTxtDescription().getText());
                    s.setEspb(Integer.parseInt(frmSubject.getTxtEspb().getText()));
                    s.setName(frmSubject.getTxtName().getText());
                    s.setSemestar(Integer.parseInt(frmSubject.getTxtSemester().getText()));
                    
                    
                    
                    Communication.getInstance().editSubject(s);
                    
                    JOptionPane.showMessageDialog(frmSubject, "Subject is sucesfully saved!");
                    
                    SubjectModel sm = (SubjectModel) MainCordinator.getInstance().getParam(Constants.CURRENT_SUBJECT_MODEL);
                    sm.refresh(Communication.getInstance().getAllSubjects());
                    
                    frmSubject.dispose();
                    
                } catch (Exception ex) {
                    Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSubject, ex.getMessage());
                    frmSubject.dispose();
                }
                
                
                
                
                
                
                
                
                
                
                
            }
        });
        
        frmSubject.addActionListenerRemoveSubject(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                removeSubject();
                
                
            }

            private void removeSubject() {
                
                try {
                    Subject s = (Subject) MainCordinator.getInstance().getParam(Constants.CURRENT_SUBJECT);
                    
                    Communication.getInstance().removeSubject(s);
                    JOptionPane.showMessageDialog(frmSubject,"Subject deleted succesffully","Delete subject", JOptionPane.INFORMATION_MESSAGE);
                    SubjectModel sm = (SubjectModel) MainCordinator.getInstance().getParam(Constants.CURRENT_SUBJECT_MODEL);
                   
                    sm.refresh(Communication.getInstance().getAllSubjects());
                    frmSubject.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmSubject, "Error deleting subject!\n" + ex.getMessage(),"Delete subject",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                
                
            }
        });
        
        
        
        
        
        
        
    }
    
    
    
    
    
}
