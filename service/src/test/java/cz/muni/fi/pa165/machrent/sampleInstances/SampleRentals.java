package cz.muni.fi.pa165.machrent.sampleInstances;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.exceptions.RentalServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Collection of sample entities used in various tests.
 * 
 * @author Josef Plch
 * @since  2016-11-24
 * @since  2016-11-25
 */
public abstract class SampleRentals {

    @Autowired
    private BeanMappingService beanMappingService;

    public static Rental newRental (Long rentalId, RentalUser customer, RentalUser employee, Machine machine) {

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");

        Rental rental = new Rental (rentalId);
        rental.setCustomer    (customer);

        try {
            rental.setDateCreated (formater.parse("2000-01-01"));
            rental.setDateEnd     (formater.parse("2000-04-18"));
            rental.setDateStart   (formater.parse("2000-04-14"));
        }
        catch (ParseException ex){
            throw new RentalServiceException(ex);
        }


        rental.setEmployee    (employee);
        rental.setMachine     (machine);
        rental.setNote        ("This is a sample note.");
        return rental;
    }
    
    public static RentalCreateDto newRentalCreateDto (Long customerId, Long employeeId, Long machineId) {
        Rental rental = newRentalOfBixMaxByCharlie ();
        RentalCreateDto rentalCreateDto = new RentalCreateDto ();
        rentalCreateDto.setId(rental.getId());
        rentalCreateDto.setCustomerId  (customerId);
        rentalCreateDto.setDateCreated (rental.getDateCreated ());
        rentalCreateDto.setDateEnd     (rental.getDateEnd ());
        rentalCreateDto.setDateStart   (rental.getDateStart ());
        rentalCreateDto.setEmployeeId  (employeeId);
        rentalCreateDto.setMachineId   (machineId);
        rentalCreateDto.setNote        (rental.getNote ());
        return rentalCreateDto;
    }
    
    public static RentalDto newRentalDto (Long rentalDtoId, RentalUserDto customerDto, RentalUserDto employeeDto, MachineDto machineDto) {
        Rental rental = newRentalOfBixMaxByCharlie ();
        RentalDto rentalDto = new RentalDto ();
        rentalDto.setId(rentalDtoId);
        rentalDto.setCustomer    (customerDto);
        rentalDto.setDateCreated (rental.getDateCreated ());
        rentalDto.setDateEnd     (rental.getDateEnd ());
        rentalDto.setDateStart   (rental.getDateStart ());
        rentalDto.setEmployee    (employeeDto);
        rentalDto.setMachine     (machineDto);
        rentalDto.setNote        (rental.getNote ());
        return rentalDto;
    }
    
    public static Rental newRentalOfBixMaxByCharlie () {
        return SampleRentals.newRental (1L,
            SampleRentalUsers.newCustomerCharlie (),
            SampleRentalUsers.newEmployeeEdward (),
            SampleMachines.newMachineBigMax ()
        );
    }
    
    public static RentalDto newRentalOfBixMaxByCharlieDto () {
        return SampleRentals.newRentalDto (1L,
            SampleRentalUsers.newCustomerCharlieDto (),
            SampleRentalUsers.newEmployeeEdwardDto (),
            SampleMachines.newMachineBigMaxDto ()
        );
    }
}
