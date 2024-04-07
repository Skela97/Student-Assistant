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
public class UpdateSubjectDomain implements Serializable{
    
    private List<Project> removedProjects;
    private Subject subject;

    public UpdateSubjectDomain() {
    }

    public UpdateSubjectDomain(List<Project> removedProjects, Subject subject) {
        this.removedProjects = removedProjects;
        this.subject = subject;
    }

    /**
     * @return the removedProjects
     */
    public List<Project> getRemovedProjects() {
        return removedProjects;
    }

    /**
     * @param removedProjects the removedProjects to set
     */
    public void setRemovedProjects(List<Project> removedProjects) {
        this.removedProjects = removedProjects;
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
    
    
    
        
        
        
}
