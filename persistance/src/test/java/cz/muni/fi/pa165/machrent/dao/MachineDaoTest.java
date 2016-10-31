package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.entities.Machine;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by zschwarz on 10/29/16.
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MachineDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MachineDao machineDao;

    private Machine machine;
    private Machine traktor;


    @PersistenceContext
    private EntityManager em;

    @BeforeMethod
    public void createMachines() {
        machine = new Machine();
        machine.setName("MACH1");
        machine.setDescription("machine1");

        traktor = new Machine();
        traktor.setName("TRAK1");
        traktor.setDescription("traktor1");

        machineDao.create(machine);
        machineDao.create(traktor);
    }

    @Test
    public void findAllMachines() {
        List<Machine> machines = machineDao.findAll();
        Assert.assertEquals(machines.size(), 2);
    }
    @Test()
    public void findMachine() {
        Machine found = machineDao.findById(machine.getId());

        Assert.assertEquals(found.getName(), "MACH1");
        Assert.assertEquals(found.getDescription(), "machine1");
    }

    @Test
    public void nonExistentReturnsNull() {
        Assert.assertNull(machineDao.findById(873623l));
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void nullMachineNameNotAllowed(){
        Machine mach = new Machine();
        mach.setName(null);
        machineDao.create(mach);
    }


    @Test()
    public void deleteMachine() {
        Assert.assertNotNull(machineDao.findById(traktor.getId()));
        machineDao.delete(traktor);
        Assert.assertNull(machineDao.findById(traktor.getId()));
    }


    @Test()
    public void saveName() {
        Machine m = new Machine();
        m.setName("masina");
        machineDao.create(m);
        Assert.assertEquals(machineDao.findById(m.getId()).getName(),"masina");
    }

    @Test()
    public void saveDescription() {
        Machine n = new Machine();
        n.setName("blah");
        n.setDescription("narez");
        machineDao.create(n);
        Assert.assertEquals(machineDao.findById(n.getId()).getDescription(),"narez");
    }
}
