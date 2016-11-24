/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Peter Benus
 */
public interface RentalFacade {

    /**
     * Method that returns {@code RentalDto} with specified {@code Long id}.
     * @param id to find
     * @return RentalDto object
     */
    public RentalDto getRentalWithId(Long id);

    /**
     * Method that returns list of {@code RentalDto}.
     * @return list of RentalDto objects
     */
    public List<RentalDto> getAllRentals();

    /**
     * Method that creates {@code Rental} in database.
     * @param r object to create
     * @return id of created Rental object.
     */
    public Long createRental(RentalCreateDto r);

    /**
     * Method that remove {@code Rental} from database.
     * @param rentalId id of object to delete
     */
    public void deleteRental(Long rentalId);

    /**
     * Method that sets note of {@code Rental} object.
     * @param rentalId id of Rental object
     * @param note note for this object
     */
    public void setNote(Long rentalId, String note);

    /**
     * Method that returns note of (@code Rental} object.
     * @param rentalId id of Rental object
     * @return note of this object
     */
    public String getNote(Long rentalId);

    /**
     * Method that sets customer of {@code  Rental} object.
     * @param rentalId id of Rental object
     * @param customerId id of customer for this object
     */
    public void setCustomer(Long rentalId, Long customerId);

    /**
     * Method that returns customer of {@code Rental} object.
     * @param rentalId id of Rental object
     * @return object's customer
     */
    public RentalUserDto getCustomer(Long rentalId);

    /**
     * Method that sets employee of {@code Rental} object.
     * @param rentalId id of Rental object
     * @param employeeId id of employee for this object
     */
    public void setEmployee(Long rentalId, Long employeeId);

    /**
     * Method that returns employee of {@code Rental} object.
     * @param rentalId id of Rental object
     * @return object's employee
     */
    public RentalUserDto getEmployee(Long rentalId);

    /**
     * Method that sets machine for {@code Rental} object.
     * @param rentalId id of Rental object
     * @param machineId id of machine for this object
     */
    public void setMachine(Long rentalId, Long machineId);

    /**
     * Method that returns machine for {@code Rental} object.
     * @param rentalId id of Rental object
     * @return object's machine
     */
    public MachineDto getMachine(Long rentalId);

    /**
     * Method that sets starting date for {@code Rental} object.
     * @param rentalId id of Rental object
     * @param d date of start
     */
    public void setDateStart(Long rentalId, Date d);

    /**
     * Method that returns starting date for {@code Rental} object.
     * @param rentalId id of Rental object
     * @return date of start
     */
    public Date getDateStart(Long rentalId);

    /**
     * Method that sets ending date for {@code Rental} object.
     * @param rentalId id of Rental object
     * @param d date of end
     */
    public void setDateEnd(Long rentalId, Date d);

    /**
     * Method that returns ending date for {@code Rental} object.
     * @param rentalId id of Rental object
     * @return date of end
     */
    public Date getDateEnd(Long rentalId);

    /**
     * Method that sets creation date for {@code Rental} object.
     * @param rentalId id of Rental object
     * @return creation date
     */
    public Date getDateCreate(Long rentalId);
}
