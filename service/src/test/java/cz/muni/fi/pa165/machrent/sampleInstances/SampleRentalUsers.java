package cz.muni.fi.pa165.machrent.sampleInstances;

import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import java.util.EnumSet;

/**
 * Collection of sample entities used in various tests.
 * 
 * @author Josef Plch
 * @since  2016-11-24
 * @since  2016-11-25
 */
public abstract class SampleRentalUsers {
    public static RentalUser newCustomerCharlie () {
        RentalUser customer = new RentalUser ();
        customer.setEmail            ("charlie@rental.com");
        customer.setId               (101L);
        customer.setLegalPersonality (LegalPersonality.NATURAL);
        customer.setName             ("Charlie Customer");
        customer.setPassword         ("charliecharlie");
        customer.setRoles            (EnumSet.of (RentalUserRole.CUSTOMER));
        customer.setUsername         ("charlie");
        return customer;
    }
    
    public static RentalUserDto newCustomerCharlieDto () {
        RentalUser customer = newCustomerCharlie ();
        RentalUserDto customerDto = new RentalUserDto ();
        customerDto.setEmail            (customer.getEmail ());
        customerDto.setId               (customer.getId ());
        customerDto.setLegalPersonality (customer.getLegalPersonality ());
        customerDto.setName             (customer.getName ());
        customerDto.setPasswordHash     (customer.getPasswordHash ());
        customerDto.setRoles            (customer.getRoles ());
        customerDto.setUsername         (customer.getUsername ());
        return customerDto;
    }
    
    public static RentalUser newEmployeeEdward () {
        RentalUser employee = new RentalUser ();
        employee.setEmail            ("edward@rental.com");
        employee.setId               (102L);
        employee.setLegalPersonality (LegalPersonality.NATURAL);
        employee.setName             ("Edward Employee");
        employee.setPassword         ("edwardedward");
        employee.setRoles            (EnumSet.of (RentalUserRole.EMPLOYEE));
        employee.setUsername         ("edward");
        return employee;
    }
    
    public static RentalUserDto newEmployeeEdwardDto () {
        RentalUser employee = newEmployeeEdward ();
        RentalUserDto employeeDto = new RentalUserDto ();
        employeeDto.setEmail            (employee.getEmail ());
        employeeDto.setId               (employee.getId ());
        employeeDto.setLegalPersonality (employee.getLegalPersonality ());
        employeeDto.setName             (employee.getName ());
        employeeDto.setPasswordHash     (employee.getPasswordHash ());
        employeeDto.setRoles            (employee.getRoles ());
        employeeDto.setUsername         (employee.getUsername ());
        return employeeDto;
    }
}
