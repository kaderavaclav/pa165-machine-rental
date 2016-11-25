package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.RentalService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.exceptions.RentalServiceException;
import cz.muni.fi.pa165.machrent.sampleInstances.SampleMachines;
import cz.muni.fi.pa165.machrent.sampleInstances.SampleRentalUsers;
import cz.muni.fi.pa165.machrent.sampleInstances.SampleRentals;
import java.util.List;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author  Josef Plch
 * @since   2016-11-24
 * @version 2016-11-25
 */
@ContextConfiguration (classes = ServiceConfiguration.class)
@TestExecutionListeners (TransactionalTestExecutionListener.class)
@Transactional
public class RentalFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalFacade rentalFacade = new RentalFacadeImpl ();

    private RentalCreateDto rentalCreateDto;
    private RentalDto rentalDto;
    private RentalUser customer;
    private RentalUser employee;
    private Machine machine;
    private Rental rental;
    private List <Rental> allRentals;

    @BeforeClass
    public void initTestClass () throws RentalServiceException {
        MockitoAnnotations.initMocks (this);
    }

    @BeforeMethod
    public void initTestMethods () {
        customer = SampleRentalUsers.newCustomerCharlie ();
        employee = SampleRentalUsers.newEmployeeEdward ();
        machine = SampleMachines.newMachineBigMax ();
        rental = SampleRentals.newRentalOfBixMaxByCharlie ();
        
        rentalCreateDto = SampleRentals.newRentalCreateDto (customer.getId (), employee.getId (), machine.getId ());
        rentalDto = SampleRentals.newRentalOfBixMaxByCharlieDto ();
    }

    @Test
    public void createRental () {
        rentalFacade.createRental (rentalCreateDto);
        verify(rentalService).createRental (rental);
    }
    
    @Test
    public void deleteRental () {
        rentalFacade.deleteRental (rentalDto.getId ());
        verify(rentalService).deleteRental (rental);
    }
    
    @Test
    public void getAllRentals () {
        rentalFacade.getAllRentals ();
        verify(rentalService).findAll ();
    }
    
    @Test
    public void getRentalWithId () {
        rentalFacade.getRentalWithId (rental.getId ());
        verify(rentalService).findById (rental.getId ());
    }
}
