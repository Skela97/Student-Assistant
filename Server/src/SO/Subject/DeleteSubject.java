/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Subject;

import SO.AbstractGenericOperation;
import domain.Subject;

/**
 *
 * @author Vladimir
 */
public class DeleteSubject extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
       if(!(param instanceof Subject)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        
        repository.delete((Subject)entity);
        
        return null;
    }
    
}
