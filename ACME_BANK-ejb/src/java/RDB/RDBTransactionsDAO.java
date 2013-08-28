/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RDB;

import DAO.TransactionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vextroid
 */
public class RDBTransactionsDAO implements TransactionDAO {
    
    private Connection dbConnection = null;
    private ArrayList employeeList = new ArrayList();
    
    public RDBTransactionsDAO(Connection connection) {
        this.dbConnection = connection;    
    }
    
    @Override
    public void deposit(int C_ID, String accNum, int amount, String desc)
    {
        
         try{
            //int id = savings.C_ID;
            //String acc = savings.accNum;
            //int bal = savings.balance;
            
                                                                    //UPDATE DBUSR.SAVINGS SET BALANCE = BALANCE+100 WHERE ACC_NUM = 'ROTT2013';
          PreparedStatement sqlStatement = dbConnection.prepareStatement("UPDATE DBUSR.SAVINGS SET BALANCE = BALANCE" + amount +" WHERE ACC_NUM "+ accNum , Statement.RETURN_GENERATED_KEYS);
          PreparedStatement sqlStatement2 = dbConnection.prepareStatement("INSERT INTO DBUSR.TRANSACTIONS (C_ID, ACC_NUM, AMOUNT, DESCRIPTION)" + "VALUES(?,?,?.?)", Statement.RETURN_GENERATED_KEYS);
          
          sqlStatement2.setInt(1, C_ID);
          sqlStatement2.setString(2, accNum);
          sqlStatement2.setInt(3, amount);
          sqlStatement2.setString(4, desc);
          
          sqlStatement.executeUpdate();
          //sqlStatement.execute();
          
          
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not make a deposit.");
                  sqlException.printStackTrace();
                }
    }
    
    @Override
    public void withdrawl(int C_ID, String accNum, int amount, String desc)
    {
        
        
    }
    
}
