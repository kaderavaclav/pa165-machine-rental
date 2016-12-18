package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
 * @author  Josef Plch
 * @since   2016-10-31
 * @version 2016-12-13
 */
@ContextConfiguration (classes = PersistenceApplicationContext.class)
@TestExecutionListeners (TransactionalTestExecutionListener.class)
@Transactional
public class RentalDaoTest extends AbstractTestNGSpringContextTests {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat ("yyyy-MM-dd");
    
    @Autowired
    private MachineDao machineDao;
    
    private Rental rentalA;
    private RentalUser customerA;
    
    @Autowired
    private RentalDao rentalDao;
    
    @Autowired
    private RentalUserDao userDao;

    @BeforeMethod
    public void beforeMethod () {
        customerA = new RentalUser ();
        customerA.setEmail ("zednicek@email.cz");
        customerA.setLegalPersonality (LegalPersonality.NATURAL);
        customerA.setName ("Pavel Zedn��ek");
        customerA.setRoles (Collections.singleton (RentalUserRole.CUSTOMER));
        customerA.setUsername ("zednicek");
        userDao.create (customerA);
        
        RentalUser employeeA;
        employeeA = new RentalUser ();
        employeeA.setEmail ("kant@machinerentals.com");
        employeeA.setLegalPersonality (LegalPersonality.NATURAL);
        employeeA.setName ("Immanuel Kant");
        employeeA.setRoles (Collections.singleton (RentalUserRole.EMPLOYEE));
        employeeA.setUsername ("kant");
        userDao.create (employeeA);
        
        Machine machineA;
        machineA = new Machine ();
        machineA.setDescription ("mal� �lut� bag��k");
        machineA.setName ("BAGR 01");
        machineDao.create (machineA);
        
        rentalA = new Rental ();
        rentalA.setCustomer (customerA);
        rentalA.setDateCreated (parseDate ("2016-08-01"));
        rentalA.setDateEnd     (parseDate ("2016-10-28"));
        rentalA.setDateStart   (parseDate ("2016-10-25"));
        rentalA.setMachine (machineA);
        rentalA.setNote ("The first rent of Pavel Zedn��ek");
        rentalDao.create (rentalA);
    }

    @Test ()
    public void findById_idOfExistingMachine_foundMachineHasCorrectAttributes () {
        Rental found = rentalDao.findById (rentalA.getId ());
        Assert.assertEquals (found.getCustomer (),    rentalA.getCustomer ());
        Assert.assertEquals (found.getDateCreated (), rentalA.getDateCreated ());
        Assert.assertEquals (found.getDateEnd (),     rentalA.getDateEnd ());
        Assert.assertEquals (found.getDateStart (),   rentalA.getDateStart ());
        Assert.assertEquals (found.getEmployee (),    rentalA.getEmployee ());
        Assert.assertEquals (found.getMachine (),     rentalA.getMachine ());
        Assert.assertEquals (found.getNote (),        rentalA.getNote ());
    }
    
    @Test ()
    public void delete_existingRental_findReturnsNull () {
        Assert.assertNotNull (rentalDao.findById (rentalA.getId ()));
        rentalDao.delete (rentalA);
        Assert.assertNull (rentalDao.findById (rentalA.getId ()));
    }

    @Test
    public void findAll_twoMachinesCreated_returnTwoMachines () {
        Machine machineB = new Machine ();
        machineB.setDescription ("velk� �lut� bagr");
        machineB.setName ("BAGR 02");
        machineDao.create (machineB);
        
        RentalUser customerB = new RentalUser ();
        customerB.setEmail ("info@slovakostav.eu");
        customerB.setLegalPersonality (LegalPersonality.JURIDICAL);
        customerB.setName ("Slovakostav, s.r.o.");
        customerB.setRoles (Collections.singleton (RentalUserRole.CUSTOMER));
        customerB.setUsername ("slovakostav");
        userDao.create (customerB);
        
        RentalUser employeeB;
        employeeB = new RentalUser ();
        employeeB.setEmail ("descartes@machinerentals.com");
        employeeB.setLegalPersonality (LegalPersonality.NATURAL);
        employeeB.setName ("Ren� Descartes");
        employeeB.setRoles (Collections.singleton (RentalUserRole.EMPLOYEE));
        employeeB.setUsername ("rene");
        userDao.create (employeeB);
        
        Rental rentalB = new Rental ();
        rentalB.setCustomer (customerB);
        rentalB.setDateCreated (parseDate ("2010-12-24"));
        rentalB.setDateEnd     (parseDate ("2011-01-14"));
        rentalB.setDateStart   (parseDate ("2011-01-01"));
        rentalB.setEmployee (employeeB);
        rentalB.setMachine (machineB);
        rentalB.setNote ("The first rent of Slovakostav");
        rentalDao.create (rentalB);

        List <Rental> revisions = rentalDao.findAll ();
        Assert.assertEquals (revisions.size (), 2);
    }

    @Test
    public void findById_idOfExistingRental_returnThatRental () {
        Rental found = rentalDao.findById (rentalA.getId ());
        Assert.assertEquals (found, rentalA);
    }

    @Test
    public void findById_nonExistentId_returnNull () {
        Assert.assertNull (rentalDao.findById (-1L));
    }

    @Test
    public void findAllByCustomerId_idOfExistingCustomer_returnCorrectRentalList(){
        List<Rental> expected = new ArrayList<>();
        expected.add(rentalA);

        List<Rental> actual = rentalDao.findAllByCustomerId(customerA.getId());
        Assert.assertEquals(actual, expected);
    }
    
    /**
     * Parse date in format "2005-04-20". If the string cannot be parsed, null
     * is returned.
     * 
     * @param string string to be parsed
     * @return parsed date
     */
    private static Date parseDate (String string) {
        Date date;
        try {
            date = DATE_FORMAT.parse (string);
        }
        catch (ParseException exception) {
            date = null;
        }
        return date;
    }
}
