/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vladimir
 */
public class Plan implements GenericEntity{
    //Plan(StudentID, PredmetID, DatumKreiranja, UkupanBrojStavki, BrojKompletiranihStavki)
    
    private Subject subject;
    private Student student;
    private Date dateCreated;
    private int allItems;
    private int completedItems;
    private List<PlanItem> items;

    public Plan() {
    }

    public Plan(Subject subject, Student student, Date dateCreated, int allItems, int completedItems, List<PlanItem> items) {
        this.subject = subject;
        this.student = student;
        this.dateCreated = dateCreated;
        this.allItems = allItems;
        this.completedItems = completedItems;
        this.items = items;
    }

    

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the allItems
     */
    public int getAllItems() {
        return allItems;
    }

    /**
     * @param allItems the allItems to set
     */
    public void setAllItems(int allItems) {
        this.allItems = allItems;
    }

    /**
     * @return the completedItems
     */
    public int getCompletedItems() {
        return completedItems;
    }

    /**
     * @param completedItems the completedItems to set
     */
    public void setCompletedItems(int completedItems) {
        this.completedItems = completedItems;
    }

    /**
     * @return the items
     */
    public List<PlanItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<PlanItem> items) {
        this.items = items;
    }

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return subject.getName();
    }

    @Override
    public String getTableName() {
        return "plan";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "studentID,subjectID,dateCreated,allItems,CompletedItems";
        
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getStudentID()).append(",")
                .append(subject.getPredmetID()).append(",")
                .append("'").append(new java.sql.Date(dateCreated.getTime())).append("',")
                .append(allItems).append(",")
                .append(completedItems);
        
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
        
    }

    @Override
    public String getCondictionListObjects() {
        return String.format(" WHERE studentID = %d", student.getStudentID());
    }

    @Override
    public String getJoinTables() {
        return " JOIN subject USING(subjectID) JOIN student USING(studentID)";
    }

    @Override
    public String getCriteriaRow() {
        
       return "subject.name";
                                                         
    }

    @Override
    public String getCriteriaValue() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("'%").append(subject.getName()).append("%'");
        
        return sb.toString();

    }

    
    //tring sql = "INSERT into PLAN (studentID,subjectID,dateCreated,allItems,CompletedItems) VALUES (?,?,?,?,?)";

    @Override
    public String findRowByID() {
        return String.format("subjectID = %d AND studentID = %d", subject.getPredmetID(), student.getStudentID());
    }

    @Override
    public String getSetForUpdate() {
       return String.format("allItems = %d, completedItems = %d", allItems, completedItems);
    }
    
}
