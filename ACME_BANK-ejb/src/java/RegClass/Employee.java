/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RegClass;

import java.io.Serializable;

/**
 *
 * @author Vextroid
 */
public class Employee implements Serializable{
    
    public String C_ID;
    public String firstName;
    public String lastName;
    
    public Employee(String fName, String lName)
    {
        //C_ID = id;
        firstName = fName;
        lastName = lName;
        //dob = dateOfBirth;
        //this.address = address;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
}
