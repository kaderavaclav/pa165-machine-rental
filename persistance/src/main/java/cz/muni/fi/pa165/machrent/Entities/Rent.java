/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Peter Benus
 */
@Entity
@Table (name = "Rent")
public class Rent implements Serializable {

    private Machine machine;
    private User customer;
    private String note;
    private User employee;
    private Date dateStart;
    private Date dateEnd;    
    private Date dateCreated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.machine != null ? this.machine.hashCode() : 0);
        hash = 97 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 97 * hash + (this.dateCreated != null ? this.dateCreated.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rent other = (Rent) obj;
        if (this.machine != other.machine && (this.machine == null || !this.machine.equals(other.machine))) {
            return false;
        }
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        if (this.dateCreated != other.dateCreated && (this.dateCreated == null || !this.dateCreated.equals(other.dateCreated))) {
            return false;
        }
        return true;
    }

    
    
    
}
