/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vextroid
 */
@Entity
@Table(name = "SAVINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Savings.findAll", query = "SELECT s FROM Savings s"),
    @NamedQuery(name = "Savings.findByAccNum", query = "SELECT s FROM Savings s WHERE s.accNum = :accNum"),
    @NamedQuery(name = "Savings.findByBalance", query = "SELECT s FROM Savings s WHERE s.balance = :balance")})
public class Savings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACC_NUM")
    private String accNum;
    @Column(name = "BALANCE")
    private Integer balance;
    @JoinColumn(name = "C_ID", referencedColumnName = "C_ID")
    @ManyToOne
    private Customer cId;

    public Savings() {
    }

    public Savings(String accNum) {
        this.accNum = accNum;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Customer getCId() {
        return cId;
    }

    public void setCId(Customer cId) {
        this.cId = cId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accNum != null ? accNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Savings)) {
            return false;
        }
        Savings other = (Savings) object;
        if ((this.accNum == null && other.accNum != null) || (this.accNum != null && !this.accNum.equals(other.accNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Savings[ accNum=" + accNum + " ]";
    }
    
}
