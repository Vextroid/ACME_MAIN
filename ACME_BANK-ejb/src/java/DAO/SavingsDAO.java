/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import RegClass.SavingsAcc;


/**
 *
 * @author Vextroid
 */
public interface SavingsDAO {
    
    public void createSavingsAccount(SavingsAcc savings);
    public String getAccountInfo(int id);
    //public void makeDeposit(String id, int amount);
    //public void makeWithdrawl(String id);
    public void getAllSavingsAccount();
    public int getBalance(int id, String accNum);
    public boolean maxSavings(int C_ID);
    
}
