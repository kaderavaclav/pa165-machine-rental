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
 * @author  Peter Benus
 * @since   2016-11-23
 * @version 2016-12-13
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
    public void createRevision_validRevision_daoMethodIsCalled () {
        revisionService.createRevision(testRevision);
        verify(revisionDao).create(testRevision);
    }
        
    @Test
    public void updateRevision_validRevision_daoMethodIsCalled () {
        revisionService.updateRevision(testRevision);
        verify(revisionDao).update(testRevision);
    }
    
    @Test
    public void deleteRevision_validRevision_daoMethodIsCalled () {
        revisionService.deleteRevision(testRevision);
        verify(revisionDao).delete(testRevision);
    }
    
    @Test
    public void findAllRevisions_always_daoMethodIsCalled () {
        List<Revision> revisions = revisionService.findAllRevisions();
        verify(revisionDao).findAll();
    }
    
    @Test
    public void findById_idOfExistingRevision_daoMethodIsCalled () {
        Revision r = revisionService.findById(testRevision.getId());
        verify(revisionDao).findById(testRevision.getId());
    }
}
