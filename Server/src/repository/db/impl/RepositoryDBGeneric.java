/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import domain.GenericEntity;
import domain.Student;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;


public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

    
    @Override
    public GenericEntity add(GenericEntity entity) throws Exception {
        try {
            
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                entity.setId(id);
            }
            
            
            statement.close();
            rsKey.close();
            return entity;
            
        } catch (SQLException ex) {
            throw ex;
        }
        
        
    }

    @Override
    public ResultSet getAll(GenericEntity param) throws Exception {
      Connection conn = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
                .append(param.getTableName())
                .append(param.getJoinTables())
                .append(param.getCondictionListObjects());
        
         
        String query = sb.toString();
        System.out.println(query);
        
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        return rs;
        
        
    }

    @Override
    public GenericEntity edit(GenericEntity param) throws Exception {
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").
                append(param.getTableName())
                .append(" SET ").append(param.getSetForUpdate())
                .append(" WHERE ").append(param.findRowByID());
              
        String query = sb.toString();
        
        System.out.println(query);
        
        Statement s = conn.createStatement();
        s.executeUpdate(query);
        
        return param;
        
                
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        
       
        Connection conn = DbConnectionFactory.getInstance().getConnection();
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("DELETE FROM ")
                .append(param.getTableName())
                .append(" WHERE ")
                .append(param.findRowByID());
        
        String query = sb.toString();
        System.out.println(query);
        
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        
    }
        
    
        
        
    

    @Override
    public ResultSet search(GenericEntity param) throws Exception {
         
         Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            
            sb.append("SELECT * FROM ")
                    .append(param.getTableName())
                    .append(param.getJoinTables())
                    .append(param.getCondictionListObjects())
                    .append(" AND ")
                    .append(param.getCriteriaRow())
                    .append(" LIKE ")
                    .append(param.getCriteriaValue());
               
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

           // String sql = "SELECT * FROM plan JOIN subject USING(subjectID) JOIN student USING(studentID) WHERE student.studentID = ? AND subject.name LIKE '%"+filter+"%'";
            //connection.commit();
            //statement.close();
            return rs;
         }

    @Override
    public ResultSet get(GenericEntity param) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            
            sb.append("SELECT * FROM ")
                    .append(param.getTableName())
                    .append(param.getJoinTables())
                    .append(param.getCondictionListObjects())
                    .append(" AND ")
                    .append(param.findRowByID());
               
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

           // String sql = "SELECT * FROM plan JOIN subject USING(subjectID) JOIN student USING(studentID) WHERE student.studentID = ? AND subject.name LIKE '%"+filter+"%'";
            //connection.commit();
            //statement.close();
            return rs;
    }
         
        
         
         
        
        
    }

    



    
    

