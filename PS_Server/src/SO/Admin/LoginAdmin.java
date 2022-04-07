/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO.Admin;

import SO.AbstractGenericOperation;
import domain.Admin;
import domain.Student;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class LoginAdmin extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(!(param instanceof Admin)){
        
                throw new Exception("Wrong type of parameter!");
        
        }
    }

    @Override
    protected Object executeOperation(Object entity) throws Exception {
     
        
        ResultSet rs = repository.getAll((Admin)entity);
        Admin admin = (Admin)entity;
        System.out.println("Podaci od admina koji su stigli od servera + " + admin.getEmail() + " " +admin.getPassword());
        
        
        List<Admin> admins = new ArrayList<>();
        
        while(rs.next()){
        
                Admin a = new Admin();
                a.setAdminID(rs.getLong("adminID"));
                a.setEmail(rs.getString("email"));
                a.setPassword(rs.getString("password"));
                
                admins.add(a);
            
        
        }
        
        System.out.println("Lista admina nadjenih iz baze " + admins);
        
        for (Admin a : admins) {
            
            if(a.getEmail().equals(admin.getEmail()) && a.getPassword().equals(admin.getPassword())){
            
                
                System.out.println("Admin is found!");
                return a;
            
            }
            
            
            
        }
        
        
        throw new Exception("Unknown user");
        
        
        
        
        
        
        
        
        
    }
    
}
