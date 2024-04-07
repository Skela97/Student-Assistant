/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Subject;

import SO.AbstractGenericOperation;
import domain.Admin;
import domain.Project;
import domain.Subject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class SearchSubjects extends AbstractGenericOperation {

    
    
    
    @Override
    protected void preconditions(Object param) throws Exception {
         if(!(param instanceof Subject)){
        
                throw new Exception("Wrong type of parameter!");
        
        }    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
       
        ResultSet rs = repository.search((Subject) entity);
        
        List<Subject> subjects = new ArrayList<>();
        
       while(rs.next()){
            
                Admin a = new Admin();
                
                a.setAdminID(rs.getLong("adminID"));
                a.setEmail(rs.getString("email"));
                a.setPassword(rs.getString("password"));
                
                
                
                Subject s = new Subject();
               
                s.setAdmin(a);
                s.setDescription(rs.getString("description"));
                s.setEspb(rs.getInt("espb"));
                s.setName(rs.getString("name"));
                s.setPredmetID(rs.getLong("subjectID"));
                s.setSemestar(rs.getInt("semestar"));
                
                Project project = new Project();
                
                project.setPredmet(s);
                
                ResultSet rs2 = repository.getAll((Project) project);
                
                
                List<Project> projects = new ArrayList<>();
                
                while(rs2.next()){
                
                    Project p = new Project();
                    
                    p.setPredmet(s);
                    p.setDeadline(rs2.getDate("deadline"));
                    p.setDescription(rs2.getString("description"));
                    p.setMaxPoints(rs2.getInt("maxpoints"));
                    p.setName(rs2.getString("name"));
                    p.setProjectID(rs2.getLong("projectID"));
                    
                    projects.add(p);
                
                
                    
                }
                
                s.setProjects(projects);
                
              
                
        
               subjects.add(s);
               
               
               
               
               
               
        }
 
       
        return subjects;
       
       
       
       
    }  
        
        
        
        
        
        
        
        
    
    
}
