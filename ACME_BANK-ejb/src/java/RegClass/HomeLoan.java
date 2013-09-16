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
public class HomeLoan implements Serializable{
    
    public int C_ID;
    public String accNum;
    public int amountBorrowed;
    public int amountRepayed;
    
        public HomeLoan(int id, String acc, int amntB, int amountR)
    {
        C_ID = id;
        accNum = acc;
        amountBorrowed = amntB;
        amountRepayed = amountR;
    }
    
}
