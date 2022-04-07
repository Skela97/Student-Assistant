/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.models;

import domain.Project;
import domain.Subject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladimir
 */
public class SubjectModel extends AbstractTableModel{

    String columns[];
    List<Subject> subjects;

    public SubjectModel() {
        
        columns = new String[]{"Name","Description","Semester","ESPB"};
        subjects = new ArrayList<>();
        
    }
    
    
    
    
    
    
    
    @Override
    public int getRowCount() {
        return subjects.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            
            case 0: return subjects.get(rowIndex).getName();
            case 1 : return subjects.get(rowIndex).getDescription();
            case 2 : return subjects.get(rowIndex).getSemestar();
            case 3 : return subjects.get(rowIndex).getEspb();
            default: return "N/A";
            
        
        }
        
       
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public void refresh(List<Subject> allSubjects) {
        subjects = allSubjects;
        fireTableDataChanged();
    }

    public Subject getSubject(int row) {
        return subjects.get(row);
    }

    
    
    
    
    
}
