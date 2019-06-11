/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.pi17b.karves;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Simonas
 */
@Entity
@Table(name = "milk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Milk.findAll", query = "SELECT m FROM Milk m"),
    @NamedQuery(name = "Milk.findById", query = "SELECT m FROM Milk m WHERE m.id = :id"),
    @NamedQuery(name = "Milk.findByAmount", query = "SELECT m FROM Milk m WHERE m.amount = :amount"),
    @NamedQuery(name = "Milk.findByFat", query = "SELECT m FROM Milk m WHERE m.fat = :fat"),
    @NamedQuery(name = "Milk.findByLactose", query = "SELECT m FROM Milk m WHERE m.lactose = :lactose")})
public class Milk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fat")
    private double fat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lactose")
    private double lactose;

    public Milk() {
    }

    public Milk(Integer id) {
        this.id = id;
    }

    public Milk(Integer id, double amount, double fat, double lactose) {
        this.id = id;
        this.amount = amount;
        this.fat = fat;
        this.lactose = lactose;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getLactose() {
        return lactose;
    }

    public void setLactose(double lactose) {
        this.lactose = lactose;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Milk)) {
            return false;
        }
        Milk other = (Milk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.viko.pi17b.karves.Milk[ id=" + id + " ]";
    }
    
}
