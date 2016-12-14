package cz.muni.fi.pa165.machrent.service;

import cz.muni.fi.pa165.machrent.MachineService;
import cz.muni.fi.pa165.machrent.MachineServiceImpl;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dao.MachineDao;
import cz.muni.fi.pa165.machrent.dao.RentalDao;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.exceptions.MachineServiceException;
import cz.muni.fi.pa165.machrent.exceptions.MachrentServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author  zuz-schwarzova
 * @since   2016-11-23
 * @version 2016-12-13
 */
@ContextConfiguration (classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MachineServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MachineDao machineDao;

    @Mock
    private RentalDao rentalDao;

    @Autowired
    @InjectMocks
    private MachineService machineService = new MachineServiceImpl();

    @Captor
    private ArgumentCaptor<Machine> machineArgumentCaptor;

    private Machine machine1;
    private Machine machine2;
    private String name1;
    private String name2;
    private String description1;
    private String description2;
    private Date dateFrom;
    private Date dateTo;
    private List<Rental> rentals;
    private List<Machine> machines;

    @BeforeClass
    public void initMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initMachines() throws MachrentServiceException{
        machine1 = new Machine();
        machine2 = new Machine();
        name1="mach1";
        name2="mach2";
        description1 = "lala";
        description2 = "salala";
        machine1.setId(1L);
        machine1.setName(name1);
        machine1.setDescription(description1);
        machine2.setId(2L);
        machine2.setName(name2);
        machine2.setDescription(description2);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");

        try {
            dateFrom = formater.parse("2000-01-01");
            dateTo = formater.parse("2000-04-18");
        }
        catch (ParseException ex){
            throw new MachineServiceException(ex);
        }

        rentals = new ArrayList<>();
        machines = new ArrayList<>();
        machines.add(machine1);
    }

    @BeforeMethod(dependsOnMethods = "initMachines")
    public void initMocksBehaviour(){
        //find by id
        when(machineService.findById(1L)).thenReturn(machine1);
        when(machineService.findById(2L)).thenReturn(machine2);
    }

    @Test
    public void createMachine_validMachine_daoMethodIsCalled () {
        machineService.createMachine(machine1);
        verify(machineDao).create(machine1);
    }

    @Test
    public void updateMachine_validMachine_daoMethodIsCalled () {
        machineService.updateMachine(machine1);
        verify(machineDao).update(machine1);
    }

    @Test
    public void deleteMachine_validMachine_daoMethodIsCalled () {
        machineService.deleteMachine(machine1);
        verify(machineDao).delete(machine1);
    }

    @Test
    public void findById_idOfExistingMachine_returnThatMachine () {
        assertSame(machineService.findById(machine1.getId()), machine1);
        verify(machineDao).findById(machine1.getId());
    }


    @Test
    public void findAllMachines_always_daoMethodIsCalled () {
        machineService.findAllMachines();
        verify(machineDao).findAll();
    }

    // Josef: What does it test?
    @Test
    public void findAvailableMachines () {
        when(rentalDao.findAllEffectiveBetween(dateFrom, dateTo)).thenReturn(rentals);
        when(machineDao.findAll()).thenReturn(machines);

        List<Machine> available = machineService.findAvailableMachines(dateFrom, dateTo);
        assertEquals(machines, available);
    }
}
