package cz.muni.fi.pa165.machrent.sampleInstances;

import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.sql.Date;

/**
 * Collection of sample entities used in various tests.
 * 
 * @author Josef Plch
 * @since  2016-11-24
 * @since  2016-11-25
 */
public abstract class SampleRentals {
    public static Rental newRental (RentalUser customer, RentalUser employee, Machine machine) {
        Rental rental = new Rental ();
        rental.setCustomer    (customer);
        rental.setDateCreated (Date.valueOf ("2000-01-01"));
        rental.setDateEnd     (Date.valueOf ("2000-04-18"));
        rental.setDateStart   (Date.valueOf ("2000-04-14"));
        rental.setEmployee    (employee);
        rental.setMachine     (machine);
        rental.setNote        ("This is a sample note.");
        return rental;
    }
    
    public static RentalCreateDto newRentalCreateDto (Long customerId, Long employeeId, Long machineId) {
        Rental rental = newRentalOfBixMaxByCharlie ();
        RentalCreateDto rentalCreateDto = new RentalCreateDto ();
        rentalCreateDto.setCustomerId  (rental.getId ());
        rentalCreateDto.setDateCreated (rental.getDateCreated ());
        rentalCreateDto.setDateEnd     (rental.getDateEnd ());
        rentalCreateDto.setDateStart   (rental.getDateStart ());
        rentalCreateDto.setEmployeeId  (employeeId);
        rentalCreateDto.setId          (machineId);
        rentalCreateDto.setMachineId   (machineId);
        rentalCreateDto.setNote        (rental.getNote ());
        return rentalCreateDto;
    }
    
    public static RentalDto newRentalDto (RentalUserDto customerDto, RentalUserDto employeeDto, MachineDto machineDto) {
        Rental rental = newRentalOfBixMaxByCharlie ();
        RentalDto rentalDto = new RentalDto ();
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
        return SampleRentals.newRental (
            SampleRentalUsers.newCustomerCharlie (),
            SampleRentalUsers.newEmployeeEdward (),
            SampleMachines.newMachineBigMax ()
        );
    }
    
    public static RentalDto newRentalOfBixMaxByCharlieDto () {
        return SampleRentals.newRentalDto (
            SampleRentalUsers.newCustomerCharlieDto (),
            SampleRentalUsers.newEmployeeEdwardDto (),
            SampleMachines.newMachineBigMaxDto ()
        );
    }
}
