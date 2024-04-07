/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communication.Communication;
import domain.PlanItem;
import domain.Project;
import domain.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.OperationsException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmPlanItem;
import view.form.util.FormMode;
import view.models.PlanItemModel;

/**
 *
 * @author Vladimir
 */
public class PlanItemController {
    private FrmPlanItem frmPI;
   
    public PlanItemController(FrmPlanItem frmPI) {
        this.frmPI = frmPI;
        
    }
    
    
    

    public void openForm(FormMode formMode) {
        populateForm();
        fillCbProjects();
        setupMode(formMode);
        addActionListeners();
    }

    private void populateForm() {
        frmPI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmPI.setLocationRelativeTo(null);
        frmPI.setVisible(true);
        frmPI.setTitle("Working with plan item");
    }

    private void setupMode(FormMode formMode) {
       
        switch(formMode){
        
            case FORM_ADD: 
                
                frmPI.getBtnCancel().setEnabled(true);
                frmPI.getBtnSave().setEnabled(true);
                frmPI.getTxtComment().setEnabled(true);
                frmPI.getTxtDate().setEnabled(true);
                frmPI.getCbCompleted().setEnabled(true);
                frmPI.getBtnCancel().setEnabled(true);
                break;
            case FORM_EDIT:
                
        
        
        }
        
            
        
    }

    private void fillCbProjects() {
        
        frmPI.getCbProject().removeAllItems();
        
        List<Project> projects = null;
        
        
          Subject s = (Subject) MainCordinator.getInstance().getParam(Constants.CURRENT_SUBJECT);
            //System.out.println(s);
           //projects.projects = Communication.getInstance().getAllProjects(s);
         
        
        projects = s.getProjects();
        frmPI.getCbProject().setModel(new DefaultComboBoxModel<>(projects.toArray()));
        
        
        
        
        
        
    }
    
      private void addActionListeners(){
        
        frmPI.addActionListerSave(new ActionListener() {
                
                
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                saveItem();
            }

            private void saveItem() {
                
                try {
                    PlanItem pi = new PlanItem();
                    
                    validationForm();
                    
                     
                     
                    pi.setComment(frmPI.getTxtComment().getText());
                    
                    if(frmPI.getCbCompleted().isSelected()){
                        pi.setCompleted(true);
                    }
                    else{
                        
                        pi.setCompleted(false);
                        
                    }
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    
                    pi.setDeadline(sdf.parse(frmPI.getTxtDate().getText()));
                    pi.setProject((Project) frmPI.getCbProject().getSelectedItem());
                    
                    PlanItemModel pim = (PlanItemModel) MainCordinator.getInstance().getParam(Constants.PLAN_ITEM_MODEL);
                    
                   
                    
                   
                            
                     pim.addPlanItem(pi);
                     JOptionPane.showMessageDialog(null, "Sucesffuly added new item!");
                     resetForm();
                    
                  //  MainCordinator.getInstance().addItem();
                    
                } catch (ParseException e) {
                   Logger.getLogger(PlanItemController.class.getName()).log(Level.SEVERE, null, e);
                   JOptionPane.showMessageDialog(frmPI, e.getMessage(), "Failed login", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    Logger.getLogger(PlanItemController.class.getName()).log(Level.SEVERE, null, e);
                     JOptionPane.showMessageDialog(frmPI, e.getMessage(), "Failed login", JOptionPane.ERROR_MESSAGE);
                }
            
            
            }

            private void validationForm() throws Exception {
                 if(frmPI.getTxtComment().getText().isEmpty() || frmPI.getTxtDate().getText().isEmpty()){
                    
                        throw new Exception("You have to fill all fields");                    
                    }
            }

            private void resetForm() {
                
                frmPI.getTxtComment().setText("");
                frmPI.getTxtDate().setText("");
            }
               
            
            }
        );
        
        frmPI.addActionListenerCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cancelForm(ae);
            }

            private void cancelForm(ActionEvent ae) {
                frmPI.dispose();
            }
            
            
        });
            
            
            
            
      }
            
       
    
}
