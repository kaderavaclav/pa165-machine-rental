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

    /**
     *  Returns ID of this rent object.
     * 
     * @return ID of rent object
     */
    public Long getId() {
        return id;
    }
    
    /**
     *  Returns rented machine by customer in this object.
     * 
     * @return rented machine 
     */
    public Machine getMachine() {
        return machine;
    }

    /**
     *  Sets the rented machine to this rent object.
     * 
     * @param machine rented machine in this rent
     */
    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    /**
     * Returns customer who ordered this rent of machine in this object.
     * 
     * @return customer who ordered this rent
     */
    public RentalUser getCustomer() {
        return customer;
    }

    /**
     * Sets customer who ordered this rent of machine in this object.
     *
     * @param customer who ordered this rent
     */
    public void setCustomer(RentalUser customer) {
        this.customer = customer;
    }

    /**
     * Returns note saved to this rent.
     *
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets note for this rent.
     *
     * @param note note for this rent
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Returns employee who created this rent in system.
     * 
     * @return employee who created rent
     */
    public RentalUser getEmployee() {
        return employee;
    }

    /**
     * Sets employee who created this rent in system.
     * 
     * @param employee employee who created rent
     */
    public void setEmployee(RentalUser employee) {
        this.employee = employee;
    }

    /**
     * Returns first day of this rent.
     * 
     * @return first day of rent
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     *  Sets first day of this rent.
     * 
     * @param dateStart first day of rent
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Returns last day of this rent.
     * 
     * @return last day of rent
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets last day of this rent.
     * 
     * @param dateEnd last day of rent
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Returns the day when this rent was ordered.
     *
     * @return day of order this rent
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the day when this rent was ordered.
     * 
     * @param dateCreated day of order this rent
     */
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
