/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Milos Milic
 */
public interface GenericEntity extends Serializable {

    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    void setId(Long id);

    String getCondictionListObjects();
    
    String getJoinTables();
    
    String getCriteriaRow();
    
    String getCriteriaValue();
    
    String findRowByID();
    
    String getSetForUpdate();
    
    
    
}
