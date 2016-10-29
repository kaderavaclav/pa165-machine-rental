/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.Machine;
import cz.muni.fi.pa165.machrent.Entities.Revision;
import cz.muni.fi.pa165.machrent.Repository.PersistenceApplicationContext;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Peter Benus
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RevisionTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RevisionDAO revisionDao;
    @Autowired
    private UserDAO userDao;
    @Autowired
    private MachineDAO machineDao;

    private Revision revision;
    private User mechanic;
    private Machine machine;
    private Date timestamp;

    @PersistenceContext
    private EntityManager em;

    @BeforeMethod
    public void createRevision() {
        revision = new Revision();
        mechanic = new User();
        machine = new Machine();
        revision.setMachine(machine);
        revision.setMechanic(mechanic);
        revision.setNote("revision1");
        timestamp = new Date();
        revision.setRevisionDate(timestamp);
        revisionDao.create(revision);
    }

    @Test
    public void nonExistentReturnsNull() {
        Assert.assertNull(revisionDao.findById(12345l));
    }

    @Test()
    public void find() {
        Revision found = revisionDao.findById(revision.getId());
        Assert.assertEquals(found, revision);
    }

    @Test
    public void findAll() {
        Revision revision2 = new Revision();
        revisionDao.create(revision2);

        List<Revision> revisions = revisionDao.findAll();

        Assert.assertEquals(revisions.size(), 2);
    }

    @Test()
    public void delete() {
        Assert.assertNotNull(revisionDao.findById(revision.getId()));
        revisionDao.delete(revision);
        Assert.assertNull(revisionDao.findById(revision.getId()));
    }

    @Test()
    public void savesNote() {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getNote(), "revision1");
    }
    
    @Test()
    public void savesMachine() {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getMachine(), revision.getMachine());
    }
    
    @Test()
    public void savesMechanic() {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getMechanic(), revision.getMechanic());
    }
    
    @Test()
    public void savesRevisionDate() {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getRevisionDate(), revision.getRevisionDate());
    }
}
