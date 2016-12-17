/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.dto;

import java.util.Date;

/**
 *
 * @author Peter Benus
 */
public class RentalDto {
    
    private Long id;
    MachineDto machine;    
    RentalUserDto customer;
    private String note;
    private RentalUserDto employee;
    private Date dateStart;
    private Date dateEnd;    
    Date dateCreated;

    public RentalDto(){
        
    }
    
    public RentalDto(RentalDto r){
        id = r.getId();
        machine = r.getMachine();
        customer = r.getCustomer();
        note = r.getNote();
        employee = r.getEmployee();
        dateStart = r.getDateStart();
        dateEnd = r.getDateEnd();
        dateCreated = r.getDateCreated();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MachineDto getMachine() {
        return machine;
    }

    public void setMachine(MachineDto machine) {
        this.machine = machine;
    }

    public RentalUserDto getCustomer() {
        return customer;
    }

    public void setCustomer(RentalUserDto customer) {
        this.customer = customer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RentalUserDto getEmployee() {
        return employee;
    }

    public void setEmployee(RentalUserDto employee) {
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
        int hash = 3;
        hash = 67 * hash + (this.machine != null ? this.machine.hashCode() : 0);
        hash = 67 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 67 * hash + (this.dateCreated != null ? this.dateCreated.hashCode() : 0);
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
        final RentalDto other = (RentalDto) obj;
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
