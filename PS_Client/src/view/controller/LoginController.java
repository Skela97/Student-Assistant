/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import communication.Communication;
import domain.Admin;
import domain.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.constant.Constants;
import view.cordinator.MainCordinator;
import view.form.FrmLogin;

/**
 *
 * @author TEST
 */
public class LoginController {
        
    private FrmLogin frmLogin;
    

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        
    }

    public void openForm() {
       
        populateForm();
        
        
    }
    
    private void addActionListenerStudent(){
        
        frmLogin.loginBtnStudentAddActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
             
                showLoginStudent(e);
               
                
                
            }
            private void showLoginStudent(ActionEvent e) {
                
               frmLogin.getjPanel1().setVisible(true);
               frmLogin.getjPanel3().setVisible(false);
                
                
            }
            
            
        });
       
        
        
        
        
    }
    
        private void addActionListenerAdmin(){
        
        frmLogin.loginBtnAdminAddActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
             
                showLoginAdmin(e);
               
                
                
            }
            private void showLoginAdmin(ActionEvent e) {
                
               frmLogin.getjPanel1().setVisible(false);
               frmLogin.getjPanel3().setVisible(true);
                
                
                
            }
            
            
        });
       
        
        
        
        
    }
    
    
    
      private void addActionListenerLoginStudent() {
        frmLogin.loginStudentAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loginUser(actionEvent);
            }

            private void loginUser(ActionEvent actionEvent) {
                resetForm();
                try {
                    
                    String username = frmLogin.getTxtUserStudent().getText();
                    String password = frmLogin.getTxtPassStudent().getText();

                    validateForm(username, password);

                    //User user = Controller.getInstance().login(username, password);
                    Student student = Communication.getInstance().login(username, password);
                    JOptionPane.showMessageDialog(
                            frmLogin,
                            "Welcome " + student.getFirstName() + " " + student.getLastName(),
                            "Login", JOptionPane.INFORMATION_MESSAGE
                    );
                    frmLogin.dispose();
                    MainCordinator.getInstance().addParam(Constants.CURRENT_STUDENT, student);
                    MainCordinator.getInstance().openMainStudent();
                     
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frmLogin, e.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);
                }
            }

            
            
            
            
            
            private void resetForm() {
                
            }

            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
                if (username.isEmpty()) {
                    //frmLogin.getLblUsernameError().setText("Username can not be empty!");
                    errorMessage += "Username can not be empty!\n";
                }
                if (password.isEmpty()) {
                    //frmLogin.getLblPasswordError().setText("Password can not be empty!");
                    errorMessage += "Password can not be empty!\n";
                }
                if (!errorMessage.isEmpty()) {
                    
                    throw new Exception(errorMessage);
                }
            }

        });
    
        frmLogin.addActionListenerLoginAdmin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loginAdmin(ae);
            }

            private void loginAdmin(ActionEvent ae) {
                
                try {
                    String username = frmLogin.getTxtUserAdmin().getText();
                    String password = frmLogin.getTxtPassAdmin().getText();
                    
                    validateForm(username, password);
                    
                    Admin admin = Communication.getInstance().loginAdmin(username, password);
                    JOptionPane.showMessageDialog(
                            frmLogin,
                            "Welcome" ,
                            "Login", JOptionPane.INFORMATION_MESSAGE
                    );
                    frmLogin.dispose();
                    MainCordinator.getInstance().addParam(Constants.CURRENT_ADMIN, admin);
                    MainCordinator.getInstance().openMainAdmin();
                    
                    
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frmLogin, e.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);
                }
                
                
                
              
            }

            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
               
                if (username.isEmpty()) {
                    //frmLogin.getLblUsernameError().setText("Username can not be empty!");
                    errorMessage += "Username can not be empty!\n";
                }
                if (password.isEmpty()) {
                    //frmLogin.getLblPasswordError().setText("Password can not be empty!");
                    errorMessage += "Password can not be empty!\n";
                }
                if (!errorMessage.isEmpty()) {
                    
                    throw new Exception(errorMessage);
                }
            }
            
            
            
            
            
            
            
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
      }

        

    private void populateForm() {
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);
        frmLogin.setTitle("Login");
       
        frmLogin.getjPanel1().setVisible(false);
        frmLogin.getjPanel3().setVisible(false);
       
        addActionListenerStudent();
        addActionListenerAdmin();
        addActionListenerLoginStudent();
        
        
    }
    
}
  