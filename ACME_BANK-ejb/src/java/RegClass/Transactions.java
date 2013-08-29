/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RegClass;

/**
 *
 * @author Vextroid
 */
public class Transactions {
    
        public String C_ID;
    public String cid;
    public String acc;
    public int amount;
    public String desc;
    
    public Transactions(String accNum, int amount, String description)
    {
        //C_ID = id;
        //cid = C_ID;
        acc = accNum;
        this.amount = amount;
        desc = description;
    }
    
}
