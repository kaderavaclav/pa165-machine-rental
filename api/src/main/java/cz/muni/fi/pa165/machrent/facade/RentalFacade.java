/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Peter Benus
 */
public interface RentalFacade {
    public RentalDto getRentalWithId(Long id);
    public List<RentalDto> getAllRentals();
    public Long createRental(RentalCreateDto r);
    public void deleteRental(Long rentalId);
    public void setNote(Long rentalId, String note);
    public String getNote(Long rentalId);
    public void setCustomer(Long rentalId, Long customerId);
    public Long getCustomer(Long rentalId);
    public void setEmployee(Long rentalId, Long employeeId);
    public Long getEmployee(Long rentalId);
    public void setMachine(Long rentalId, Long machineId);
    public Long getMachine(Long rentalId);
    public void setDateStart(Long rentalId, Date d);
    public Date getDateStart(Long rentalId);
    public void setDateEnd(Long rentalId, Date d);
    public Date getDateEnd(Long rentalId);
    public Date getDateCreate(Long rentalId);
}
