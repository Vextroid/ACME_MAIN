/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RDB;

import DAO.TransactionDAO;
import RegClass.Transactions;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vextroid
 */
public class RDBTransactionsDAO implements TransactionDAO, Serializable {
    
    private Connection dbConnection = null;
    private ArrayList transacationsList = new ArrayList();
    
    public RDBTransactionsDAO(Connection connection) {
        this.dbConnection = connection;    
    }
    
    @Override
    public void deposit(Transactions transactions)
    {
        
         try{
             int i= 0;
//            String acc = accNum;
//            int am = amount;
//            String description = desc;
           
//            PreparedStatement getBal = dbConnection.prepareStatement("SELECT * FROM DBUSR.SAVINGS WHERE C_ID = " + transactions.C_ID);       
//            getBal.executeQuery();
//            //sqlStatement.execute();
//            
//            ResultSet result = getBal.executeQuery();
//            while(result.next())
//            {
//                String resultAcc = result.getString("ACC_NUM");
//                if(transactions.acc.equals(resultAcc))
//                {
//                    i = result.getInt("BALANCE");
//                }
//            }
//             
//          int newAmount = i+transactions.amount;   
             
//             
//                                                                        //UPDATE DBUSR.SAVINGS SET BALANCE = BALANCE+100 WHERE ACC_NUM = 'ROTT2013';
            PreparedStatement sqlStatement = dbConnection.prepareStatement("UPDATE DBUSR.SAVINGS SET BALANCE = ? WHERE ACC_NUM = ? ");
            sqlStatement.setInt(1, transactions.amount);
            sqlStatement.setString(2, transactions.acc);
            sqlStatement.executeUpdate();
         
        PreparedStatement sqlStatement2 = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS      (C_ID, ACC_NUM, BALANCE)"       + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);   
          PreparedStatement inserter = dbConnection.prepareStatement("INSERT INTO DBUSR.TRANSACTIONS (ACC_NUM, AMOUNT, DESCRIPTION)" + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
          //sqlStatement2.setInt(1, C_ID);
          inserter.setString(1, transactions.acc);
          inserter.setInt(2, transactions.amount);
          inserter.setString(3, transactions.desc);
       
          inserter.executeUpdate();
          
          //ResultSet result = inserter.getGeneratedKeys();
          //result.next();
          //customer.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          //System.out.println("Customer" + customer.C_ID + "created.");
          
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not make a deposit.");
                  sqlException.printStackTrace();
                }
    }
    
    @Override
    public void withdrawl(Transactions transactions)
    {
                 try{
//            int id = C_ID;
//            String acc = accNum;
//            int am = amount;
//            String description = desc;
            
                                                                        //UPDATE DBUSR.SAVINGS SET BALANCE = BALANCE+100 WHERE ACC_NUM = 'ROTT2013';
            PreparedStatement sqlStatement = dbConnection.prepareStatement("UPDATE DBUSR.SAVINGS SET BALANCE = ? WHERE ACC_NUM = ? ");
            sqlStatement.setInt(1, transactions.amount);
            sqlStatement.setString(2, transactions.acc);
            sqlStatement.executeUpdate();
            
        //PreparedStatement sqlStatement2 = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS      (C_ID, ACC_NUM, BALANCE)"       + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);   
          PreparedStatement sqlStatement2 = dbConnection.prepareStatement("INSERT INTO DBUSR.TRANSACTIONS (ACC_NUM, AMOUNT, DESCRIPTION)" + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
          //sqlStatement2.setInt(1, C_ID);
          sqlStatement2.setString(1, transactions.acc);
          sqlStatement2.setInt(2, transactions.amount);
          sqlStatement2.setString(3, transactions.desc);
       
          sqlStatement2.executeUpdate();
          
          ResultSet result = sqlStatement2.getGeneratedKeys();
          result.next();
          //customer.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          //System.out.println("Customer" + customer.C_ID + "created.");
          
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not make a deposit.");
                  sqlException.printStackTrace();
                }
        
    }
    
    @Override
    public List viewAllTransactions(String accNum)
    {
                 try{

        //PreparedStatement sqlStatement2 = dbConnection.prepareStatement("INSERT INTO DBUSR.SAVINGS      (C_ID, ACC_NUM, BALANCE)"       + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);   
          PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT * FROM DBUSR.TRANSACTIONS WHERE ACC_NUM =" + accNum, Statement.RETURN_GENERATED_KEYS);
          
          //sqlStatement2.setInt(1, C_ID);
          //sqlStatement2.setString(1, transactions.acc);
          //sqlStatement2.setInt(2, transactions.amount);
          //sqlStatement2.setString(3, transactions.desc);
       
          sqlStatement.executeUpdate();
          
          ResultSet result = sqlStatement.getGeneratedKeys();
          result.next();
          
             while (result.next())
          {
              transacationsList.add(result);
          }
          //customer.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          //System.out.println("Customer" + customer.C_ID + "created.");
          return transacationsList;
        }
        catch (SQLException sqlException)
                {
                  System.out.println("Could not make a deposit.");
                  sqlException.printStackTrace();
                  return transacationsList;
                }
        
    }
    
}
