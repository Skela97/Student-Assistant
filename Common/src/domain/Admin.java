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
 * @author Vladimir
 */
public class Admin implements GenericEntity {

    
    private Long adminID;
    private String email;
    private String password;
    private List<Subject> listPredmet;
    
    

    public Admin() {
    }

    public Admin(Long adminID, String email, String password, List<Subject> listPredmet) {
        this.adminID = adminID;
        this.email = email;
        this.password = password;
        this.listPredmet = listPredmet;
    }

    /**
     * @return the adminID
     */
    public Long getAdminID() {
        return adminID;
    }

    /**
     * @param adminID the adminID to set
     */
    public void setAdminID(Long adminID) {
        this.adminID = adminID;
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
     * @return the listPredmet
     */
    public List<Subject> getListPredmet() {
        return listPredmet;
    }

    /**
     * @param listPredmet the listPredmet to set
     */
    public void setListPredmet(List<Subject> listPredmet) {
        this.listPredmet = listPredmet;
    }

    @Override
    public String getTableName() {
        return "admin";
    }

    @Override
    public String getColumnNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public String toString() {
        return email;
    
    }
    

   
    
    
            
    
    
}
