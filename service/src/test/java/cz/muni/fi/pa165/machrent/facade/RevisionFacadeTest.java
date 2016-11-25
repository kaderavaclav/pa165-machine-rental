/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.RevisionService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
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
 *
 * @author Peter Benus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RevisionFacadeTest extends AbstractTestNGSpringContextTests{
    
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
        Machine machine = new Machine();
        machine.setName("machine");
        revisionDto.setMachine(machine);
        RentalUser mechanic = new RentalUser();
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
        machine.setName("machine");
        revision.setMachine(machine);
        mechanic.setName("Janko");
        mechanic.setEmail("janko@mail.com");
        mechanic.setUsername("johny");
        mechanic.setLegalPersonality(LegalPersonality.NATURAL);
        revision.setMechanic(mechanic);
        
        revisions = new ArrayList<>();
        revisions.add(revision);
    }
    
    @Test
    public void RevisionFacadeCreateTest(){
        when(beanMappingService.mapTo(revisionCreateDto, Revision.class)).thenReturn(revision);
        
        revisionFacade.createRevision(revisionCreateDto);
        
        verify(revisionService).createRevision(revision);
    }
    
    @Test
    public void RevisionFacadeDeleteTest(){  
        when(revisionService.findById(1L)).thenReturn(revision);
        when(beanMappingService.mapTo(revisionDto, Revision.class)).thenReturn(revision);
        
        revisionFacade.deleteRevision(revisionDto.getId());
        
        verify(revisionService).deleteRevision(revision);
    }
    
    @Test
    public void RevisionFacadeUpdateTest(){
        when(beanMappingService.mapTo(revisionDto, Revision.class)).thenReturn(revision);
        
        revisionFacade.updateRevision(revisionDto);
        
        verify(revisionService).updateRevision(revision);
    }
    
    @Test
    public void RevisionFacadeFindAllRevisionsTest(){
        when(revisionService.findAllRevisions()).thenReturn(revisions);
        
        revisionFacade.findAllRevisions();
        
        verify(revisionService).findAllRevisions();
    }
    
    @Test
    public void RevisionFacadeFindByIdTest(){
        when(revisionService.findById(1L)).thenReturn(revision);
        when(beanMappingService.mapTo(revision, RevisionDto.class)).thenReturn(revisionDto);
        
        RevisionDto rev = revisionFacade.findById(revisionDto.getId());
        Assert.assertEquals(rev, revisionDto);
    }
}
