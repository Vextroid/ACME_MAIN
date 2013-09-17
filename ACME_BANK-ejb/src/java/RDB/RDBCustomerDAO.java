/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RDB;

import DAO.CustomerDAO;
import RegClass.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author s3248563
 * 
 * USING LAB3 as a base for the assignment
 */
public class RDBCustomerDAO implements CustomerDAO{

    private Connection dbConnection = null;
    private ArrayList customerList = new ArrayList();
    
    public RDBCustomerDAO(Connection connection) {
        this.dbConnection = connection;        
        
        
    }
    
    //DONE!
    @Override
    public void createCustomer(Customer customer)
    {
      try{
          PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.CUSTOMER(FIRST_NAME, LAST_NAME, DOB, ADDRESS)" + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          
          //sqlStatement.setString(1, customer.C_ID);(3, customer.DOB);
          sqlStatement.setString(1, customer.firstName);
          sqlStatement.setString(2, customer.lastName);
          //sqlStatement.setDate(3, customer.DOB);
          sqlStatement.setDate(3, customer.dob);
          sqlStatement.setString(4, customer.address);
          
          sqlStatement.executeUpdate();
          
          ResultSet result = sqlStatement.getGeneratedKeys();
          result.next();
          customer.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          System.out.println("Customer" + customer.C_ID + "created.");
      }catch (SQLException sqlException)
      {
          System.out.println("Could not add new customer.");
          sqlException.printStackTrace();
    }
    
    }
    
    //DONE
    @Override
    public String readCustomer(int id){
        
         Customer c = null;
         
          try{
          //Customer c = null;
          
          PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT * FROM DBUSR.CUSTOMER WHERE C_ID =" + id);
           
          ResultSet result = sqlStatement.executeQuery();
 
          result.next();

          String fName = result.getString("FIRST_NAME");

          return fName;

      }catch (SQLException sqlException)
      {
          System.out.println("Could not find customer.");
          sqlException.printStackTrace();
          return sqlException.toString();
    }
        
    }
    
    //DONE
    @Override
    public void updateCustomer(String id, Customer customer){
        
              try{
          //PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.CUSTOMER(C_ID, FIRST_NAME, LAST_NAME, DOB, ADDRESS)" + "VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          PreparedStatement sqlStatement2 = dbConnection.prepareStatement("DELETE FROM DBUSR.CUSTOMER WHERE C_ID = (id)");
          
          //sqlStatement2.setString(1, customer.FIRST_NAME);
          //sqlStatement2.setString(2, customer.LAST_NAME);
          
          sqlStatement2.executeUpdate();
          
          PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.CUSTOMER(FIRST_NAME, LAST_NAME, DOB, ADDRESS)" + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

          sqlStatement.setString(1, customer.firstName);
          sqlStatement.setString(2, customer.lastName);
          sqlStatement.setDate(3, customer.dob);
          sqlStatement.setString(4, customer.address);
          
          sqlStatement.executeUpdate();

          
          
          ResultSet result = sqlStatement.getGeneratedKeys();
          result.next();
          customer.C_ID = result.getString(1);
          System.out.println("Customer: " + customer.C_ID + "updated.");
      }catch (SQLException sqlException)
      {
          System.out.println("Could not update customer.");
          sqlException.printStackTrace();
    }
        
    }
    
    //DONE
    @Override
    public void removeCustomer(String id){
        
              try{
          PreparedStatement sqlStatement = dbConnection.prepareStatement("DELETE FROM DBUSR.CUSTOMER WHERE C_ID = (id)");
          
          //sqlStatement.setString(1, customer.C_ID);
          //sqlStatement.setString(1, customer.FIRST_NAME);
          //sqlStatement.setString(2, customer.LAST_NAME);
          //sqlStatement.setString(4, customer.DOB);
          //sqlStatement.setString(5, customer.ADDRESS);
          
          sqlStatement.executeUpdate();
          
          //ResultSet result = sqlStatement.getGeneratedKeys();
          //result.next();
          //customer.C_ID = result.getString(1);
          System.out.println("Customer removed.");
      }catch (SQLException sqlException)
      {
          System.out.println("Could not remove customer.");
          sqlException.printStackTrace();
    }
        
    }
    
    @Override
    public void getAllCustomer(Customer customer)
    {
        
         //put in a list or collection of somesort.
              try{
          PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT * FROM DBUSR.CUSTOMER", Statement.RETURN_GENERATED_KEYS);
          

          
          sqlStatement.executeQuery();
          
          ResultSet result = sqlStatement.getResultSet();
          result.next();
          customerList.add(result);

          System.out.println();
          System.out.println(customerList);
      }catch (SQLException sqlException)
      {
          System.out.println("Could not show all customers.");
          sqlException.printStackTrace();
    }
    
    }
    


    
    
}
