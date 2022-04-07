/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Cartman
 */
public enum Operation implements Serializable{
    
    LOGIN_STUDENT,
    LOGIN_ADMIN,
    
    GET_ALL_SUBJECTS,
    SEARCH_SUBJECT,
    ADD_SUBJECT,
    EDIT_SUBJECT,
    DELETE_SUBJECT,
    GET_SUBJECT,
    
    
    GET_PLAN,
    GET_ALL_PLANS,
    ADD_PLAN,
    DELETE_PLAN,
    EDIT_PLAN,
    SEARCH_PLANS,
    GET_ALL_PROJECTS
     
    
}
