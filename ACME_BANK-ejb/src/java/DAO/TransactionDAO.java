/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Vextroid
 */
public interface TransactionDAO {
    
    public void deposit(int C_ID, String accNum, int amount, String desc);
    public void withdrawl(int C_ID, String accNum, int amount, String desc);
    
}
