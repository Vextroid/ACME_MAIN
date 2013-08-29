/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import RegClass.Transactions;

/**
 *
 * @author Vextroid
 */
public interface TransactionDAO {
    
    public void deposit(Transactions transaction);
    public void withdrawl(Transactions transaction);
    
}
