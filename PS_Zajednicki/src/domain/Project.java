/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Vladimir
 */
public class Project implements GenericEntity{
    
    private long projectID;
    private String name;
    private String description;
    private Date deadline;
    private int maxPoints;
    private Subject predmet;

    public Project() {
    }

    public Project(long projectID, String name, String description, Date deadline, int maxPoints, Subject predmet) {
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.maxPoints = maxPoints;
        this.predmet = predmet;
    }

    /**
     * @return the projectID
     */
    public long getProjectID() {
        return projectID;
    }

    /**
     * @param projectID the projectID to set
     */
    public void setProjectID(long projectID) {
        this.projectID = projectID;
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
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return the maxPoints
     */
    public int getMaxPoints() {
        return maxPoints;
    }

    /**
     * @param maxPoints the maxPoints to set
     */
    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    /**
     * @return the predmet
     */
    public Subject getPredmet() {
        return predmet;
    }

    /**
     * @param predmet the predmet to set
     */
    public void setPredmet(Subject predmet) {
        this.predmet = predmet;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName() {
        return "project";    }

    @Override
    public String getColumnNamesForInsert() {
      return "name, description,deadline,maxPoints,subjectID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("',")
                .append("'").append(description).append("',")
                .append("'").append(new java.sql.Date(deadline.getTime())).append("',")
                .append("").append(maxPoints).append(",")
                .append("").append(predmet.getPredmetID());
               
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
        this.projectID = id;
    }

    @Override
    public String getCondictionListObjects() {
        return String.format(" WHERE subjectID = %d", predmet.getPredmetID());
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
         return String.format("subjectID = %d AND projectID = %d", predmet.getPredmetID(), projectID);
    }

    @Override
    public String getSetForUpdate() {
        java.sql.Date dateDB = new java.sql.Date(deadline.getTime());
        return String.format("name = '%s', description = '%s', deadline= '%s', maxpoints = %d ", name, description, dateDB, maxPoints);
        
    }
    
}
