/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author TEST
 */
public class Student implements GenericEntity {
 
    
    //Student(StudentID, BrojIndeksa, Ime, Prezime, Email, Lozinka, Telefon)
    
    private long StudentID;
    private String brIndeksa;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private List<Plan> plans;
    

    public Student() {
    }

    public Student(long StudentID, String brIndeksa, String firstName, String lastName, String email, String password, String phoneNumber,List<Plan> plans) {
        this.StudentID = StudentID;
        this.brIndeksa = brIndeksa;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.plans = plans;
    }

    
    /**
     * @return the StudentID
     */
    
    public long getStudentID() {
        return StudentID;
    }

    /**
     * @param StudentID the StudentID to set
     */
    public void setStudentID(long StudentID) {
        this.StudentID = StudentID;
    }

    /**
     * @return the brIndeksa
     */
    public String getBrIndeksa() {
        return brIndeksa;
    }

    /**
     * @param brIndeksa the brIndeksa to set
     */
    public void setBrIndeksa(String brIndeksa) {
        this.brIndeksa = brIndeksa;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * @return the plans
     */
    public List<Plan> getPlans() {
        return plans;
    }

    /**
     * @param plans the plans to set
     */
    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public String getTableName() {
        return "student";
    }

    @Override
    public String getColumnNamesForInsert() {
       
          return "";
      
        
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCondictionListObjects() {
        return "";
    }

    @Override
    public String getJoinTables() {
        return "";
    }

    @Override
    public String getCriteriaRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCriteriaValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String findRowByID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSetForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
    
    
    
    
    
        
}
