/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import repository.db.Repository;
import repository.db.DbRepository;
import repository.db.impl.RepositoryDBGeneric;


public abstract class AbstractGenericOperation {

    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

    public final Object execute(Object param) throws Exception {
        try {
            
            preconditions(param);
            startTransaction();
            Object result = executeOperation(param);
            commitTransaction();
            return result;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    // Operation-specific method
    protected abstract Object executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}
