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
public class PlanItem implements GenericEntity{
        
    private Plan plan;
    private Project project;
    private long planItemID;
    private String comment;
    private Date deadline;
    private Boolean completed;

    public PlanItem() {
    }

    public PlanItem(Plan plan, Project project, long planItemID, String comment, Date deadline, Boolean completed) {
        this.plan = plan;
        this.project = project;
        this.planItemID = planItemID;
        this.comment = comment;
        this.deadline = deadline;
        this.completed = completed;
    }

    /**
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the planItemID
     */
    public long getPlanItemID() {
        return planItemID;
    }

    /**
     * @param planItemID the planItemID to set
     */
    public void setPlanItemID(long planItemID) {
        this.planItemID = planItemID;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String getTableName() {
        return "planItem";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "studentID,subjectID,comment, deadline, completed, projectID, subjectProjectID";
    }

    @Override
    public String getInsertValues() {
       StringBuilder sb = new StringBuilder();
               
               sb.append(plan.getStudent().getStudentID()).append(",")
                .append(plan.getSubject().getPredmetID()).append(",")
                .append("'").append(comment).append("',")
                .append("'").append(new java.sql.Date(deadline.getTime())).append("',")
                .append(completed).append(",")
                .append(project.getProjectID()).append(",")
                .append(plan.getSubject().getPredmetID());
                
        
                return sb.toString();
    }

    @Override
    public void setId(Long id) {
        this.planItemID = id;
    }

    @Override
    public String getCondictionListObjects() {
        return String.format(" WHERE pi.subjectID = %d AND pi.studentID = %d", plan.getSubject().getPredmetID(), plan.getStudent().getStudentID() );
    }

    @Override
    public String getJoinTables() {
        return " pi JOIN project po ON pi.projectID = po.projectID AND pi.subjectProjectId = po.subjectID";
    }
    
    //SELECT * FROM planItem PI JOIN project po ON pi.projectID = po.projectID AND pi.subjectProjectId = po.subjectID WHERE pi.subjectID = ? AND pi.studentID = ?";

    @Override
    public String getCriteriaRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCriteriaValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //String sql2 = "INSERT into planItem (studentID,subjectID,comment, deadline, completed, projectID, subjectProjectID) VALUES(?,?,?,?,?,?,?)";

    @Override
    public String findRowByID() {
       return String.format(" subjectID = %d AND studentID = %d", plan.getSubject().getPredmetID(), plan.getStudent().getStudentID() );
    }

    @Override
    public String getSetForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
