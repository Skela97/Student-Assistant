/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.models;

import domain.Project;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vladimir
 */
public class ProjectModel extends AbstractTableModel{
    
    
    private List<Project> projects;
    private String[]columns;

    public ProjectModel() {
    
        projects = new ArrayList<>();
        columns = new String[]{"Name","Description","Deadline","Max points"};
        
    
    }
    
    
    
    
    
    @Override
    public int getRowCount() {
       return projects.size();
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch(columnIndex){
            
            case 0 : return projects.get(rowIndex).getName();
            case 1 : return projects.get(rowIndex).getDescription();
            case 2: return sdf.format(projects.get(rowIndex).getDeadline());
            case 3 : return projects.get(rowIndex).getMaxPoints();
            default: return "N/A";
        
        
        
        }
       
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }
    
    public void refresh(List<Project> projects){
        this.projects = projects;
        fireTableDataChanged();
    
    }

    public void add(Project p) {
        projects.add(p);
        fireTableDataChanged();
    }

    public Project getProject(int row) {
        return projects.get(row);
    }

    public void update(Project p, int row) {
        projects.add(row, p);
        fireTableDataChanged();
        
        
    }

    public void refreshh() {
        fireTableDataChanged();
    }

    public void delete(int row) {
        projects.remove(row);
        fireTableDataChanged();
    }

    public List<Project> getAll() {
        return projects;
    }
    
    
}
