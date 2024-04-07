/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Plan;

import SO.AbstractGenericOperation;
import domain.GenericEntity;
import domain.Plan;
import domain.PlanItem;
import java.util.List;
import repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Vladimir
 */
public class AddPlan extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        
        if(!(param instanceof Plan)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
        
        
    }

    @Override
    protected  Object executeOperation(Object entity) throws Exception {
        
       Plan p = (Plan) repository.add(entity);
       
       List<PlanItem> items = p.getItems();
       
        
       
        for (PlanItem item : items) {
            
            item.setPlan(p);
            repository.add((PlanItem)item);
            
        }
        
        
        return null;
        
                
    }
    
}
