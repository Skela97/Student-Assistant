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
public class Subject implements GenericEntity {
   // Predmet(PredmetID, Naziv, Opis, Semestar, Espb, AdminID)

    private long predmetID;
    private String name;
    private String description;
    private int semestar;
    private int espb;
    private Admin admin;
    private List<Project> projects;

    
    
    public Subject() {
    }

    public Subject(long predmetID, String name, String description, int semestar, int espb, Admin admin,List<Project> projects) {
        this.predmetID = predmetID;
        this.name = name;
        this.description = description;
        this.semestar = semestar;
        this.espb = espb;
        this.admin = admin;
        this.projects = projects;
    }

    /**
     * @return the predmetID
     */
    public long getPredmetID() {
        return predmetID;
    }

    /**
     * @param predmetID the predmetID to set
     */
    public void setPredmetID(long predmetID) {
        this.predmetID = predmetID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the semestar
     */
    public int getSemestar() {
        return semestar;
    }

    /**
     * @param semestar the semestar to set
     */
    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    /**
     * @return the espb
     */
    public int getEspb() {
        return espb;
    }

    /**
     * @param espb the espb to set
     */
    public void setEspb(int espb) {
        this.espb = espb;
    }

    /**
     * @return the admin
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return name;

    }

    /**
     * @return the projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String getTableName() {
        return "subject";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "name,description,semestar,espb,adminID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
                sb.append("'").append(name).append("',")
                .append("'").append(description).append("',")
                .append(semestar).append(",")
                .append(espb).append(",")
                .append(admin.getAdminID());
                
        
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
        predmetID = id;
    }

    @Override
    public String getCondictionListObjects() {
        return "WHERE subjectID=subjectID";
    }

    @Override
    public String getJoinTables() {
        return " JOIN admin USING(adminID)";
    }

    @Override
    public String getCriteriaRow() {
        return "subject.name";
        
    }

    @Override
    public String getCriteriaValue() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("'%").append(getName()).append("%'");
        return sb.toString();
    
    }
/*    @Override
    public String getColumnNamesForInsert() {
        return "id,name,description,price,manufacturerid,measurementunit";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",")
                .append("'").append(name).append("',")
                .append("'").append(description).append("',")
                .append(price).append(",")
                .append(manufacturer.getId()).append(",")
                .append("'").append(measurementUnit).append("'");
        return sb.toString();
    }
*/
    //    String query ="INSERT into SUBJECT (name,description,semestar,espb,adminID) VALUES(?,?,?,?,?)";
    
   //query = "INSERT into Project(name, description,deadline,maxPoints,subjectID) VALUES (?,?,?,?,?)";

    @Override
    public String findRowByID() {
        return String.format("subjectID = %d", predmetID);
    }

    @Override
    public String getSetForUpdate() {
        return String.format("name = '%s', description= '%s', semestar = %d, espb= %d ", name, description,semestar,espb);
    }

    
}
