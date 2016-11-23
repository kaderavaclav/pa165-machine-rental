package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.BeanMappingServiceImpl;
import cz.muni.fi.pa165.machrent.MachineService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
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
public class MachineFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private MachineService machineService;

    @Spy
    @Inject
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
    public void deleteMachine(){
        machineFacade.deleteMachine(2L);
        verify(machineService).deleteMachine(machine2);
    }

    @Test
    public void findById(){
        MachineDto machDto = machineFacade.findById(1L);
        assertEquals(machDto.getId(),machine1.getId());
        assertEquals(machDto.getName(),machine1.getName());
        assertEquals(machDto.getDescription(),machine1.getDescription());

    }

    @Test
    public void findAll() {
        List<Machine> machines = Arrays.asList(machine1, machine2);
        when(machineService.findAllMachines()).thenReturn(machines);
        List<MachineDto> machineDtos = machineFacade.findAllMachines();

        assertEquals(machineDtos.size(), machines.size());

        assertEquals(machine1.getId(),machineDtos.get(0).getId());
        assertEquals(machine1.getName(),machineDtos.get(0).getName());
        assertEquals(machine1.getDescription(),machineDtos.get(0).getDescription());
        assertEquals(machine2.getId(),machineDtos.get(1).getId());
        assertEquals(machine2.getName(),machineDtos.get(1).getName());
        assertEquals(machine2.getDescription(),machineDtos.get(1).getDescription());
    }

}
