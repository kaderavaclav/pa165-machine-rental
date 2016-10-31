package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
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

/**
 * Created by vaclav.kadera on 30-Oct-16.
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    private RentalUser validUser;
    private long invalidId;
    private String invalidEmail;
    private RentalUser createdUser;

    @BeforeMethod
    public void setUp(){
        validUser = new RentalUser();
        validUser.setEmail("valid@email.com");
        validUser.setName("Valid User");
        validUser.setUsername("validUser");
        invalidId = 0L;
        invalidEmail = "invalidEmail";

        userDao.create(validUser);

        createdUser = userDao.findByEmail("valid@email.com");
    }

    @Test
    public void findById_NotNull(){
        RentalUser user = userDao.findById(createdUser.getId());
        Assert.assertNotNull(user,  "UserDao.findById() valid user Id was null!");
    }

    @Test
    public void findById_Null(){
        RentalUser nullUser = userDao.findById(invalidId);
        Assert.assertNull(nullUser, "UserDao.findById() invalid user Id was not null!");
    }

    @Test
    public void findByEmail_NotNull(){

        RentalUser user = userDao.findByEmail(createdUser.getEmail());
        Assert.assertNotNull(user, "UserDao.findByEmail() valid email was null!");
    }

    @Test
    public void findByEmail_Null(){
        RentalUser nullUser = userDao.findByEmail(invalidEmail);
        Assert.assertNull(nullUser, "UserDao.findByEmail() invalid email was not null!");
    }

    @Test
    public void findAll_NotNull(){
        // TODO: implement TestMethod
    }

    @Test
    public void setPassword_NotNull(){
        // TODO: implement TestMethod
    }

    @Test
    public void hashPassword_NotNull(){
        // TODO: implement TestMethod
    }

    @Test
    public void verifyPassword_EqualsTrue(){
        // TODO: implement TestMethod
    }

}
