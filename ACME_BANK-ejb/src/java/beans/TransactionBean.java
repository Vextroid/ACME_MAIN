/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.TransactionDAO;
import RDB.RDBTransactionsDAO;
import RegClass.SavingsAcc;
import RegClass.Transactions;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Vextroid
 */
@Stateless
public class TransactionBean implements TransactionBeanRemote, Serializable {

  @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    private Connection connection;
    
        @PostConstruct
    public void initialize(){
        try{
            connection = dataSource.getConnection();
        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    /**
     *
     */
    @PreDestroy
     public void close()
    {
        try{
            connection.close();
        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
    }
    
    @Override
    public void deposit(int C_ID, String accNum, int amount, String description)
    {
         try{
            TransactionDAO dao = new RDBTransactionsDAO(connection);
            Transactions trans = new Transactions(accNum, amount, description);
            dao.deposit(trans);
                        
        }catch(Exception e)
        {
            System.out.println("Could not make a deposit.");
            e.printStackTrace();
        }
    }
    
    @Override
    public void withdrawl(int C_ID, String accNum, int amount, String description)
    {
                 try{
            TransactionDAO dao = new RDBTransactionsDAO(connection);
            Transactions trans = new Transactions(accNum, amount, description);
            dao.deposit(trans);
                        
        }catch(Exception e)
        {
            System.out.println("Could not make a withdrawl.");
            e.printStackTrace();
        }
    }

}
