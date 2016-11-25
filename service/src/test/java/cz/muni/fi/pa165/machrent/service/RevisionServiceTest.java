/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.service;

import cz.muni.fi.pa165.machrent.RevisionService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dao.RevisionDao;
import cz.muni.fi.pa165.machrent.entities.Revision;
import cz.muni.fi.pa165.machrent.exceptions.MachrentServiceException;
import java.util.Date;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Peter Benus
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RevisionServiceTest extends AbstractTestNGSpringContextTests{
    
    @Mock
    private RevisionDao revisionDao;

    @Autowired
    @InjectMocks
    private RevisionService revisionService;

    private Revision testRevision;
    private Date timestamp;


    @BeforeClass
    public void initTestClass() throws MachrentServiceException {
       MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initTestMethods(){
        testRevision = new Revision();
    }

    @Test
    public void RevisionServiceCreateTest(){
        revisionService.createRevision(testRevision);
        verify(revisionDao).create(testRevision);
    }
        
    @Test
    public void RevisionServiceUpdateTest(){
        revisionService.updateRevision(testRevision);
        verify(revisionDao).update(testRevision);
    }
    
    @Test
    public void RevisionServiceDeleteTest(){
        revisionService.deleteRevision(testRevision);
        verify(revisionDao).delete(testRevision);
    }
    
    @Test
    public void RevisionServiceFindAllRevisionsTest(){
        List<Revision> revisions = revisionService.findAllRevisions();
        verify(revisionDao).findAll();
    }
    
    @Test
    public void RevisionServiceFindByIdTest(){
        Revision r = revisionService.findById(testRevision.getId());
        verify(revisionDao).findById(testRevision.getId());
    }
}
