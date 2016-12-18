package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.RevisionService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.dto.RevisionCreateDto;
import cz.muni.fi.pa165.machrent.dto.RevisionDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.entities.Revision;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.exceptions.RevisionServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author  Peter Benus
 * @since   2016-11-23
 * @version 2016-12-13
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RevisionFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private RevisionService revisionService;
    
    @Mock
    private BeanMappingService beanMappingService;
    
    @InjectMocks
    private RevisionFacade revisionFacade = new RevisionFacadeImpl();
    
    private RevisionDto revisionDto;
    private RevisionCreateDto revisionCreateDto;
    private Revision revision;
    private Date timestamp;
    private List<Revision> revisions;
    
    @BeforeClass
    public void initTestClass() throws RevisionServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void initTest() {
        revisionDto = new RevisionDto();
        revisionDto.setId(1L);
        revisionDto.setNote("Test note");
        timestamp = new Date();
        revisionDto.setRevisionDate(timestamp);
        MachineDto machine = new MachineDto();
        machine.setName("machine");
        revisionDto.setMachine(machine);
        RentalUserDto mechanic = new RentalUserDto();
        mechanic.setName("Janko");
        mechanic.setEmail("janko@mail.com");
        mechanic.setUsername("johny");
        mechanic.setLegalPersonality(LegalPersonality.NATURAL);
        revisionDto.setMechanic(mechanic);
        
        revisionCreateDto = new RevisionCreateDto();
        revisionCreateDto.setId(1L);
        revisionCreateDto.setNote("Test note");
        timestamp = new Date();
        revisionCreateDto.setRevisionDate(timestamp);
        machine.setName("machine");
        revisionCreateDto.setMachine(machine);
        mechanic.setName("Janko");
        mechanic.setEmail("janko@mail.com");
        mechanic.setUsername("johny");
        mechanic.setLegalPersonality(LegalPersonality.NATURAL);
        revisionCreateDto.setMechanic(mechanic);
        
        revision = new Revision();
        revision.setId(1L);
        revision.setNote("Test note");
        timestamp = new Date();
        revision.setRevisionDate(timestamp);
        Machine mach= new Machine();
        machine.setName("machine");
        machine.setName("machine");
        revision.setMachine(mach);
        RentalUserDto mech= new RentalUserDto();
        mech.setName("Janko");
        mech.setEmail("janko@mail.com");
        mech.setUsername("johny");
        mech.setLegalPersonality(LegalPersonality.NATURAL);
        revisionDto.setMechanic(mech);
        
        revisions = new ArrayList<>();
        revisions.add(revision);
    }
    
    @Test
    public void createRevision_validDto_serviceMethodIsCalled () {
        when(beanMappingService.mapTo(revisionCreateDto, Revision.class)).thenReturn(revision);
        
        revisionFacade.createRevision(revisionCreateDto);
        
        verify(revisionService).createRevision(revision);
    }
    
    @Test
    public void deleteRevision_idOfExistingRevision_serviceMethodIsCalled () {  
        when(revisionService.findById(1L)).thenReturn(revision);
        when(beanMappingService.mapTo(revisionDto, Revision.class)).thenReturn(revision);
        
        revisionFacade.deleteRevision(revisionDto.getId());
        
        verify(revisionService).deleteRevision(revision);
    }
    
    @Test
    public void updateRevision_validDto_serviceMethodIsCalled () {
        when(beanMappingService.mapTo(revisionDto, Revision.class)).thenReturn(revision);
        
        revisionFacade.updateRevision(revisionDto);
        
        verify(revisionService).updateRevision(revision);
    }
    
    @Test
    public void findAllRevisions_always_serviceMethodIsCalled () {
        when(revisionService.findAllRevisions()).thenReturn(revisions);
        
        revisionFacade.findAllRevisions();
        
        verify(revisionService).findAllRevisions();
    }
    
    @Test
    public void findById_idOfExistingRevision_returnThatRevisionDto () {
        when(revisionService.findById(1L)).thenReturn(revision);
        when(beanMappingService.mapTo(revision, RevisionDto.class)).thenReturn(revisionDto);
        
        RevisionDto rev = revisionFacade.findById(revisionDto.getId());
        Assert.assertEquals(rev, revisionDto);
    }

    @Test
    public void findAllMachineRevisions_idOfExistingMachine_returnCorrectRevisions(){
        List<Revision> expected = new ArrayList<Revision>();
        expected.add(revision);
        List<RevisionDto> expectedDto = new ArrayList<RevisionDto>();
        expectedDto.add(revisionDto);

        when(revisionService.findAllMachineRevisions(1L)).thenReturn(expected);
        when(beanMappingService.mapTo(expected, RevisionDto.class)).thenReturn(expectedDto);

        List<RevisionDto> actual = revisionFacade.findAllMachineRevisions(1L);
        Assert.assertEquals(actual, expectedDto);
    }
}
