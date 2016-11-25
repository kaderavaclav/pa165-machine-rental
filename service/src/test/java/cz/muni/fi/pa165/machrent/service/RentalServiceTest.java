package cz.muni.fi.pa165.machrent.service;

import cz.muni.fi.pa165.machrent.RentalService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dao.RentalDao;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.exceptions.RentalServiceException;
import cz.muni.fi.pa165.machrent.exceptions.RentalUserServiceException;
import cz.muni.fi.pa165.machrent.sampleInstances.SampleMachines;
import cz.muni.fi.pa165.machrent.sampleInstances.SampleRentals;
import cz.muni.fi.pa165.machrent.sampleInstances.SampleRentalUsers;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  Josef Plch
 * @since   2016-11-23
 * @version 0016-11-25
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
    private Date dateFrom;
    private Date dateTo;
    private List<Rental> rentals;

    @BeforeClass
    public void initTestClass () throws RentalUserServiceException {
        MockitoAnnotations.initMocks (this);
    }

    @BeforeMethod
    public void initTestMethods() throws RentalServiceException {
        validCustomer = SampleRentalUsers.newCustomerCharlie ();
        validEmployee = SampleRentalUsers.newEmployeeEdward ();
        validMachine = SampleMachines.newMachineBigMax ();
        validRental = SampleRentals.newRentalOfBixMaxByCharlie ();

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");

        try {
            dateFrom = formater.parse("2000-01-01");
            dateTo = formater.parse("2000-04-18");
        }
        catch (ParseException ex){
            throw new RentalServiceException(ex);
        }

        rentals = new ArrayList<>();
        rentals.add(validRental);

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
        when(rentalDao.findById(validRental.getId())).thenReturn(validRental);
        Rental rental = rentalService.findById (validRental.getId ());
        assertEquals (rental, validRental);
    }

    @Test
    public void findAllCreatedBetween(){
        when(rentalDao.findAllCreatedBetween(dateFrom, dateTo)).thenReturn(rentals);
        List<Rental> created = rentalService.findAllCreatedBetween(dateFrom, dateTo);
        assertEquals(rentals, created);
    }
}
