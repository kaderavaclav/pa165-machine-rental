/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Rental;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Peter Benus
 */
public interface RentalDao {
    
    /**
     * Save created rent entity given by parameter into database.
     * 
     * @throws IllegalArgumentException when parameter is null
     * 
     * @param r rent to create
     */
    public void create(Rental r);

    /**
     * Remove given rent from database.
     * 
     * @throws IllegalArgumentException when parameter is null
     * 
     * @param r rent to delete
     */
    public void delete(Rental r); 

    /**
     * Update entry in database with new values given by rent entity in parameter.
     * 
     * @throws IllegalArgumentException when parameter is null
     * 
     * @param r rent to update
     */
    public void update(Rental r); 

    /**
     * Find rent with given ID in database.
     * 
     * @throws IllegalArgumentException when parameter is null
     * 
     * @param id ID to find
     * @return rent with given ID
     */
    public Rental findById (Long id);

    /**
     * Select all rents saved in database.
     * 
     * @return List of Rent entities.
     */
    public List<Rental> findAll();

    /**
     * Find all rents with given customerID in database.
     *
     * @throws IllegalArgumentException when parameter is null
     *
     * @param customerId ID of customer
     * @return all rentals with given customerID
     */
    public List<Rental> findAllByCustomerId(long customerId);
    
    public List<Rental> findAllCreatedBetween(Date startDate, Date endDate);
    
    public List<Rental> findAllEffectiveBetween(Date startDate, Date endDate);
}
