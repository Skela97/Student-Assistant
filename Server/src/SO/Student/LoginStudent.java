/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Student;

import SO.AbstractGenericOperation;
import domain.Student;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class LoginStudent extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
             if(!(param instanceof Student)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
        
        ResultSet rs =  repository.getAll((Student)entity);
        
        Student student = (Student) entity;
        List<Student> students = new ArrayList<>();
        
        
        
        while(rs.next()){
           
            
             Student s = new Student();
                
                s.setBrIndeksa(rs.getString("index"));
                s.setEmail(rs.getString("Email"));
                s.setFirstName(rs.getString("FirstName"));
                s.setLastName(rs.getString("LastName"));
                s.setPassword(rs.getString("password"));
                s.setPhoneNumber(rs.getString("phoneNumber"));
                s.setStudentID(rs.getLong("StudentID"));
                
                students.add(s);
        
        
        }
        
        for (Student s : students) {
            
            if(s.getEmail().equals(student.getEmail()) && s.getPassword().equals(student.getPassword())){
                
                return s;
            
            }
            
            
            
        }
        
        throw new Exception("Unknown user!");
        
        
        
    }
    
    
    
    
}
