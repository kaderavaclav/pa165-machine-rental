package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rent;
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
public class RentDaoTest extends AbstractTestNGSpringContextTests {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyyy-MM-dd");
    
    @Autowired
    private MachineDao machineDao;
    
    private Rent rentA;
    
    @Autowired
    private RentDao rentDao;
    
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
        
        rentA = new Rent ();
        rentA.setCustomer (customerA);
        rentA.setDateCreated (parseDate ("2016-08-01"));
        rentA.setDateEnd     (parseDate ("2016-10-28"));
        rentA.setDateStart   (parseDate ("2016-10-25"));
        rentA.setMachine (machineA);
        rentA.setNote ("The first rent of Pavel Zedníèek");
        rentDao.create (rentA);
    }

    @Test ()
    public void create () {
        Assert.assertEquals (rentDao.findById (rentA.getId ()).getCustomer (),    rentA.getCustomer ());
        Assert.assertEquals (rentDao.findById (rentA.getId ()).getDateCreated (), rentA.getDateCreated ());
        Assert.assertEquals (rentDao.findById (rentA.getId ()).getDateEnd (),     rentA.getDateEnd ());
        Assert.assertEquals (rentDao.findById (rentA.getId ()).getDateStart (),   rentA.getDateStart ());
        Assert.assertEquals (rentDao.findById (rentA.getId ()).getEmployee (),    rentA.getEmployee ());
        Assert.assertEquals (rentDao.findById (rentA.getId ()).getMachine (),     rentA.getMachine ());
        Assert.assertEquals (rentDao.findById (rentA.getId ()).getNote (),        rentA.getNote ());
    }
    
    @Test ()
    public void delete () {
        Assert.assertNotNull (rentDao.findById (rentA.getId ()));
        rentDao.delete (rentA);
        Assert.assertNull (rentDao.findById (rentA.getId ()));
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
        
        Rent rentB = new Rent ();
        rentB.setCustomer (customerB);
        rentB.setDateCreated (parseDate ("2010-12-24"));
        rentB.setDateEnd     (parseDate ("2011-01-14"));
        rentB.setDateStart   (parseDate ("2011-01-01"));
        rentB.setEmployee (employeeB);
        rentB.setMachine (machineB);
        rentB.setNote ("The first rent of Slovakostav");
        rentDao.create (rentB);

        List <Rent> revisions = rentDao.findAll ();
        Assert.assertEquals (revisions.size (), 2);
    }

    @Test
    public void findById () {
        Rent found = rentDao.findById (rentA.getId ());
        Assert.assertEquals (found, rentA);
    }

    @Test
    public void nonExistentReturnsNull () {
        Assert.assertNull (rentDao.findById (-1L));
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
