package cz.muni.fi.pa165.machrent.service;

import cz.muni.fi.pa165.machrent.RentalUserService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dao.RentalUserDao;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import cz.muni.fi.pa165.machrent.exceptions.RentalUserServiceException;
import java.util.EnumSet;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author  vaclav.kadera
 * @since   2016-11-22
 * @version 2016-12-13
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
    private String validPassword;
    private String invalidPassword;

    @BeforeClass
    public void initTestClass() throws RentalUserServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initTestMethods() {
        validPassword = "123456*654321";
        invalidPassword = "thisIsInvalidPassword";

        validUser = new RentalUser();
        validUser.setId(1L);
        validUser.setUsername("validUser");
        validUser.setEmail("valid@email.com");
        validUser.setPassword(validPassword);
        validUser.setName("Valid User");
        validUser.setLegalPersonality(LegalPersonality.NATURAL);
        validUser.setRoles(EnumSet.of(RentalUserRole.CUSTOMER));
    }

    @Test
    public void registerUser_validUserAndPassword_daoMethodIsCalled () {
        rentalUserService.registerUser(validUser, validPassword);

        verify(rentalUserDao).create(validUser);
    }

    @Test
    public void getAllUsers_always_daoMethodIsCalled () {
        rentalUserService.getAllUsers();

        verify(rentalUserDao).findAll();
    }

    @Test
    public void findUserByUsername_usernameOfExistingUser_daoMethodIsCalled () {
        rentalUserService.findUserByUsername(validUser.getUsername());

        verify(rentalUserDao).findByUsername(validUser.getUsername());
    }

    @Test
    public void findUserById_idOfExistingUser_daoMethodIsCalled () {
        rentalUserService.findUserById(validUser.getId());

        verify(rentalUserDao, atMost(2)).findById(validUser.getId());
    }


    @Test
    public void findUserByEmail_emailOfExistingUser_daoMethodIsCalled () {
        rentalUserService.findUserByEmail(validUser.getEmail());

        verify(rentalUserDao).findByEmail(validUser.getEmail());
    }


    @Test
    public void authenticate_correctAndWrongPassword_returnTrueAndFalse () {
        assertTrue(rentalUserService.authenticate(validUser, validPassword));
        assertFalse(rentalUserService.authenticate(validUser, invalidPassword));
    }

    @Test
    public void deleteUser_idOfExistingUser_daoMethodIsCalled () {
        when(rentalUserDao.findById(validUser.getId())).thenReturn(validUser);
        rentalUserService.deleteUser(validUser.getId());

        verify(rentalUserDao, atMost(2)).findById(validUser.getId());
        verify(rentalUserDao).delete(validUser);
    }

    @Test
    public void updateUser_validUser_daoMethodIsCalled () {
        rentalUserService.updateUser(validUser);

        verify(rentalUserDao).update(validUser);
    }
}
