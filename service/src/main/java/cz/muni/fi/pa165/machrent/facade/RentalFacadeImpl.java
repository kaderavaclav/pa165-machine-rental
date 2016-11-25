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
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
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
    public RentalDto getRentalWithId(Long id) {
        Rental rental = rentalService.findById(id);
        return (rental == null) ? null : beanMappingService.mapTo(rental, RentalDto.class);
    }

    @Override
    public List<RentalDto> getAllRentals() {
        return beanMappingService.mapTo(rentalService.findAll(), RentalDto.class);
    }

    @Override
    public Long createRental(RentalCreateDto r) {
        Rental mappedRental = beanMappingService.mapTo(r, Rental.class);
        Date now = new Date();
        mappedRental.setDateCreated(now);
        mappedRental.setDateStart(now);
        mappedRental.setDateEnd(now);
        mappedRental.setNote(new String());
        mappedRental.setEmployee(rentalUserService.findUserById(r.getEmployeeId()));
        mappedRental.setCustomer(rentalUserService.findUserById(r.getCustomerId()));
        mappedRental.setMachine(machineService.findById(r.getMachineId()));
        Rental newRental = rentalService.createRental(mappedRental);
        return newRental.getId();
    }

    @Override
    public void deleteRental(Long rentalId) {
        Rental rental = rentalService.findById(rentalId);
        
        if (rental == null) 
            throw new RentalServiceException("Machine with id: " + rentalId + " doesn't exist!"); 
        
        rentalService.deleteRental(new Rental(rentalId)); 
    }
}
