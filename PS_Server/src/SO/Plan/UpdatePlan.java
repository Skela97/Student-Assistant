/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Plan;

import SO.AbstractGenericOperation;
import domain.Plan;
import domain.PlanItem;
import domain.Student;
import domain.Subject;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class UpdatePlan extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if(!(param instanceof Plan)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
       
        Plan p = (Plan) entity;
        
        List<PlanItem> items = p.getItems();
        
        repository.edit((Plan)entity);
        
        PlanItem pi = new PlanItem();
        
        Subject s = new Subject();
        s.setPredmetID(p.getSubject().getPredmetID());
        
        Student st = new Student();
        st.setStudentID(p.getStudent().getStudentID());
        
        Plan plan = new Plan();
        
        plan.setStudent(st);
        plan.setSubject(s);
        
        pi.setPlan(plan);
        
        repository.delete((PlanItem) pi);
        
        for (PlanItem item : items) {
            item.setPlan(p);
            repository.add((PlanItem) item);
        
        }
        
        
        
        
        
        
        return null;
        
        
        
    }
    
}
