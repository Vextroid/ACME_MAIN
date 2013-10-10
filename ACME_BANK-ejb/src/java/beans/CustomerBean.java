/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import RegClass.Customer;
import DAO.CustomerDAO;
import RDB.RDBCustomerDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
//import javax.activation.DataSource;
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
public class CustomerBean implements CustomerBeanRemote, Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

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
    public void addCustomer(String firstName, String lastName, Date dob, String address)
    {
        try{
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(firstName, lastName, dob, address);
            dao.createCustomer(customer);
        }catch(Exception e)
        {
            System.out.println("Could not create cusomter.");
            e.printStackTrace();
        }
        
    }
    
    @Override
    public String readCustomer (int id)    {
            String s;
            
        try{
            CustomerDAO dao = new RDBCustomerDAO(connection);
          //Employee employee = new Employee(id);
            //dao.createEmployee(employee);
            //return dao.readCustomer(id);
            
            //The Below worked before change back to this if things break.
            s = dao.readCustomer(id);
            return s;
           
            
            //return "TESTTTTTTTTTTTTTTTTTT";
            
        }catch(Exception e)
        {
            System.out.println("Invalid Customer.");
            e.printStackTrace();
            return e.toString();
        }
    
    }
}
