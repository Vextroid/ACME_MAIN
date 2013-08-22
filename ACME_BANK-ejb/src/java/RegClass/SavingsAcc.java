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
public class SavingsAcc implements Serializable{
    
    public int C_ID;
    public String accNum;
    public int balance;
    
    public SavingsAcc(int id, String acc, int bal)
    {
        C_ID = id;
        accNum = acc;
        balance = bal;
    }
    
}
