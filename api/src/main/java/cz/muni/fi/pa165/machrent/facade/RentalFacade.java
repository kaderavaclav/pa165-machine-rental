/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
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
}
