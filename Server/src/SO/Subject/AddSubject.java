/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Subject;

import SO.AbstractGenericOperation;
import domain.Plan;
import domain.Project;
import domain.Subject;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class AddSubject extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        
          if(!(param instanceof Subject)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
        


    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        
        Subject s = (Subject) repository.add((Subject) entity);
        
        List<Project> projects = s.getProjects();
        
        
        
        for (Project project : projects) {
            project.setPredmet(s);
            repository.add((Project) project);
            
        }
        
        
        
        
        return null;
        
    }
    
}
