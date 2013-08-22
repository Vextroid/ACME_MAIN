/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RDB;


import DAO.EmployeeDAO;
import RegClass.Employee;
import java.sql.Connection;
//import data_access.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vextroid
 */
public class RDBEmployeeDAO implements EmployeeDAO{
    
    
    private Connection dbConnection = null;
    private ArrayList employeeList = new ArrayList();
    
    public RDBEmployeeDAO(Connection connection) {
        this.dbConnection = connection;    
    }
    
    @Override
    public void createEmployee(Employee employee)
    {
      try{
          PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.EMPLOYEE(FIRST_NAME, LAST_NAME)" + "VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
          
          //sqlStatement.setString(1, customer.C_ID);(3, customer.DOB);
          sqlStatement.setString(1, employee.firstName);
          sqlStatement.setString(2, employee.lastName);
          //sqlStatement.setDate(3, customer.DOB);
          //sqlStatement.setDate(3, customer.dob);
          //sqlStatement.setString(4, customer.address);
          
          sqlStatement.executeUpdate();
          
          ResultSet result = sqlStatement.getGeneratedKeys();
          result.next();
          employee.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          System.out.println("Customer" + employee.C_ID + "created.");
      }catch (SQLException sqlException)
      {
          System.out.println("Could not add new customer.");
          sqlException.printStackTrace();
    }
    
    }
    
    //DONE
    @Override
    public String readEmployee(int id){
        
        //Employee e = null;
        //String newId = null;
        //newId = id;
        //String id = "Default";
        //boolean isOk = false;
          try{
          PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT FIRST_NAME FROM DBUSR.EMPLOYEE WHERE E_ID = " + id);
          
          //SELECT FIRST_NAME, LAST_NAME FROM ABC.EMPLOYEE
          //SELECT * FROM ABC.EMPLOYEE WHERE E_ID =(1)
          //sqlStatement.setString(1, id);
          //sqlStatement.
          //sqlStatement.setString(1, customer.FIRST_NAME);
          //sqlStatement.setString(2, customer.LAST_NAME);
          //sqlStatement.setString(4, customer.DOB);
          //sqlStatement.setString(5, customer.ADDRESS);
          
            //sqlStatement.executeQuery();
          
          //String result = sqlStatement.executeQuery().toString();
          
          ResultSet result = sqlStatement.executeQuery();
         //String result = sqlStatement.toString();
          //ResultSet result = sqlStatement.
          
          //Customer c = new Customer(result.getString(1),result.getString(2),result.getDate(3),result.getString(4));
//          while (result.next())
//          {
//              int check = result.getInt("ID");
//              if(check == id)
//                {
//                    e = new Employee(result.getString(2),result.getString(3));
//                    isOk = true;
//                    return e.getFirstName;
//                }
//          }
          //id = result.getString(1) + result.getString(2);
          
          result.next();
          
          //e = result.next();
          //customer.FIRST_NAME = result.getString(1);
          //customer.LAST_NAME = result.getString(2);
          //return e;
          //System.out.println("Employee: " + e);
          //System.out.println("!!!!!!!!!!!!!!!!!");
          
          //int ID = id;
          //String fName = result.getString("FIRST_NAME");
          //String fName = "John";
          
          //String lName = result.getString(3);
          //fName += lName;
          //return e.getFirstName();
          //return fName + lName;
          
          return result.getString("FIRST_NAME");
          //return fName;
          //return "John";
          
          //return result.toString();
          //return "IT MADE IT THE SECTION WITH THE SQL COMMAND!";
         }catch (SQLException sqlException)
      {
          System.out.println("Invalid Employee.");
          sqlException.printStackTrace();
          return sqlException.toString();
          //return "NULL";
    }
          
//       if (e == null)
//       {
//           return null;
//       }
//       else
       //return "TEXTXTXTXTTTTTTXXTXTXT";
          //return fName;
    }
    
    
    
    @Override
    public void updateEmployee(String id, Employee employee ){
        
              try{
          //PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO ACME.CUSTOMER(C_ID, FIRST_NAME, LAST_NAME, DOB, ADDRESS)" + "VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          PreparedStatement sqlStatement2 = dbConnection.prepareStatement("DELETE FROM DBUSR.CUSTOMER WHERE C_ID = (id)");
          
          //sqlStatement2.setString(1, customer.FIRST_NAME);
          //sqlStatement2.setString(2, customer.LAST_NAME);
          
          sqlStatement2.executeUpdate();
          
          PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO DBUSR.CUSTOMER(FIRST_NAME, LAST_NAME, DOB, ADDRESS)" + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

          sqlStatement.setString(1, employee.firstName);
          sqlStatement.setString(2, employee.lastName);
//          sqlStatement.setDate(3, customer.dob);
//          sqlStatement.setString(4, customer.address);
          
          sqlStatement.executeUpdate();

          
          
          ResultSet result = sqlStatement.getGeneratedKeys();
          result.next();
          employee.C_ID = result.getString(1);
          System.out.println("Customer: " + employee.C_ID + "updated.");
      }catch (SQLException sqlException)
      {
          System.out.println("Could not update customer.");
          sqlException.printStackTrace();
    }
        
    }
    
    //DONE
    @Override
    public void removeEmployee(String id){
        
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
    public void getAllEmployee(Employee employee)
    {
        
         //put in a list or collection of somesort.
              try{
          PreparedStatement sqlStatement = dbConnection.prepareStatement("SELECT * FROM DBUSR.CUSTOMER", Statement.RETURN_GENERATED_KEYS);
          
          //sqlStatement.setString(1, customer.C_ID);
          //sqlStatement.setString(2, customer.FIRST_NAME);
          //sqlStatement.setString(3, customer.LAST_NAME);
          //sqlStatement.setString(4, customer.DOB);
          //sqlStatement.setString(5, customer.ADDRESS);
          
          sqlStatement.executeQuery();
          
          ResultSet result = sqlStatement.getResultSet();
          result.next();
          employeeList.add(result);
          //customer.C_ID = result.getString(1);
          //customer.FIRST_NAME = result.getString(2);
          //customer.LAST_NAME = result.getString(3);
          //customer.DOB = result.getString(4);
          //customer.ADDRESS = result.getString(5);
          System.out.println();
          System.out.println(employeeList);
      }catch (SQLException sqlException)
      {
          System.out.println("Could not show all customers.");
          sqlException.printStackTrace();
    }
    
    }
    
    
    
}
