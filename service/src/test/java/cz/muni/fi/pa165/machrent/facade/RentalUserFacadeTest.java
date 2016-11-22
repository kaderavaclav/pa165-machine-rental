package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by vaclav.kadera on 22-Nov-16.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RentalUserFacadeTest extends AbstractTestNGSpringContextTests {


    @BeforeMethod
    public void testInit(){

    }

    @Test
    public void Test1(){
        Assert.assertNull(null);
    }

}
