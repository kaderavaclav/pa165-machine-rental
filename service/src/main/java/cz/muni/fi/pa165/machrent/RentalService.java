/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Machine;
import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Peter Benus
 */
@Service
public interface RentalService {

    /**
     * Method that returns {@code Rental} with specified {@code Long id}.
     * @param id to find
     * @return Rental object
     */
    public Rental findById(Long id);

    /**
     * Method that returns all {@code Rental} objects in database.
     * @return List of Rental objects.
     */
    public List<Rental> findAll();

    /**
     * Method that creates {@code Rental} in database.
     * @param r object to create
     * @return Created Rental object.
     */
    public Rental createRental(Rental r);

    /**
     * Method that remove {@code Rental} from database.
     * @param r object to delete
     */
    public void deleteRental(Rental r);

    /**
     * Method that sets note of {@code Rental} object.
     * @param r Rental object
     * @param note note for this object
     */
    public void setNote(Rental r, String note);

    /**
     * Method that returns note of (@code Rental} object.
     * @param r object to get note from
     * @return object's note
     */
    public String getNote(Rental r);

    /**
     * Method that sets customer of {@code  Rental} object.
     * @param r Rental object
     * @param customer customer for this object
     */
    public void setCustomer(Rental r, RentalUser customer);

    /**
     * Method that returns customer of {@code Rental} object.
     * @param r Rental object
     * @return object's customer
     */
    public RentalUser getCustomer(Rental r);

    /**
     * Method that sets employee of {@code Rental} object.
     * @param r Rental object
     * @param employee employee for this object
     */
    public void setEmployee(Rental r, RentalUser employee);

    /**
     * Method that returns employee of {@code Rental} object.
     * @param r Rental object
     * @return object's employee
     */
    public RentalUser getEmployee(Rental r);

    /**
     * Method that sets machine for {@code Rental} object.
     * @param r Rental object
     * @param m machine for this object
     */
    public void setMachine(Rental r, Machine m);

    /**
     * Method that returns machine of {@code Rental} object.
     * @param r Rental object
     * @return object's machine
     */
    public Machine getMachine(Rental r);

    /**
     * Method that sets starting date of {@code Rental} object
     * @param r Rental object
     * @param d date of start
     */
    public void setDateStart(Rental r, Date d);

    /**
     * Method that returns starting date of {@code Rental} object
     * @param r Rental object
     * @return date of start
     */
    public Date getDateStart(Rental r);

    /**
     * Method that sets ending date of {@code Rental} object
     * @param r Rental object
     * @param d date of end
     */
    public void setDateEnd(Rental r, Date d);

    /**
     * Method that returns ending date of {@code Rental} object
     * @param r Rental object
     * @return date of end
     */
    public Date getDateEnd(Rental r);

    /**
     * Method that returns creation date of {@code Rental} object
     * @param r Rental object
     * @return date of creation
     */
    public Date getDateCreate(Rental r);
}
