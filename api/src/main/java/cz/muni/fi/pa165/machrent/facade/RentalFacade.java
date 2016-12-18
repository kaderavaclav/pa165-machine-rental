/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUpdateDto;

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
    public RentalDto findRentalWithId(Long id);

    /**
     * Method that returns list of {@code RentalDto}.
     * @return list of RentalDto objects
     */
    public List<RentalDto> findAllRentals();

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
    
    public void updateRental(RentalUpdateDto r);

    /**
     * Method that returns list of {@code Rental} objects with specified customerId.
     * @param customerId customerId
     * @return list of Rental objects
     */
    public List<RentalDto> findAllByCustomerId(long customerId);

    /**
     * Method that returns list of {@code RentalDto} created within specified interval.
     * @param from {@code Date} start of interval
     * @param to {@code Date} end of interval
     */
    public List <RentalDto> findAllCreatedBetween(Date from, Date to);

    /**
     * Method that returns list of {@code RentalDto} effected within specified interval.
     * @param from {@code Date} start of interval
     * @param to {@code Date} end of interval
     */
    public List <RentalDto> findAllEffectiveBetween(Date from, Date to);
}
