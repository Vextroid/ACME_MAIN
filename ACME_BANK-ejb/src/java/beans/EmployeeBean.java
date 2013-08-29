/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.EmployeeDAO;
import RDB.RDBEmployeeDAO;
import RegClass.Employee;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import javax.sql.DataSource;

/**
 *
 * @author Vextroid
 */
@Stateful
public class EmployeeBean implements EmployeeBeanRemote, Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    private Connection connection;
    
    @PostConstruct
    public void initialize(){
        try{
            connection = dataSource.getConnection();
            
            //ADD THE TIME OUT THING HERE
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
    public void addEmployee(String firstName, String lastName)
    {
        try{
            EmployeeDAO dao = new RDBEmployeeDAO(connection);
            Employee employee = new Employee(firstName, lastName);
            dao.createEmployee(employee);
        }catch(Exception e)
        {
            System.out.println("Could not add employee.");
            e.printStackTrace();
        }
        
    }
    
    @Override
    public String readEmployee (int id)    {
            //String s = "John";
            
        try{
            EmployeeDAO dao = new RDBEmployeeDAO(connection);
          //Employee employee = new Employee(id);
            //dao.createEmployee(employee);
            return dao.readEmployee(id);
            //String s = dao.readEmployee(id);
           //s = dao.toString();
//            if (s.equals(null))
//            {
//                return "It failed";
//            }
//            else
            //{
            //return s;
            //}
            //return "TESTTTTTTTTTTTTTTTTTT";
            
        }catch(Exception e)
        {
            System.out.println("Invalid Employee.");
            e.printStackTrace();
            return e.toString();
        }

        
    }
    
}
