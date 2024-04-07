/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import communication.Communication;
import domain.Project;
import domain.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmViewSubjects;
import view.form.util.FormMode;
import view.models.SubjectModel;

/**
 *
 * @author Vladimir
 */
public class ViewSubjectsController {
    FrmViewSubjects frmSubjects;

    public ViewSubjectsController(FrmViewSubjects frmSubjects) {
        this.frmSubjects = frmSubjects;
    }

    public void openForm() {
        
        populateForm();
        setModel();
        fillTable();
        addActionListeners();
        
    }

    private void populateForm() {
        frmSubjects.setTitle("View subjects");
        frmSubjects.setVisible(true);
        frmSubjects.setLocationRelativeTo(null);
        frmSubjects.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void setModel() {
        SubjectModel sm = new SubjectModel();
        frmSubjects.getTblSubjects().setModel(sm);
        
        MainCordinator.getInstance().addParam(Constants.CURRENT_SUBJECT_MODEL, sm);
        
    }
    
    private void addActionListeners(){
    
        frmSubjects.addActionListenerSearch(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                search(ae);
            }

            private void search(ActionEvent ae) {
                try {
                    String filter = frmSubjects.getTxtFilter().getText();
                    
                    Subject s = new Subject();
                    s.setName(filter);
                    
                    
                    
                    List<Subject> subjects = Communication.getInstance().searchSubjects(s);
                    SubjectModel sm = (SubjectModel) frmSubjects.getTblSubjects().getModel();
                    
                    sm.refresh(subjects);
                    
                    if( subjects == null){
                        
                            JOptionPane.showMessageDialog(frmSubjects, "Subjects couldn't be loaded!");
                            return;
                        }
                        if(subjects.isEmpty()){
                        
                            JOptionPane.showMessageDialog(frmSubjects, "No subjects were found!");
                            
                        
                        }
                    
                } catch (Exception ex) {
                    Logger.getLogger(ViewSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSubjects, "Error loadin subjects!" + ex.getMessage(), " Error details",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        frmSubjects.addActionListenerDetails(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                try {
                    int row = frmSubjects.getTblSubjects().getSelectedRow();
                    
                    if(row == -1){
                        JOptionPane.showMessageDialog(null, "Choose subject you want to see");
                        return;
                        
                        
                    }
                    
                    List<Project> removedProjects = new ArrayList<>();
                    
                    MainCordinator.getInstance().addParam(Constants.REMOVED_PROJECTS, removedProjects);
                    
                    SubjectModel sm = (SubjectModel) frmSubjects.getTblSubjects().getModel();
                    Subject s = sm.getSubject(row);
                    
                    s = Communication.getInstance().getSubject(s);
                    
                    
                    
                    MainCordinator.getInstance().addParam(Constants.CURRENT_SUBJECT, s);
                    MainCordinator.getInstance().openSubjectForm(FormMode.FORM_VIEW);
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmSubjects, "Plan couldn't be loaded!");
                    return;
                }
            }
        });
     
    
    
    }

    private void fillTable()  {
        
        try {
            SubjectModel sm = (SubjectModel) frmSubjects.getTblSubjects().getModel();
            sm.refresh(Communication.getInstance().getAllSubjects());
        } catch (Exception ex) {
            Logger.getLogger(ViewSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmSubjects, "Error" + ex.getMessage(), " Error loading subjects!",JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    
    
    
}
