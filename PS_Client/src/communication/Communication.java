/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.Admin;

import domain.Plan;
import domain.Project;
import domain.Student;
import domain.Subject;
import domain.UpdateSubjectDomain;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import javax.management.OperationsException;
import view.constant.Constants;
import view.cordinator.MainCordinator;



/**
 *
 * @author Cartman
 */
public class Communication {
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communication instance;
    
    private Communication() throws Exception{
        socket=new Socket("localhost", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
   /* public Student login(String username, String password) throws Exception {
        
        user.setUsername(username);
        user.setPassword(password);
        Request request=new Request(Operation.LOGIN_STUDENT, user);
        sender.send(request);
        
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (User)response.getResult();
        }else{
            throw response.getException();
        }
    }
    */
     

    public Student login(String username, String password) throws Exception {
      
        Student student = new Student();
        student.setEmail(username);
        student.setPassword(password);
        
        Request request = new Request(Operation.LOGIN_STUDENT, student);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
        
            return (Student) response.getResult();
            
        }
        else{
        
            throw response.getException();
        }
    
       
    }

    public List<Subject> getAllSubjects() throws Exception {
       
        
        Request request = new Request(Operation.GET_ALL_SUBJECTS,null);
        sender.send(request);
        Response response = (Response) receiver.receive();
       
       if(response.getException()==null){
           
           return (List<Subject>)response.getResult();
       
       }
       else{
       
           throw response.getException();
       
       }
    }

   

  

    public void addPlan(Plan p) throws Exception {
        Request request = new Request(Operation.ADD_PLAN, p);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null){
        
            
        
        }
        
        else{
            
            throw response.getException();
        
        }
        
        
        
    }

    public List<Plan> getAllPlans() throws Exception {
        
        Student student = (Student) MainCordinator.getInstance().getParam(Constants.CURRENT_STUDENT);
       
        Plan p = new Plan();
        p.setStudent(student);
        
        Request request = new Request(Operation.GET_ALL_PLANS,p);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
           
            return (List<Plan>) response.getResult();
        
        }
        
        else{
            
            throw response.getException();
        
        }
        
        
        
        
    }

    public void removePlan(Plan p) throws Exception {
        Request request = new Request(Operation.DELETE_PLAN,p);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
        
        }
        
        else{
            
              throw response.getException();
            
        
         }
         
        
        
        
 }

    public void editPlan(Plan p) throws Exception {
        Request request = new Request(Operation.EDIT_PLAN, p);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
        
        
        }
        else{
        
            throw response.getException();
        
        }
        
        
    }

    public Admin loginAdmin(String username, String password) throws Exception {
       
        Admin admin = new Admin();
        admin.setEmail(username);
        admin.setPassword(password);
        
        Request request = new Request(Operation.LOGIN_ADMIN, admin);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
        
            return (Admin) response.getResult();
            
        }
        else{
        
            throw response.getException();
        }
    }

    public void addSubject(Subject s) throws Exception {
        Request request = new Request(Operation.ADD_SUBJECT,s);
        sender.send(request);
        
         Response response = (Response) receiver.receive();
         
         if(response.getException() == null){
         
         
         }
         else{
         
             throw response.getException();
         
         }
    }

    public void editSubject(Subject s) throws Exception {
        
        UpdateSubjectDomain us = new UpdateSubjectDomain();
        List<Project> removedProjects = (List<Project>) MainCordinator.getInstance().getParam(Constants.REMOVED_PROJECTS);
        us.setRemovedProjects(removedProjects);
        us.setSubject(s);
        
        
        Request request = new Request(Operation.EDIT_SUBJECT, us);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
        
        
        }
        else{
        
            throw response.getException();
        
        }
    }

    public void removeSubject(Subject s) throws Exception {
       
        
        Request request = new Request(Operation.DELETE_SUBJECT,s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
        
        }
        
        else{
            
              throw response.getException();
            
        
         }
    }

    public List<Subject> searchSubjects(Subject s) throws Exception {
        
        Request request = new Request(Operation.SEARCH_SUBJECT,s);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
        
        
            return (List<Subject>) response.getResult();
        
        }
        
        else{
        
            throw response.getException();
        
        
        }
        
        
        
    }

    public List<Plan> searchPlans(String text) throws Exception {
       
        Plan p = new Plan();
        Student st = (Student) MainCordinator.getInstance().getParam(Constants.CURRENT_STUDENT);
        Subject s = new Subject();
        p.setStudent(st);
        s.setName(text);
        p.setSubject(s);
       
        Request request = new Request(Operation.SEARCH_PLANS,p);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        
        
        if(response.getException()==null){
           
            return (List<Plan>) response.getResult();
        
        }
        
        else{
            
            throw response.getException();
        
        }
        
        
    }

    public Plan getPlan(Plan p) throws Exception {
      Request request = new Request(Operation.GET_PLAN,p);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
           
            return (Plan) response.getResult();
        
        }
        
        else{
            
            throw response.getException();
        
        }
    }

    public Subject getSubject(Subject s) throws Exception {
        
        Request request = new Request(Operation.GET_SUBJECT,s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException()==null){
           
            return (Subject) response.getResult();
        
        }
        
        else{
            
            throw response.getException();
        
        }
    }

   
}

