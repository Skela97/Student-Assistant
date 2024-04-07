/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.models;

import domain.Plan;
import domain.PlanItem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladimir
 */
public class PlanItemModel extends AbstractTableModel {

    private List<PlanItem> items;
    private String[] columns;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public PlanItemModel() {
        
        items = new ArrayList<>();
        columns = new String[]{"Project","Comment","Deadline","Completed"};
        
    }
    
    @Override
    public int getRowCount() {
        
        return items.size();
        
        
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            
            case 0 : return items.get(rowIndex).getProject();
            case 1: return items.get(rowIndex).getComment();
            case 2 : return sdf.format(items.get(rowIndex).getDeadline());
            case 3 : return items.get(rowIndex).getCompleted();
            
            default: return "N/A";
            
        
        
        
        }
        

        
    }

    @Override
    public Class<?> getColumnClass(int i) {
        
            if(i==3){
            
                return Boolean.class;
            
            }
        
        return super.getColumnClass(i); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public void addPlanItem(PlanItem pi) {
        
        items.add(pi);
        fireTableDataChanged();
        
    }

    public List<PlanItem> getAll() {
        return items;
    }

    public int getSize() {
       return items.size();
    }

    public int getCompleted() {
        int i = 0;
        for (PlanItem item : items) {
            
            if(item.getCompleted() == true){
                i++;
            }
            
            
        }
        return i;
    }

    

    public void remove(int row) {
        items.remove(row);
        fireTableDataChanged();
    }

    public void refresh(List<PlanItem> items) {
        this.items = items;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        if(columnIndex==3){
        
            return true;
        }
        
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        
        if(columnIndex == 3){
        
         items.get(rowIndex).setCompleted((Boolean) o);
        
        }
        
        
        
        super.setValueAt(o, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
