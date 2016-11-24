package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.BeanMappingServiceImpl;
import cz.muni.fi.pa165.machrent.MachineService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.MachineUpdateDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.crypto.Mac;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;


/**
 * Created by zuz-schwarzova on 23. 11. 2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MachineFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MachineService machineService;

    @Mock
    private BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    @InjectMocks
    private MachineFacade machineFacade = new MachineFacadeImpl();

    @Captor
    private ArgumentCaptor<Machine> machineArgumentCaptor;

    private MachineCreateDto machineCreateDto;
    private Machine machine1;
    private Machine machine2;
    private String name1;
    private String name2;
    private String description1;
    private String description2;

    @BeforeClass
    public void initMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initMachines()
    {
        machine1 = new Machine();
        machine2 = new Machine();
        name1="CAT";
        name2="MAT";
        description1 = "big";
        description2 = "nice";
        machine1.setId(1L);
        machine1.setName(name1);
        machine1.setDescription(description1);
        machine2.setId(2L);
        machine2.setName(name2);
        machine2.setDescription(description2);

    }

    @BeforeMethod(dependsOnMethods = "initMachines")
    public void initMocksBehaviour(){
        //find by id
        when(machineService.findById(1L)).thenReturn(machine1);
        when(machineService.findById(2L)).thenReturn(machine2);
    }

    @Test
    public void createMachine(){
        machineCreateDto = new MachineCreateDto();
        machineCreateDto.setId(machine1.getId());
        machineCreateDto.setName(machine1.getName());
        machineCreateDto.setDescription(machine1.getDescription());

        machineFacade.createMachine(machineCreateDto);

        verify(machineService).createMachine(machineArgumentCaptor.capture());

        Machine m = machineArgumentCaptor.getValue();

        assertEquals(m.getName(), name1);
        assertEquals(m.getDescription(), description1);
    }


    @Test
    public void testUpdate() {
        MachineUpdateDto machineDto = new MachineUpdateDto();
        machineDto.setId(machine1.getId());
        machineDto.setName(machine1.getName());
        machineDto.setDescription(machine1.getDescription());

        when(beanMappingService.mapTo(machineDto, Machine.class)).thenReturn(machine1);

        machineFacade.updateMachine(machineDto);

        verify(machineService).updateMachine(machine1);

    }

    @Test
    public void deleteMachine(){
        MachineDto machineDto = new MachineDto();
        machineDto.setId(machine1.getId());
        machineDto.setName(machine1.getName());
        machineDto.setDescription(machine1.getDescription());

        when(beanMappingService.mapTo(machineDto, Machine.class)).thenReturn(machine1);

        machineFacade.deleteMachine(machineDto.getId());

        verify(machineService).deleteMachine(machine1);
    }

    @Test
    public void findById(){
        MachineDto machineDto = new MachineDto();
        machineDto.setId(machine1.getId());
        machineDto.setName(machine1.getName());
        machineDto.setDescription(machine1.getDescription());

        when(beanMappingService.mapTo(machine1, MachineDto.class)).thenReturn(machineDto);

        MachineDto machDto = machineFacade.findById(machineDto.getId());
        assertEquals(machDto.getId(),machineDto.getId());
        assertEquals(machDto.getName(),machineDto.getName());
        assertEquals(machDto.getDescription(),machineDto.getDescription());

    }

    @Test
    public void findAll() {
        List<Machine> machines = Arrays.asList(machine1, machine2);
        when(machineService.findAllMachines()).thenReturn(machines);
        machineFacade.findAllMachines();

        verify(machineService).findAllMachines();
    }

}
