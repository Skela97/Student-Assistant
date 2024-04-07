/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import communication.Communication;
import domain.Plan;
import domain.PlanItem;
import domain.Project;
import domain.Student;
import domain.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmPlan;
import view.form.FrmSubject;
import view.form.util.FormMode;
import view.models.PlanItemModel;
import view.models.PlanModel;

/**
 *
 * @author Vladimir
 */
public class PlanController {
    FrmPlan frmPlan;
    Plan p;
    public PlanController(FrmPlan frmPlan) {
        this.frmPlan = frmPlan;
    }

    public void openFormAdd(FormMode formMode) {
       
        populateForm();
        setumForm(formMode);
        p = new Plan();
        
    }

    private void populateForm() {
        frmPlan.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmPlan.setLocationRelativeTo(null);
        frmPlan.setVisible(true);
        frmPlan.setTitle("Working with plan");
        addActionListeners();
        setModel();
        fillCBSubjects();
    }

    private void fillCBSubjects() {
        frmPlan.getCbSubject().removeAllItems();
        
        List<Subject> listSubjects = null;
        try {
            
            listSubjects = Communication.getInstance().getAllSubjects();
        } catch (Exception ex) {
            Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       frmPlan.getCbSubject().setModel(new DefaultComboBoxModel<>(listSubjects.toArray()));
    }

    private void setumForm(FormMode formMode) {
        switch(formMode){
        
            case FORM_ADD:
                frmPlan.getCbSubject().setEnabled(true);
                frmPlan.getBtnChoose().setEnabled(true);
                frmPlan.getjPanel1().setEnabled(false);
                frmPlan.getBtnSave().setEnabled(false);
                frmPlan.getBtnCancel().setEnabled(true);
                frmPlan.getBtnDetails().setEnabled(false);
                frmPlan.getBtnAddItem().setEnabled(false);
                frmPlan.getBtnEdit().setEnabled(false);
                frmPlan.getBtnEnable().setEnabled(false);
                frmPlan.getBtnDelete().setEnabled(false);
                break;
                
            case FORM_VIEW:
                frmPlan.getCbSubject().setEnabled(false);
                frmPlan.getBtnChoose().setEnabled(false);
                frmPlan.getjPanel1().setEnabled(false);
                frmPlan.getBtnSave().setEnabled(false);
                frmPlan.getBtnCancel().setEnabled(true);
                frmPlan.getBtnDetails().setEnabled(false);
                frmPlan.getBtnAddItem().setEnabled(false);
                frmPlan.getBtnEdit().setEnabled(false);
                frmPlan.getBtnEnable().setEnabled(true);
                frmPlan.getBtnDelete().setEnabled(false);
                frmPlan.getTblPlanItem().setEnabled(false);
                 setCb();
                 PlanItemModel pim = (PlanItemModel) frmPlan.getTblPlanItem().getModel();
                 Plan p = (Plan) MainCordinator.getInstance().getParam(Constants.CURRENT_PLAN);
              
                 pim.refresh(p.getItems());
                 
                break;
                
            case FORM_EDIT:
                
                frmPlan.getCbSubject().setEnabled(false);
                frmPlan.getBtnChoose().setEnabled(false);
                frmPlan.getjPanel1().setEnabled(true);
                frmPlan.getBtnSave().setEnabled(false);
                frmPlan.getBtnCancel().setEnabled(true);
                frmPlan.getBtnDetails().setEnabled(true);
                frmPlan.getBtnAddItem().setEnabled(true);
                frmPlan.getBtnEdit().setEnabled(true);
                frmPlan.getBtnEnable().setEnabled(false);
                frmPlan.getBtnDelete().setEnabled(true);
                frmPlan.getTblPlanItem().setEnabled(true);
                
                
                
                
                
                
                break;
                
                
                
                
                
        }
        
        
        
        
        
    }
    
    private void addActionListeners(){
        
        frmPlan.addActionListerChoose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openFormAddNewPlan(ae);
            }

            private void openFormAddNewPlan(ActionEvent ae) {
                
                try {
                    List<Plan> plans = Communication.getInstance().getAllPlans();
                    Subject sub = (Subject) frmPlan.getCbSubject().getSelectedItem();
                    for (Plan plan : plans) {
                        
                        if(plan.getSubject().getPredmetID() == sub.getPredmetID()){
                        
                            JOptionPane.showMessageDialog(frmPlan, "You already have plan for this subject!");
                            return;
                        
                        }
                           
                        
                    }
                    if(sub.getProjects().isEmpty()){
                    
                        JOptionPane.showMessageDialog(null, "You can't make plans for this subject because admin didn't add any projects into it");
                        return;
                    }
                    
                    
                    // p.setSubject((Subject) frmPlan.getCbSubject().getSelectedItem());
                    
                    MainCordinator.getInstance().addParam(Constants.CURRENT_SUBJECT, (Subject) frmPlan.getCbSubject().getSelectedItem());
                    
                    frmPlan.getCbSubject().setEnabled(false);
                    frmPlan.getBtnChoose().setEnabled(false);
                    frmPlan.getjPanel1().setEnabled(true);
                    frmPlan.getBtnSave().setEnabled(true);
                    frmPlan.getBtnCancel().setEnabled(true);
                    frmPlan.getBtnDetails().setEnabled(true);
                    frmPlan.getBtnAddItem().setEnabled(true);
                    frmPlan.getBtnEdit().setEnabled(false);
                    frmPlan.getBtnEnable().setEnabled(false);
                    frmPlan.getBtnDelete().setEnabled(false);
                } catch (Exception ex) {
                    Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPlan, ex.getMessage(), "Choosingg failed", JOptionPane.ERROR_MESSAGE);
                }
                
                
            }
            
            
            
            
            
            
            
        });
        
        
        
        frmPlan.addActionListenerSaveEdit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Plan p = (Plan) MainCordinator.getInstance().getParam(Constants.CURRENT_PLAN);
                    PlanItemModel pim = (PlanItemModel) frmPlan.getTblPlanItem().getModel();
                    List<PlanItem> items = pim.getAll();
                    p.setAllItems(pim.getSize());
                    p.setCompletedItems(pim.getCompleted());
                    
                    p.setItems(items);
                    
                    
                    Communication.getInstance().editPlan(p);
                    JOptionPane.showMessageDialog(frmPlan, "Plan edited sucesffully!\n", "Edit plan", JOptionPane.INFORMATION_MESSAGE);
                    
                    PlanModel pm = (PlanModel) MainCordinator.getInstance().getParam(Constants.CURRENT_PLAN_MODEL);
                   
                    pm.refresh(Communication.getInstance().getAllPlans());
                    
                    frmPlan.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPlan, ex.getMessage(), "Editing failed", JOptionPane.ERROR_MESSAGE);
                    
                    
                }
                
                
                
            }
        });
        
        frmPlan.addActionListenerRemoveItem(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                PlanItemModel pim = (PlanItemModel) frmPlan.getTblPlanItem().getModel();
                  int row = frmPlan.getTblPlanItem().getSelectedRow();
                 
                  if(row == -1){
                      JOptionPane.showMessageDialog(frmPlan, "Please select item you want to delete");
                  
                  }
                  else{
                  pim.remove(row);
                  }
                
                
                
            }
        });
        
       frmPlan.addactionListenerRemovePlan(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                try {
                    Plan p = (Plan) MainCordinator.getInstance().getParam(Constants.CURRENT_PLAN);
                    Communication.getInstance().removePlan(p);
                    JOptionPane.showMessageDialog(frmPlan,"Plan deleted succesffully","Delete plan", JOptionPane.INFORMATION_MESSAGE);
                    
                    PlanModel pm = (PlanModel) MainCordinator.getInstance().getParam(Constants.CURRENT_PLAN_MODEL);
                   
                    pm.refresh(Communication.getInstance().getAllPlans());
                    
                    frmPlan.dispose();
                    
                } catch (Exception ex) {
                     JOptionPane.showMessageDialog(frmPlan, "Error deleting plan!\n" + ex.getMessage(),"Delete Plan",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        });
    
      frmPlan.addActionListenerNewItem(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                MainCordinator.getInstance().openItemForm(FormMode.FORM_ADD);
            
            
            }   
            
            
            
            
        });
      
    
      
      frmPlan.addActionListenerCancel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                closeForm();
            }

            private void closeForm() {
                frmPlan.dispose();
            }
        });
      
      
      
      frmPlan.addActionListenerSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                addPlan(ae);
                
                
            }

            private void addPlan(ActionEvent ae) {
                
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    Plan p = new Plan();
                    PlanItemModel pim = (PlanItemModel) frmPlan.getTblPlanItem().getModel();
                    List<PlanItem> items = pim.getAll();
                    
                    p.setItems(items);
                    
                    int allItems = pim.getSize();
                    p.setAllItems(allItems);
                    int completedItems = pim.getCompleted();
                    p.setCompletedItems(completedItems);
                    p.setDateCreated(new Date());
                    p.setStudent((Student) MainCordinator.getInstance().getParam(Constants.CURRENT_STUDENT));
                    p.setSubject((Subject) frmPlan.getCbSubject().getSelectedItem());
                    
                           
                    
                    Communication.getInstance().addPlan(p);
                    
                    
                    JOptionPane.showMessageDialog(frmPlan, "Plan is sucesfully saved!");
                    
                    frmPlan.dispose();
                    
                    
                    
                } catch (Exception ex) {
                    
                    Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmPlan, ex.getMessage());
                }
                
                
                
            }
            
            
            
        });
    
        frmPlan.setActionListenerEnable(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setumForm(FormMode.FORM_EDIT);
            }
        });
      
      
      
}

    private void setModel() {
        PlanItemModel  pim = new PlanItemModel();
        
        frmPlan.getTblPlanItem().setModel(pim);
        
        MainCordinator.getInstance().addParam(Constants.PLAN_ITEM_MODEL, pim);
        
    }

    private void setCb() {
        
        Plan p = (Plan) MainCordinator.getInstance().getParam(Constants.CURRENT_PLAN);
        
        int itemCount = frmPlan.getCbSubject().getItemCount();
        
        
        for(int i = 0; i<itemCount;i++){
        
            Subject s = (Subject) frmPlan.getCbSubject().getItemAt(i);
            if(p.getSubject().getPredmetID() == s.getPredmetID() ){
            
                frmPlan.getCbSubject().setSelectedItem(s);
                Subject sub = (Subject) frmPlan.getCbSubject().getItemAt(i);
                MainCordinator.getInstance().addParam(Constants.CURRENT_SUBJECT, sub);
                
                
            }
        
        }
        
        
        
        
        
    }
    
  
    
    
    
    
    
    
}