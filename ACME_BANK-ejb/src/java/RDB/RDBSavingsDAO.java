/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RDB;

import DAO.SavingsDAO;
import RegClass.SavingsAcc;
import java.io.Serializable;
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
public class RDBSavingsDAO implements SavingsDAO, Serializable{
    
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
          PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE)" + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
          //PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES" 
          
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
    public int getBalance(int id, String accNum)
    {
        //THIS WORKS DONT TOUCH!!!
        int i = 10;
        String acc = accNum;
        try{
            //String acc;
        
              
            //INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES (1,'TRON234',1000);
            //PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS (C_ID, ACC_NUM, BALANCE) VALUES)" + "VALUES(?,?,?)");
            //SELECT BALANCE FROM DBUSR.SAVINGS;
            //                                                      "SELECT FIRST_NAME FROM DBUSR.EMPLOYEE WHERE E_ID = " //Wokrs if its C_ID and not ACC_NUM
            PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT * FROM DBUSR.SAVINGS WHERE C_ID = " + id);

          
            //sqlStatement.setInt(0, savings.C_ID);
            //sqlStatement.setString(1, savings.accNum);
            //sqlStatement.setInt(2, savings.balance);
          
            sqlStatement.executeQuery();
            //sqlStatement.execute();
            
            ResultSet result = sqlStatement.executeQuery();
            while(result.next())
            {
                String resultAcc = result.getString("ACC_NUM");
                if(acc.equals(resultAcc))
                {
                    i = result.getInt("BALANCE");
                }
            }
            
//            result.next();
//            result.next();
             //i = result.getInt("BALANCE");
//            int i = 10;
            return i;
            
            
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
