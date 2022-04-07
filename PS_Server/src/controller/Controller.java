/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import SO.AbstractGenericOperation;
import SO.Admin.LoginAdmin;
import SO.Plan.AddPlan;
import SO.Plan.DeletePlan;
import SO.Plan.GetAllPlans;
import SO.Plan.GetPlan;
import SO.Plan.SearchPlans;
import SO.Plan.UpdatePlan;
import SO.Student.LoginStudent;
import SO.Subject.AddSubject;
import SO.Subject.DeleteSubject;
import SO.Subject.GetAllSubjects;
import SO.Subject.GetSubject;
import SO.Subject.SearchSubjects;
import SO.Subject.UpdateSubject;
import domain.Admin;
import domain.Plan;
import domain.Project;
import domain.Student;
import domain.Subject;
import domain.UpdateSubjectDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DbRepository;
import repository.db.Repository;



/**
 *
 * @author laptop-02
 */
public class Controller {
    
   
 
    
    private static Controller controller;

    private Controller() {
        
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

   

   

 

    public void addPlan(Plan entity) throws Exception {
       
        AbstractGenericOperation addPlan = new AddPlan();
        addPlan.execute(entity);
        
    
    }

   

    public void deletePlan(Plan entity) throws Exception {
       
        AbstractGenericOperation deletePlan = new DeletePlan();
        deletePlan.execute(entity);
       
        
    }

    public void editPlan(Plan entity) throws Exception {
        
       AbstractGenericOperation updatePlan = new UpdatePlan();
       updatePlan.execute(entity);
        
        
        
    }

    public void addSubject(Subject entity) throws Exception {
        AbstractGenericOperation addSubject = new AddSubject();
        addSubject.execute(entity);
      
    }

    public void editPlan(Subject entity) throws Exception {
      
        AbstractGenericOperation updatePlan = new UpdatePlan();
        updatePlan.execute(entity);
        
        
    }

    public void deleteSubject(Subject entity) throws Exception {
        AbstractGenericOperation deleteSubject = new DeleteSubject();
        deleteSubject.execute(entity);
    }

    
    
    public Student loginStudent(Student entity) throws Exception {
       
        AbstractGenericOperation loginStudent = new LoginStudent();
        Student s = (Student) loginStudent.execute(entity);
        
        return s;
        
      
        
        
    }
  public Admin loginAdmin(Admin entity) throws Exception {
       
        AbstractGenericOperation loginAdmin = new LoginAdmin();
        return (Admin) loginAdmin.execute(entity);
            
        
        
    }
  
 
    public List<Subject> getAllSubjects(Subject entity) throws Exception {
        
        AbstractGenericOperation getAllSubjects = new GetAllSubjects();
         return (List<Subject>) getAllSubjects.execute(entity);
        
        
       
        
    }

    public List<Plan> getAllPlans(Plan entity) throws Exception {
        AbstractGenericOperation getAllPlans = new GetAllPlans();
        return (List<Plan>) getAllPlans.execute(entity);
        
        
    }

    public List<Plan> searchPlans(Plan entity) throws Exception {
        AbstractGenericOperation searchPlans = new SearchPlans();
        
        return (List<Plan>) searchPlans.execute(entity);
    }

    public List<Subject> searchSubjects(Subject entity) throws Exception {
        AbstractGenericOperation searchSubjects = new SearchSubjects();
        
        return (List<Subject>) searchSubjects.execute(entity);
        
        
    }

  

    public void editSubject(UpdateSubjectDomain us) throws Exception {
           
           AbstractGenericOperation updateSubject = new UpdateSubject();
           updateSubject.execute(us);
        
    }
    
    
    public Plan getPlan(Plan p) throws Exception{
    
        AbstractGenericOperation getPlan = new GetPlan();
        return (Plan) getPlan.execute(p);
    
    
    
    }

    public Subject getSubject(Subject s) throws Exception{
    
        AbstractGenericOperation getSubject = new GetSubject();
        return(Subject) getSubject.execute(s);
        
    
    
    }
    
    
  
}
