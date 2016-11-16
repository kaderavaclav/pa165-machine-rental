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
import javax.validation.ConstraintViolationException;

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
    private RentalUser userToDelete;
    private long invalidId;
    private String invalidEmail;
    private String updatedEmail;
    private RentalUser invalidUser;

    @BeforeMethod
    public void setUp(){
        validUser = new RentalUser();
        validUser.setEmail("valid@email.com");
        validUser.setName("Valid User");
        validUser.setUsername("validUser");
        validUser.setLegalPersonality(RentalUser.LegalPersonality.NATURAL);

        updatedEmail = "newEmail@test.it";

        userToDelete = new RentalUser();
        userToDelete.setEmail("valid2@email.com");
        userToDelete.setName("Valid2 User");
        userToDelete.setUsername("userToDelete");
        userToDelete.setLegalPersonality(RentalUser.LegalPersonality.NATURAL);

        invalidId = 0L;
        invalidEmail = "invalidEmail";
        invalidUser = new RentalUser();
        invalidUser.setName("invalid User");
        invalidUser.setUsername("invalidUser");
        invalidUser.setEmail(invalidEmail);
        invalidUser.setLegalPersonality(RentalUser.LegalPersonality.NATURAL);

        userDao.create(validUser);
        userDao.create(userToDelete);
    }

    @Test
    public void create_isNotNull() {
        userDao.create(validUser);
        userDao.create(userToDelete);
        Assert.assertNotNull(userDao.findById(validUser.getId()));
        Assert.assertNotNull(userDao.findById(userToDelete.getId()));
    }

    @Test
    public void delete_isNull(){
        Long id = userToDelete.getId();
        userDao.delete(userToDelete);
        Assert.assertNull(userDao.findById(id));
    }

    @Test
    public void findById_isNotNull(){
        RentalUser user = userDao.findById(validUser.getId());
        Assert.assertNotNull(user,  "UserDao.findById() valid user Id should not be null!");
    }

    @Test
    public void findById_isNull(){
        RentalUser nullUser = userDao.findById(invalidId);
        Assert.assertNull(nullUser, "UserDao.findById() invalid user Id should be null!");
    }

    @Test
    public void findByEmail_isNotNull(){
        RentalUser user = userDao.findByEmail(validUser.getEmail());
        Assert.assertNotNull(user, "UserDao.findByEmail() valid email should not be null!");
    }

    @Test
    public void findByEmail_isNull(){
        RentalUser nullUser = userDao.findByEmail(invalidEmail);
        Assert.assertNull(nullUser, "UserDao.findByEmail() invalid email should be null!");
    }

    @Test
    public void findAll_isNotNull(){
        Assert.assertNotNull(userDao.findAll(), "UserDao.findAll() should not be null!");
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void setNameNull_throwsException(){
        invalidUser.setName(null);
        userDao.create(invalidUser);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void setEmailNull_throwsException(){
        invalidUser.setEmail(null);
        userDao.create(invalidUser);
    }

    @Test(expectedExceptions=ConstraintViolationException.class)
    public void setUsernameNull_throwsException(){
        invalidUser.setUsername(null);
        userDao.create(invalidUser);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void setLegalPersonalityNull_throwsException(){
        invalidUser.setLegalPersonality(null);
        userDao.create(invalidUser);
    }

    @Test
    public void update_isEqual(){
        validUser.setEmail(updatedEmail);
        userDao.update(validUser);
        Assert.assertEquals(validUser.getEmail(), updatedEmail);
    }
}
