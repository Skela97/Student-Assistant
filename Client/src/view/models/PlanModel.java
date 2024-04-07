/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.models;

import domain.Plan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladimir
 */
public class PlanModel extends AbstractTableModel{
   
    
    
    private List<Plan> plans;
    private String columns[];
    
    
    public PlanModel(){
    
        plans = new ArrayList<>();
        columns = new String[]{"Subject","DateCreated","Items","CompletedItems"};
    }
    
    
    @Override
    public int getRowCount() {
        
        return plans.size();
        
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch(columnIndex){
            case 0: return plans.get(rowIndex).getSubject().getName();
            case 1: return sdf.format(plans.get(rowIndex).getDateCreated());
            case 2 : return plans.get(rowIndex).getAllItems();
            case 3 : return plans.get(rowIndex).getCompletedItems();
            default : return "N/A";
        
        
        
        }
        
        
        
        
        
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public void refresh(List<Plan> plans) {
        this.plans = plans;
        fireTableDataChanged();
    }

    public void remove(int row) {
      
        plans.remove(row);
        fireTableDataChanged();
    }

    public Plan getPlan(int row) {
        return plans.get(row);
    }
    
    
    
    
    
    
    
    
    
    
    
}
