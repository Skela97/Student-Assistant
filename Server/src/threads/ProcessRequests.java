/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import SO.Subject.UpdateSubject;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Admin;

import domain.Plan;
import domain.Student;
import domain.Subject;
import domain.UpdateSubjectDomain;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Korisnik
 */
public class ProcessRequests extends Thread {
    
    Socket socket;
    Sender sender;
    Receiver receiver;
    
    public ProcessRequests(Socket socket) {
        this.socket = socket;
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }

    @Override
    public void run() {
        
        
        while(true){
            try{
                Request request=(Request)receiver.receive();
                Response response=new Response();
                Student student;
                Plan plan;
                Subject subject;
                try{
                    switch(request.getOperation()){
                        
                        case LOGIN_STUDENT:
                            student = (Student) request.getArgument();
                            response.setResult(Controller.getInstance().loginStudent(student));
                            
                            
                            break;
                       
                        case LOGIN_ADMIN:
                            Admin admin = (Admin) request.getArgument();
                            response.setResult(Controller.getInstance().loginAdmin(admin));
                            System.out.println("Primljen admin!");
                            break;
                            
                        case GET_ALL_SUBJECTS:
                            subject = new Subject();
                            response.setResult(Controller.getInstance().getAllSubjects(subject));
                           
                            break;
                        
                        case SEARCH_SUBJECT:
                            subject = (Subject) request.getArgument();
                            response.setResult(Controller.getInstance().searchSubjects(subject));
                            
                            break;
                        
                        
                        case GET_ALL_PLANS:
                            plan  = (Plan) request.getArgument();
                            response.setResult(Controller.getInstance().getAllPlans(plan));
                            break;  
                        
                        case SEARCH_PLANS:
                            plan = (Plan) request.getArgument();
                            response.setResult(Controller.getInstance().searchPlans(plan));
                            break; 
                            
                        case ADD_PLAN:
                            plan = (Plan) request.getArgument();
                            Controller.getInstance().addPlan(plan);
                            break;
                            
                        
                        case DELETE_PLAN:
                            plan = (Plan) request.getArgument();
                            Controller.getInstance().deletePlan(plan);
                            break;
                        
                        case EDIT_PLAN:
                            plan = (Plan) request.getArgument();
                             Controller.getInstance().editPlan(plan);
                             break;
                             
                       
                        case ADD_SUBJECT:
                            subject = (Subject) request.getArgument();
                            Controller.getInstance().addSubject(subject);
                            break;
                            
                        case EDIT_SUBJECT:
                             UpdateSubjectDomain us =  (UpdateSubjectDomain) request.getArgument();
                             Controller.getInstance().editSubject(us);
                             break;
                        
                        
                        case DELETE_SUBJECT:
                            subject = (Subject) request.getArgument();
                            Controller.getInstance().deleteSubject(subject);
                            break;
                            
                        case GET_PLAN:
                             plan = (Plan) request.getArgument();
                             response.setResult(Controller.getInstance().getPlan(plan));
                            
                        break;
                        
                        case GET_SUBJECT:
                            subject = (Subject) request.getArgument();
                            response.setResult(Controller.getInstance().getSubject(subject));
                            break;

                    }
                }catch(Exception e){
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            }catch(Exception ex){
                Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    
    
    
    
    
    
}
