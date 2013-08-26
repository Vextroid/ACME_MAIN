/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.SavingsDAO;
import RDB.RDBSavingsDAO;
import RegClass.SavingsAcc;
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
public class SavingsBean implements SavingsBeanRemote, Serializable {

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
    public void createSavingsAccount(int id, String accNum, int balance)
    {
        try{
            SavingsDAO dao = new RDBSavingsDAO(connection);
            SavingsAcc save = new SavingsAcc(id, accNum, balance);
            dao.createSavingsAccount(save);
                        
        }catch(Exception e)
        {
            System.out.println("Could not create Savings Account.");
            e.printStackTrace();
        }
    }

    @Override
    public int getBalance(int id,String accNum)
    {
        try{
            int bal;
            SavingsDAO dao = new RDBSavingsDAO(connection);
            //SavingsAcc save = new SavingsAcc(id, accNum, balance);
            bal = dao.getBalance(id,accNum);
             //bal = 10;
            return bal;
        }catch(Exception e)
        {
            System.out.println("Could not create Savings Account.");
            e.printStackTrace();
            return (1);
        }
    }
    
    @Override
    public boolean maxSavings(int C_ID)
    {
               try{
            boolean isMax;
            SavingsDAO dao = new RDBSavingsDAO(connection);
            //SavingsAcc save = new SavingsAcc(id, accNum, balance);
            isMax = dao.maxSavings(C_ID);
             //bal = 10;
            return isMax;
        }catch(Exception e)
        {
            System.out.println("Could not create Savings Account.");
            e.printStackTrace();
            return false;
        } 
    }
    
}
