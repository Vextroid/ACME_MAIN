/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import RegClass.Employee;

/**
 *
 * @author Vextroid
 */
public interface EmployeeDAO {
    
    public void createEmployee(Employee employee);
    public String readEmployee(int id);
    public void updateEmployee(String id, Employee employee);
    public void removeEmployee(String id);
    public void getAllEmployee(Employee employee);
    
}
