/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import RegClass.Customer;

/**
 *
 * @author s3248563
 */
public interface CustomerDAO {
    
    public void createCustomer(Customer customer);
    public String readCustomer(int id);
    public void updateCustomer(String id, Customer customer);
    public void removeCustomer(String id);
    public void getAllCustomer(Customer customer);
        
}
