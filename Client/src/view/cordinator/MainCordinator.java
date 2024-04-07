/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cordinator;

import domain.Subject;
import java.util.HashMap;
import java.util.Map;
import view.controller.LoginController;
import view.controller.MainAdminController;
import view.controller.MainStudentController;
import view.form.FrmLogin;
import view.form.FrmMainStudent;
import view.controller.PlanController;
import view.controller.PlanItemController;
import view.controller.ProjectController;
import view.controller.SubjectController;
import view.controller.ViewPlansController;
import view.controller.ViewSubjectsController;
import view.form.FrmMainAdmin;
import view.form.FrmPlan;
import view.form.FrmPlanItem;
import view.form.FrmProject;
import view.form.FrmSubject;
import view.form.FrmViewPlans;
import view.form.FrmViewSubjects;
import view.form.util.FormMode;
/*import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.controller.InvoiceController;
import rs.ac.bg.fon.ps.view.controller.LoginController;
import rs.ac.bg.fon.ps.view.controller.MainContoller;
import rs.ac.bg.fon.ps.view.controller.ProductController;
import rs.ac.bg.fon.ps.view.controller.ProductViewAllController;
import rs.ac.bg.fon.ps.view.form.FrmInvoice;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmProduct;
import rs.ac.bg.fon.ps.view.form.FrmViewProducts;
import rs.ac.bg.fon.ps.view.form.util.FormMode;
*/
/**
 *
 * @author laptop-02
 */
public class MainCordinator {

    private static MainCordinator instance;

    //private final MainContoller mainContoller;
    
    //this can be in some other Singleton class
    private final Map<String, Object> params;

    private MainCordinator() {
        //mainContoller = new MainContoller(new FrmMain());
        params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }
    public void openLoginForm() {
        LoginController loginContoller = new LoginController(new FrmLogin());
        
        loginContoller.openForm();
    }

    public void openMainStudent(){
    
        MainStudentController mainController = new MainStudentController(new FrmMainStudent());
        
        mainController.openForm();
    
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public void openPlanForm(FormMode formMode) {
       
        PlanController planController = new PlanController(new FrmPlan());
        planController.openFormAdd(formMode);
        
        
        
    }

    
    public void openItemForm(FormMode formMode) {
        
        PlanItemController pic = new PlanItemController(new FrmPlanItem());
        pic.openForm(formMode);
        
    }

    public void openViewPlans(){
    
    
    
        ViewPlansController vpc = new ViewPlansController(new FrmViewPlans());
        vpc.openForm();
    
    }

    public void openMainAdmin() {
      
        MainAdminController mac = new MainAdminController(new FrmMainAdmin());
        mac.openForm();
        
        
    }
    
    public void openViewSubjects(){
    
        ViewSubjectsController vsc = new ViewSubjectsController(new FrmViewSubjects());
        vsc.openForm();
    
    
    }

  
    public void openSubjectForm(FormMode fm){
    
        SubjectController sc = new SubjectController(new FrmSubject());
        sc.openForm(fm);
    
        
    
    }
   
    
    public void openProjectForm(FormMode fm){
        ProjectController pc = new ProjectController(new FrmProject());
        pc.openForm(fm);
    
    
    }

   
}
