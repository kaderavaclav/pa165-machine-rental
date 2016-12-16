package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.entities.Machine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
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
 * @author  zuz-schwarzova
 * @since   2016-10-29
 * @version 2016-12-13
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
    public void findAll_twoMachinesCreated_returnTwoMachines () {
        List<Machine> machines = machineDao.findAll();
        Assert.assertEquals(machines.size(), 2);
    }
    
    @Test()
    public void findById_idOfExistingMachine_returnThatMachine () {
        Machine found = machineDao.findById(machine.getId());

        Assert.assertEquals(found.getName(), "MACH1");
        Assert.assertEquals(found.getDescription(), "machine1");
    }
    
    @Test
    public void findById_nonExistentId_returnNull () {
        Assert.assertNull(machineDao.findById(873623l));
    }
    
    @Test(expectedExceptions=ConstraintViolationException.class)
    public void create_nullName_throwException () {
        Machine mach = new Machine();
        mach.setName(null);
        machineDao.create(mach);
    }
    
    @Test()
    public void delete_existingMachine_findReturnsNull () {
        Assert.assertNotNull(machineDao.findById(traktor.getId()));
        machineDao.delete(traktor);
        Assert.assertNull(machineDao.findById(traktor.getId()));
    }
    
    @Test()
    public void create_validMachine_nameCanBeLoadedFromDatabase () {
        Machine m = new Machine();
        m.setName("masina");
        machineDao.create(m);
        Assert.assertEquals(machineDao.findById(m.getId()).getName(),"masina");
    }
    
    @Test()
    public void create_validMachine_descriptionCanBeLoadedFromDatabase () {
        Machine n = new Machine();
        n.setName("blah");
        n.setDescription("narez");
        machineDao.create(n);
        Assert.assertEquals(machineDao.findById(n.getId()).getDescription(),"narez");
    }
    
    @Test()
    public void update_changedName_findReturnsCorrectMachine () {
        Machine updated = machineDao.findById(machine.getId());
        updated.setName("updated name");
        machineDao.update(updated);

        updated = machineDao.findById(updated.getId());
        Assert.assertEquals(updated.getName(), "updated name");
    }
    
    @Test()
    public void update_changedDescrription_findReturnsCorrectMachine () {
        Machine updated = machineDao.findById(machine.getId());
        updated.setDescription("updated description");
        machineDao.update(updated);

        updated = machineDao.findById(updated.getId());
        Assert.assertEquals(updated.getDescription(),"updated description");
    }
}
