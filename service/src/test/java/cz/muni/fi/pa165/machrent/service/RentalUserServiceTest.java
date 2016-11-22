package cz.muni.fi.pa165.machrent.service;

import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.RentalUserService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dao.RentalUserDao;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.exceptions.MachrentServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by vaclav.kadera on 22-Nov-16.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RentalUserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private RentalUserDao rentalUserDao;

    @Autowired
    @InjectMocks
    private RentalUserService rentalUserService;

    private RentalUser validUser;


    @BeforeClass
    public void initTestClass() throws MachrentServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initTestMethods(){

    }

    @Test
    public void Test1(){
        Assert.assertNull(null);
    }

}
