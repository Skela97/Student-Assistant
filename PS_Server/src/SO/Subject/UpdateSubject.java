/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Subject;

import SO.AbstractGenericOperation;
import domain.Project;
import domain.Subject;
import domain.UpdateSubjectDomain;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class UpdateSubject extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
         if(!(param instanceof UpdateSubjectDomain)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        
        UpdateSubjectDomain us =  (UpdateSubjectDomain) param;
        
        Subject s = us.getSubject();
        List<Project> removedProjects  = us.getRemovedProjects();
        List<Project> projects = s.getProjects();
        repository.edit((Subject) s);
        
        for (Project removedProject : removedProjects) {
            removedProject.setPredmet(s);
            repository.delete((Project) removedProject);
        }
        
        
        for (Project project : projects) {
            
           
            if(project.getProjectID() == 0l){
               project.setPredmet(s);
               repository.add((Project) project);
            
            }
            
            else{
             
                repository.edit((Project) project);
                
                
            }
                
            
            
            
            
        }
        
        
        
        
        System.out.println("Subject is \n" + s);
        System.out.println("Removed projects : " + removedProjects);
        
        
        
        
        
        return null;
        
    }
    
}
