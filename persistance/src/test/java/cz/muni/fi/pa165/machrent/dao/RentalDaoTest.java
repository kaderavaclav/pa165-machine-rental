package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @version 2016-10-31
 */
@ContextConfiguration (classes = PersistenceApplicationContext.class)
@TestExecutionListeners (TransactionalTestExecutionListener.class)
@Transactional
public class RentalDaoTest extends AbstractTestNGSpringContextTests {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyyy-MM-dd");
    
    @Autowired
    private MachineDao machineDao;
    
    private Rental rentalA;
    
    @Autowired
    private RentalDao rentalDao;
    
    @Autowired
    private UserDao userDao;

    @BeforeMethod
    public void beforeMethod () {
        RentalUser customerA;
        customerA = new RentalUser ();
        customerA.setEmail ("zednicek@email.cz");
        customerA.setLegalPersonality (RentalUser.LegalPersonality.NATURAL);
        customerA.setName ("Pavel Zedníèek");
        customerA.setRoles (Collections.singleton (RentalUser.Role.CUSTOMER));
        customerA.setUsername ("zednicek");
        userDao.create (customerA);
        
        RentalUser employeeA;
        employeeA = new RentalUser ();
        employeeA.setEmail ("kant@machinerentals.com");
        employeeA.setLegalPersonality (RentalUser.LegalPersonality.NATURAL);
        employeeA.setName ("Immanuel Kant");
        employeeA.setRoles (Collections.singleton (RentalUser.Role.EMPLOYEE));
        employeeA.setUsername ("kant");
        userDao.create (employeeA);
        
        Machine machineA;
        machineA = new Machine ();
        machineA.setDescription ("malý žlutý bagøík");
        machineA.setName ("BAGR 01");
        machineDao.create (machineA);
        
        rentalA = new Rental ();
        rentalA.setCustomer (customerA);
        rentalA.setDateCreated (parseDate ("2016-08-01"));
        rentalA.setDateEnd     (parseDate ("2016-10-28"));
        rentalA.setDateStart   (parseDate ("2016-10-25"));
        rentalA.setMachine (machineA);
        rentalA.setNote ("The first rent of Pavel Zedníèek");
        rentalDao.create (rentalA);
    }

    @Test ()
    public void create () {
        Assert.assertEquals (rentalDao.findById (rentalA.getId ()).getCustomer (),    rentalA.getCustomer ());
        Assert.assertEquals (rentalDao.findById (rentalA.getId ()).getDateCreated (), rentalA.getDateCreated ());
        Assert.assertEquals (rentalDao.findById (rentalA.getId ()).getDateEnd (),     rentalA.getDateEnd ());
        Assert.assertEquals (rentalDao.findById (rentalA.getId ()).getDateStart (),   rentalA.getDateStart ());
        Assert.assertEquals (rentalDao.findById (rentalA.getId ()).getEmployee (),    rentalA.getEmployee ());
        Assert.assertEquals (rentalDao.findById (rentalA.getId ()).getMachine (),     rentalA.getMachine ());
        Assert.assertEquals (rentalDao.findById (rentalA.getId ()).getNote (),        rentalA.getNote ());
    }
    
    @Test ()
    public void delete () {
        Assert.assertNotNull (rentalDao.findById (rentalA.getId ()));
        rentalDao.delete (rentalA);
        Assert.assertNull (rentalDao.findById (rentalA.getId ()));
    }

    @Test
    public void findAll () {
        Machine machineB = new Machine ();
        machineB.setDescription ("velký žlutý bagr");
        machineB.setName ("BAGR 02");
        machineDao.create (machineB);
        
        RentalUser customerB = new RentalUser ();
        customerB.setEmail ("info@slovakostav.eu");
        customerB.setLegalPersonality (RentalUser.LegalPersonality.JURIDICAL);
        customerB.setName ("Slovakostav, s.r.o.");
        customerB.setRoles (Collections.singleton (RentalUser.Role.CUSTOMER));
        customerB.setUsername ("slovakostav");
        userDao.create (customerB);
        
        RentalUser employeeB;
        employeeB = new RentalUser ();
        employeeB.setEmail ("descartes@machinerentals.com");
        employeeB.setLegalPersonality (RentalUser.LegalPersonality.NATURAL);
        employeeB.setName ("René Descartes");
        employeeB.setRoles (Collections.singleton (RentalUser.Role.EMPLOYEE));
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
    public void findById () {
        Rental found = rentalDao.findById (rentalA.getId ());
        Assert.assertEquals (found, rentalA);
    }

    @Test
    public void nonExistentReturnsNull () {
        Assert.assertNull (rentalDao.findById (-1L));
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
            date = dateFormatter.parse (string);
        }
        catch (ParseException exception) {
            date = null;
        }
        return date;
    }
}
