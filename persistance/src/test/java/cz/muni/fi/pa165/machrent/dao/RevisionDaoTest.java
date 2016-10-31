/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.entities.Revision;
import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;

import java.util.Date;
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
 *
 * @author Peter Benus
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
    private UserDao userDao;

    private Revision revision;
//    private RentalUser mechanic;
//    private Machine machine;
    private Date timestamp;

    @BeforeMethod
    public void createRevision() {
        RentalUser mechanic;
        Machine machine;
        revision = new Revision();
        machine = new Machine();
        machine.setName("machine");
        machineDao.create(machine);
        revision.setMachine(machine);
        mechanic = new RentalUser();
        mechanic.setName("Janko");
        mechanic.setEmail("janko@mail.com");
        mechanic.setUsername("johny");
        mechanic.setLegalPersonality(RentalUser.LegalPersonality.NATURAL);
        userDao.create(mechanic);
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

    @Test
    public void find() {
        Revision found = revisionDao.findById(revision.getId());
        Assert.assertEquals(found, revision);
    }

    @Test
    public void findAll() {
        Revision revision2 = new Revision();
        Machine machine2 = new Machine();
        machine2.setName("machine2");
        machineDao.create(machine2);
        revision2.setMachine(machine2);
        RentalUser mechanic2 = new RentalUser();
        mechanic2.setName("Ferko");
        mechanic2.setEmail("ferko@mail.com");
        mechanic2.setUsername("fery");
        mechanic2.setLegalPersonality(RentalUser.LegalPersonality.JURIDICAL);
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
