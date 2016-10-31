/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Peter Benus
 */
@Entity
@Table
public class Rent implements Serializable {

    @OneToOne
    private Machine machine;
    @OneToOne
    private RentalUser customer;
    private String note;
    @OneToOne
    private RentalUser employee;
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

    public RentalUser getCustomer() {
        return customer;
    }

    public void setCustomer(RentalUser customer) {
        this.customer = customer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RentalUser getEmployee() {
        return employee;
    }

    public void setEmployee(RentalUser employee) {
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
