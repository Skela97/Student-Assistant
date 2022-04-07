/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;
import communication.Communication;
import domain.Plan;
import domain.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmViewPlans;
import view.form.util.FormMode;
import view.models.PlanModel;

/**
 *
 * @author Vladimir
 */
public class ViewPlansController {
    FrmViewPlans frmViewPlans;

    public ViewPlansController() {
    }

    public ViewPlansController(FrmViewPlans frmViewPlans) {
        this.frmViewPlans = frmViewPlans;
    }

    public void openForm() {
       setModel();
       populateForm();
       fillTable();
       addActionListeners();
    }

    private void populateForm() {
        
        frmViewPlans.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmViewPlans.setLocationRelativeTo(null);
        frmViewPlans.setVisible(true);
       
        
    }

    private void setModel() {
        
        PlanModel pm = new PlanModel();
        frmViewPlans.getTblPlans().setModel(pm);
        MainCordinator.getInstance().addParam(Constants.CURRENT_PLAN_MODEL, pm);
        
    }

    private void fillTable() {
        List<Plan> plans;
        try {

           
            plans = Communication.getInstance().getAllPlans();
            PlanModel pm = (PlanModel) frmViewPlans.getTblPlans().getModel();
            
            pm.refresh(plans);
                    
            
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewPlans, "Error" + ex.getMessage(), " Error details",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ViewPlansController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
       
        
        
        
        
        
        
    }
    
   
     private void addActionListeners(){
    
            frmViewPlans.addActionListenerFilter(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                   filter(frmViewPlans.getTxtFilter().getText());
                }

                private void filter(String text) {
                    
                    try {
                        
                        Plan p = new Plan();
                        
                         List<Plan> plans = Communication.getInstance().searchPlans(text);
                        
                        if( plans == null){
                        
                            JOptionPane.showMessageDialog(frmViewPlans, "Plans couldn't be loaded!");
                            return;
                        }
                        if(plans.isEmpty()){
                        
                            JOptionPane.showMessageDialog(frmViewPlans, "No plans were found!");
                            
                        
                        }
                        
                        System.out.println("blaaa");
                        System.out.println(plans);
                               
                        
                        PlanModel pm = (PlanModel) frmViewPlans.getTblPlans().getModel();
                        pm.refresh(plans);
                        
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ViewPlansController.class.getName()).log(Level.SEVERE, null, ex);
                          JOptionPane.showMessageDialog(frmViewPlans, "Plans couldn't be loaded!");
                          return;
                    }
                    
                    
                    
                    
                    
                }
            });
             frmViewPlans.addActionListenerDetails(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                openPlanForm();
                
                
                
            }

                private void openPlanForm() {
                    
                try {
                    PlanModel pm = (PlanModel) frmViewPlans.getTblPlans().getModel();
                    int row = frmViewPlans.getTblPlans().getSelectedRow();
                    
                    if(row == -1){
                    
                        JOptionPane.showMessageDialog(frmViewPlans, "No row is selected!");
                        return;
                    }
                    Plan p = pm.getPlan(row);
                    p = Communication.getInstance().getPlan(p);
                    
                   
                    MainCordinator.getInstance().addParam(Constants.CURRENT_PLAN, p);
                    
                    MainCordinator.getInstance().openPlanForm(FormMode.FORM_VIEW);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmViewPlans, "Plan couldn't be loaded!");
                    return;
                }
                    
                    
                    
                }
        });
    
    
         }
    
    
    
    
    
}
