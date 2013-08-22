/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RegClass;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author s3248563
 */
public class Customer implements Serializable{
    
    public String C_ID;
    public String firstName;
    public String lastName;
    public Date dob;
    public String address;
    
    public Customer(String fName, String lName, Date dateOfBirth, String address)
    {
        //C_ID = id;
        firstName = fName;
        lastName = lName;
        dob = dateOfBirth;
        this.address = address;
    }
    
}
