package repository.db;

import domain.GenericEntity;
import domain.Plan;
import domain.Project;
import domain.Student;
import domain.Subject;
import java.sql.ResultSet;
import java.util.List;
//import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author laptop-02
 * @param <T>
 */
public interface Repository<T> {
    ResultSet getAll(T param) throws Exception;
    GenericEntity add(T param) throws Exception;
    GenericEntity edit(T param) throws Exception;
    void delete(T param)throws Exception;
    ResultSet search(T param) throws Exception;
    ResultSet get(T param) throws Exception;
    
    
 
}
