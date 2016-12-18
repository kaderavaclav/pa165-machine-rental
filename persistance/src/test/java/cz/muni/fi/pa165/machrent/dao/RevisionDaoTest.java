package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.entities.Revision;
import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author  Peter Benus
 * @since   2016-10-29
 * @version 2016-12-13
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RevisionDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RevisionDao revisionDao;
    @Autowired
    private MachineDao machineDao;
    @Autowired
    private RentalUserDao userDao;

    private Revision revision;
    
    private Date timestamp;

    private Machine machine;

    @BeforeMethod
    public void createRevision() {
        revision = new Revision();
        machine = new Machine();
        machine.setName("machine");
        machineDao.create(machine);
        revision.setMachine(machine);
        
        RentalUser mechanic;
        mechanic = new RentalUser();
        mechanic.setName("Janko");
        mechanic.setEmail("janko@mail.com");
        mechanic.setUsername("johny");
        mechanic.setLegalPersonality(LegalPersonality.NATURAL);
        userDao.create(mechanic);
        revision.setMechanic(mechanic);
        
        revision.setNote("revision1");
        timestamp = new Date();
        revision.setRevisionDate(timestamp);
        revisionDao.create(revision);
    }

    @Test
    public void findById_nonExistentId_returnNull () {
        Assert.assertNull(revisionDao.findById(12345l));
    }

    @Test
    public void findById_idOfExistingRevision_returnThatRevision () {
        Revision found = revisionDao.findById(revision.getId());
        Assert.assertEquals(found, revision);
    }

    @Test
    public void findAll_twoRevisionsCreated_returnTwoRevisions () {
        Revision revision2 = new Revision();
        Machine machine2 = new Machine();
        machine2.setName("machine2");
        machineDao.create(machine2);
        revision2.setMachine(machine2);
        
        RentalUser mechanic2 = new RentalUser();
        mechanic2.setName("Ferko");
        mechanic2.setEmail("ferko@mail.com");
        mechanic2.setUsername("fery");
        mechanic2.setLegalPersonality(LegalPersonality.JURIDICAL);
        userDao.create(mechanic2);
        revision2.setMechanic(mechanic2);
        
        revision2.setNote("revision2");
        Date timestamp2 = new Date();
        revision2.setRevisionDate(timestamp2);
        revisionDao.create(revision2);

        List<Revision> revisions = revisionDao.findAll();
        Assert.assertEquals(revisions.size(), 2);
    }

    @Test()
    public void delete_existingRevision_findReturnsNull () {
        Assert.assertNotNull(revisionDao.findById(revision.getId()));
        revisionDao.delete(revision);
        Assert.assertNull(revisionDao.findById(revision.getId()));
    }

    @Test()
    public void findById_idOfExistingMachine_hasCorrectNote () {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getNote(), "revision1");
    }

    @Test()
    public void findById_idOfExistingMachine_hasCorrectMachine () {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getMachine(), revision.getMachine());
    }

    @Test()
    public void findById_idOfExistingMachine_hasCorrectMechanic () {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getMechanic(), revision.getMechanic());
    }

    @Test()
    public void findById_idOfExistingMachine_hasCorrectRevisionDate () {
        Assert.assertEquals(revisionDao.findById(revision.getId()).getRevisionDate(), revision.getRevisionDate());
    }

    @Test()
    public void findByMachineId_idOfExistingMachine_hasCorrectRevisions(){
        List<Revision> expected = new ArrayList<Revision>();
        expected.add(revision);
        Assert.assertEquals(revisionDao.findAllByMachineId(machine.getId()), expected);
    }
    
    @Test()
    public void update_changedNote_findReturnsUpdatedRevision () {
        Revision rev = revisionDao.findById(revision.getId());
        rev.setNote("updated note");
        revisionDao.update(rev);
        
        rev = revisionDao.findById(rev.getId());
        Assert.assertEquals(rev.getNote(), "updated note");
    }
}
