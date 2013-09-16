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
@Table(name = "HOMELOAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Homeloan.findAll", query = "SELECT h FROM Homeloan h"),
    @NamedQuery(name = "Homeloan.findByAccNum", query = "SELECT h FROM Homeloan h WHERE h.accNum = :accNum"),
    @NamedQuery(name = "Homeloan.findByAmountBorrowed", query = "SELECT h FROM Homeloan h WHERE h.amountBorrowed = :amountBorrowed"),
    @NamedQuery(name = "Homeloan.findByAmountRepayed", query = "SELECT h FROM Homeloan h WHERE h.amountRepayed = :amountRepayed")})
public class Homeloan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACC_NUM")
    private String accNum;
    @Column(name = "AMOUNT_BORROWED")
    private Integer amountBorrowed;
    @Column(name = "AMOUNT_REPAYED")
    private Integer amountRepayed;
    @JoinColumn(name = "C_ID", referencedColumnName = "C_ID")
    @ManyToOne
    private Customer cId;

    public Homeloan() {
    }

    public Homeloan(String accNum) {
        this.accNum = accNum;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public Integer getAmountBorrowed() {
        return amountBorrowed;
    }

    public void setAmountBorrowed(Integer amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }

    public Integer getAmountRepayed() {
        return amountRepayed;
    }

    public void setAmountRepayed(Integer amountRepayed) {
        this.amountRepayed = amountRepayed;
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
        if (!(object instanceof Homeloan)) {
            return false;
        }
        Homeloan other = (Homeloan) object;
        if ((this.accNum == null && other.accNum != null) || (this.accNum != null && !this.accNum.equals(other.accNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Homeloan[ accNum=" + accNum + " ]";
    }
    
}
