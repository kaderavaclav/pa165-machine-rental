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
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
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
        rentalService.deleteRental(new Rental(rentalId)); 
    }

    @Override
    public void setNote(Long rentalId, String note) {
        rentalService.setNote(rentalService.findById(rentalId), note);
    }

    @Override
    public String getNote(Long rentalId) {
        return rentalService.getNote(rentalService.findById(rentalId));
    }

    @Override
    public void setCustomer(Long rentalId, Long customerId) {
        rentalService.setCustomer(rentalService.findById(rentalId), rentalUserService.findUserById(customerId));
    }

    @Override
    public RentalUserDto getCustomer(Long rentalId) {
        RentalUser customer = rentalService.getCustomer(rentalService.findById(rentalId));
        return (customer == null) ? null : beanMappingService.mapTo(customer, RentalUserDto.class); 
    }

    @Override
    public void setEmployee(Long rentalId, Long employeeId) {
        rentalService.setEmployee(rentalService.findById(rentalId), rentalUserService.findUserById(employeeId));
    }

    @Override
    public RentalUserDto getEmployee(Long rentalId) {
        RentalUser employee = rentalService.getEmployee(rentalService.findById(rentalId));
        return (employee == null) ? null : beanMappingService.mapTo(employee, RentalUserDto.class);
    }

    @Override
    public void setMachine(Long rentalId, Long machineId) {
        rentalService.setMachine(rentalService.findById(rentalId), machineService.findById(machineId));
    }

    @Override
    public MachineDto getMachine(Long rentalId) {
        Machine machine = rentalService.getMachine(rentalService.findById(rentalId));
        return (machine == null) ? null : beanMappingService.mapTo(machine, MachineDto.class);
    }

    @Override
    public void setDateStart(Long rentalId, Date d) {
        rentalService.setDateStart(rentalService.findById(rentalId), d);
    }

    @Override
    public Date getDateStart(Long rentalId) {
        return rentalService.getDateStart(rentalService.findById(rentalId));
    }

    @Override
    public void setDateEnd(Long rentalId, Date d) {
        rentalService.setDateEnd(rentalService.findById(rentalId), d);
    }

    @Override
    public Date getDateEnd(Long rentalId) {
        return rentalService.getDateEnd(rentalService.findById(rentalId));
    }

    @Override
    public Date getDateCreate(Long rentalId) {
        return rentalService.getDateCreate(rentalService.findById(rentalId));
    }

}
