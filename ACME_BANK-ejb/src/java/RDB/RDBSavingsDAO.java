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
import java.sql.Statement;
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
            //int id = savings.C_ID;
            //String acc = savings.accNum;
            //int bal = savings.balance;
            
                                                                        //INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES (1,'TRON234',1000);
          PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE)" + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
          sqlStatement.setInt(0, savings.C_ID);
          sqlStatement.setString(1, savings.accNum);
          sqlStatement.setInt(2, savings.balance);
          
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
    
    
//    @Override
//    public void makeDeposit(String id, int amount)
//    {
//        
//    }
//    
//    @Override
//    public void makeWithdrawl(String id)
//    {
//        
//    }
    
    @Override
    public void getAllSavingsAccount()
    {
        
    }
    
    @Override
    public int getBalance(String accNum)
    {
        try{
            String bal = "ACE123";
        
              
            //INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES (1,'TRON234',1000);
            //PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES)" + "VALUES(?,?,?)");
            //SELECT BALANCE FROM DBUSR.SAVINGS;
            //                                                             "SELECT FIRST_NAME FROM DBUSR.EMPLOYEE WHERE E_ID = "
            PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT * FROM DBUSR.SAVINGS WHERE ACC_NUM = " + bal);

          
            //sqlStatement.setInt(0, savings.C_ID);
            //sqlStatement.setString(1, savings.accNum);
            //sqlStatement.setInt(2, savings.balance);
          
            //sqlStatement.executeQuery();
            //sqlStatement.execute();
            
            ResultSet result = sqlStatement.executeQuery();
            result.next();
            result.next();
            result.next();
            return result.getInt("BALANCE");
            
           // return bal; 
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not create savings account.");
                  sqlException.printStackTrace();
                  return -99999;
                }
    }
    
    @Override
    public boolean maxSavings(int C_ID)
    {
          try{
          int count =0;          
                                                                        //INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES (1,'TRON234',1000);
          PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT * FROM DBUSR.SAVINGS WHERE C_ID = " + C_ID);
          
          //sqlStatement.setInt(1, savings.C_ID);
          //sqlStatement.setString(2, savings.accNum);
          //sqlStatement.setInt(3, savings.balance);
          
          ResultSet result = sqlStatement.executeQuery();
          
          //sqlStatement.executeQuery();
          //sqlStatement.execute();
          
          while(result.next())
          {
              count ++;
          }
          
          if (count <2)
          {
          return true;
          }
          else
          {
              return false;
          }
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not create savings account.");
                  sqlException.printStackTrace();
                  return false;
                }
    }
    
    
    
}
