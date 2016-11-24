package cz.muni.fi.pa165.machrent.service;

import cz.muni.fi.pa165.machrent.RentalService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dao.RentalDao;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import cz.muni.fi.pa165.machrent.exceptions.RentalUserServiceException;
import java.sql.Date;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.EnumSet;

/**
 * @author  Josef Plch
 * @since   2016-11-23
 * @version 0016-11-24
 */
@ContextConfiguration (classes = ServiceConfiguration.class)
@TestExecutionListeners (TransactionalTestExecutionListener.class)
@Transactional
public class RentalServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private RentalDao rentalDao;

    @Autowired
    @InjectMocks
    private RentalService rentalService;

    private RentalUser validCustomer;
    private RentalUser validEmployee;
    private Machine validMachine;
    private Rental validRental;

    @BeforeClass
    public void initTestClass () throws RentalUserServiceException {
        MockitoAnnotations.initMocks (this);
    }

    @BeforeMethod
    public void initTestMethods () {
        validCustomer = new RentalUser ();
        validCustomer.setEmail      ("charlie@rental.com");
        validCustomer.setId         (101L);
        validCustomer.setLegalPersonality (LegalPersonality.NATURAL);
        validCustomer.setName       ("Charlie Customer");
        validCustomer.setPassword   ("charliecharlie");
        validCustomer.setRoles      (EnumSet.of (RentalUserRole.CUSTOMER));
        validCustomer.setUsername   ("charlie");
        
        validEmployee = new RentalUser ();
        validEmployee.setEmail      ("edward@rental.com");
        validEmployee.setId         (102L);
        validEmployee.setLegalPersonality (LegalPersonality.NATURAL);
        validEmployee.setName       ("Edward Employee");
        validEmployee.setPassword   ("edwardedward");
        validEmployee.setRoles      (EnumSet.of (RentalUserRole.EMPLOYEE));
        validEmployee.setUsername   ("edward");
        
        validMachine = new Machine ();
        validMachine.setDescription ("a huge yellow crane");
        validMachine.setId          (201L);
        validMachine.setName        ("Big Max");
        
        validRental = new Rental ();
        validRental.setCustomer    (validCustomer);
        validRental.setDateCreated (Date.valueOf ("2000-01-01"));
        validRental.setDateEnd     (Date.valueOf ("2000-04-18"));
        validRental.setDateStart   (Date.valueOf ("2000-04-14"));
        validRental.setEmployee    (validEmployee);
        validRental.setMachine     (validMachine);
        validRental.setNote        ("This is the first rental of Max.");
    }
    
    @Test
    public void createRental () {
        rentalService.createRental(validRental);
        verify(rentalDao).create(validRental);
    }
    
    @Test
    public void deleteRental () {
        rentalService.deleteRental(validRental);
        verify(rentalDao).delete(validRental);
    }
    
    @Test
    public void findAll () {
        rentalService.findAll();
        verify(rentalDao).findAll();
    }
    
    @Test
    public void findById () {
        RentalUser customer = rentalService.findById (validCustomer.getId ());
        assertEquals (customer, validCustomer);
    }

    @Test
    public void getCustomer () {
        RentalUser customer = rentalService.getCustomer (validRental);
        assertEquals (customer, validRental.getCustomer ());
    }

    @Test
    public void getDateCreated () {
        Date dateCreated = rentalService.getDateCreated (validRental);
        assertEquals (dateCreated, validRental.getDateCreated ());
    }
    
    @Test
    public void getDateEnd () {
        Date dateEnd = rentalService.getDateEnd (validRental);
        assertEquals (dateEnd, validRental.getDateEnd ());
    }
    
    @Test
    public void getDateStart () {
        Date dateStart = rentalService.getDateStart (validRental);
        assertEquals (dateStart, validRental.getDateStart());
    }
    
    @Test
    public void getEmployee () {
        RentalUser employee = rentalService.getEmployee (validRental);
        assertEquals (employee, validRental.getEmployee ());
    }
    
    @Test
    public void getMachine () {
        Machine machine = rentalService.getMachine (validRental);
        assertEquals (machine, validRental.getMachine ());
    }
    
    @Test
    public void getNote () {
        String note = rentalService.getNote (validRental);
        assertEquals (note, validRental.getNote ());
    }
    
    @Test
    public void setCustomer () {
        rentalService.setCustomer (validRental, validCustomer);
        verify(rentalDao).update(validRental);
    }
    
    @Test
    public void setDateEnd () {
        Date newDateEnd = Date.valueOf ("2050-01-01");
        rentalService.setDateEnd (validRental, newDateEnd);
        verify(rentalDao).update(validRental);
        assertEquals (validRental.getDateEnd(), newDateEnd);
    }
    
    @Test
    public void setDateStart () {
        Date newDateStart = Date.valueOf ("1950-01-01");
        rentalService.setDateStart (validRental, newDateStart);
        verify(rentalDao).update(validRental);
        assertEquals (validRental.getDateStart(), newDateStart);
    }
    
    @Test
    public void setEmployee () {
        rentalService.setEmployee (validRental, validEmployee);
        verify(rentalDao).update(validRental);
    }
    
    @Test
    public void setMachine () {
        rentalService.setMachine (validRental, validMachine);
        verify(rentalDao).update(validRental);
    }
    
    @Test
    public void setNote () {
        String newNote = "a new note";
        rentalService.setNote (validRental, newNote);
        verify(rentalDao).update(validRental);
        assertEquals (validRental.getNote(), newNote);
    }
}
