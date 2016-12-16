/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent;

import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.machrent.entities.Rental;
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
     * Method that returns list of {@code Rental} objects between specified dates.
     * @param startDate first date of acceptable creation date in Rental object
     * @param endDate last date of acceptable creation date in Rental object
     * @return list of Rental objects
     */
    public List<Rental> findAllCreatedBetween(Date startDate, Date endDate);
    
    /**
     * Find all rentals which are in effect between given dates.
     * 
     * @param startDate First date of the interval.
     * @param endDate   Last date of the interval.
     * @return          Effective rentals.
     */
    public List<Rental> findAllEffectiveBetween(Date startDate, Date endDate);
    
}
