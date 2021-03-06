/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.MachineService;
import cz.muni.fi.pa165.machrent.RentalService;
import cz.muni.fi.pa165.machrent.RentalUserService;
import cz.muni.fi.pa165.machrent.dto.MachineUpdateDto;
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUpdateDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.exceptions.RentalServiceException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Benus
 */
@Service
@Transactional
public class RentalFacadeImpl implements RentalFacade {

    @Inject
    private RentalService rentalService;

    @Inject
    private RentalUserService rentalUserService;

    @Inject
    private MachineService machineService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public RentalDto findRentalWithId(Long id) {
        Rental rental = rentalService.findById(id);

        if (rental == null) {
            throw new RentalServiceException("Machine with id: " + id + " doesn't exist!");
        } else {
            return beanMappingService.mapTo(rental, RentalDto.class);
        }
    }

    @Override
    public List<RentalDto> findAllRentals() {
        return beanMappingService.mapTo(rentalService.findAll(), RentalDto.class);
    }

    @Override
    public Long createRental(RentalCreateDto r) {
        Rental mappedRental = beanMappingService.mapTo(r, Rental.class);
        Rental newRental = rentalService.createRental(mappedRental);
        return newRental.getId();
    }

    @Override
    public void deleteRental(Long rentalId) {
        Rental rental = rentalService.findById(rentalId);

        if (rental == null) {
            throw new RentalServiceException("Machine with id: " + rentalId + " doesn't exist!");
        }

        rentalService.deleteRental(rental);
    }

    @Override
    public List<RentalDto> findAllCreatedBetween(Date from, Date to) {
        List<Rental> rentals = rentalService.findAllCreatedBetween(from, to);

        if (rentals == null) {
            throw new RentalServiceException("There are no Rentals within specified interval!");
        } else {
            return beanMappingService.mapTo(rentals, RentalDto.class);
        }
    }

    @Override
    public List<RentalDto> findAllEffectiveBetween(Date from, Date to) {
        List<Rental> rentals = rentalService.findAllEffectiveBetween(from, to);

        if (rentals == null) {
            throw new RentalServiceException("There are no Rentals within specified interval!");
        } else {
            return beanMappingService.mapTo(rentals, RentalDto.class);
        }
    }

    @Override
    public void updateRental(RentalUpdateDto r) {
        Rental rental = beanMappingService.mapTo(r, Rental.class);
        rentalService.updateRental(rental);
        
    }

    @Override
    public List<RentalDto> findAllByCustomerId(long customerId) {
        List<Rental> rentals = rentalService.findAllByCustomerId(customerId);

        if (rentals == null) {
            throw new RentalServiceException("There are no Rentals with this customerId!");
        } else {
            return beanMappingService.mapTo(rentals, RentalDto.class);
        }
    }

}
