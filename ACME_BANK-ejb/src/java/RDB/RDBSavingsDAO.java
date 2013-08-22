/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RDB;

import DAO.SavingsDAO;
import RegClass.SavingsAcc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vextroid
 */
public class RDBSavingsDAO implements SavingsDAO{
    
    private Connection dbConnection = null;
    private ArrayList employeeList = new ArrayList();
    
    public RDBSavingsDAO(Connection connection) {
        this.dbConnection = connection;    
    }
    

    
    @Override
    public void createSavingsAccount(SavingsAcc savings)
    {
        try{
                                                                        //INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES (1,'TRON234',1000);
          PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES)" + "VALUES(?,?,?)");
          
          sqlStatement.setInt(1, savings.C_ID);
          sqlStatement.setString(2, savings.accNum);
          sqlStatement.setInt(3, savings.balance);
          
          sqlStatement.executeUpdate();
          //sqlStatement.execute();
          
          
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not create savings account.");
                  sqlException.printStackTrace();
                }
        
    }
    
    @Override
    public String getAccountInfo(int id)
    {
        return "placeholder";
    }
    
    
    @Override
    public void makeDeposit(String id, int amount)
    {
        
    }
    
    @Override
    public void makeWithdrawl(String id)
    {
        
    }
    
    @Override
    public void getAllSavingsAccount()
    {
        
    }
    
    @Override
    public int getBalance(String accNum)
    {
        try{
            int bal = 0;
        
              
            //INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES (1,'TRON234',1000);
            //PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES)" + "VALUES(?,?,?)");
            //SELECT BALANCE FROM DBUSR.SAVINGS;
            //                                                             "SELECT FIRST_NAME FROM DBUSR.EMPLOYEE WHERE E_ID = "
            PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT BALANCE FROM DBUSR.SAVINGS WHERE ACC_NUM = " + accNum);

          
            //sqlStatement.setInt(0, savings.C_ID);
            //sqlStatement.setString(1, savings.accNum);
            //sqlStatement.setInt(2, savings.balance);
          
            //sqlStatement.executeQuery();
            //sqlStatement.execute();
            
            ResultSet result = sqlStatement.executeQuery();
            result.next();
            result.next();
            
            return result.getInt("BALANCE");
            
           // return bal; 
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not create savings account.");
                  sqlException.printStackTrace();
                  return 12312;
                }
    }
    
}
