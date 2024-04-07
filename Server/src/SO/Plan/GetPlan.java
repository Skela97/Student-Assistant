/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Plan;

import SO.AbstractGenericOperation;
import domain.Plan;
import domain.PlanItem;
import domain.Project;
import domain.Student;
import domain.Subject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class GetPlan extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
           if(!(param instanceof Plan)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        
        
        ResultSet rs = repository.get((Plan)entity);
        
       
            while(rs.next()){
            
            Plan p = new Plan();
            
            p.setAllItems(rs.getInt("allItems"));
            p.setCompletedItems(rs.getInt("CompletedItems"));
            p.setDateCreated(rs.getDate("DateCreated"));
            
            Subject su = new Subject();
            su.setPredmetID(rs.getLong("subjectID"));
            su.setName(rs.getString("name"));
            p.setSubject(su);
            
            Student student = new Student();
            
            student.setStudentID(rs.getLong("studentID"));
            
            p.setStudent(student);
            
            List<PlanItem> items = new ArrayList<>();
            PlanItem pi = new PlanItem();
            
            pi.setPlan(p);
            ResultSet rs2 = repository.getAll((PlanItem)pi);
                
             while(rs2.next()){
                    
                    PlanItem item = new PlanItem();
                    item.setComment(rs2.getString("comment"));
                    item.setCompleted(rs2.getBoolean("completed"));
                 
                            
                    item.setDeadline(rs2.getDate("deadline"));
                    item.setPlan(p);
                    item.setPlanItemID(rs2.getLong("planItemId"));
                    
                    Project pr = new Project();
                    pr.setProjectID(rs2.getLong("projectID"));
                    pr.setName(rs2.getString("name"));
                    item.setProject(pr);
                    
                   
                    items.add(item);
                    
                }
                
               
            
            
            p.setItems(items);
            
            return p;
        
            
        
        
        }
        
        
        throw new Exception("Error loading the plan");
        
        
        
        
        
    }
    
}
