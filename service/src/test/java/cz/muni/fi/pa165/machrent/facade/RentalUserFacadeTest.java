package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.PersistenceApplicationContext;
import cz.muni.fi.pa165.machrent.RentalUserService;
import cz.muni.fi.pa165.machrent.config.ServiceConfiguration;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import cz.muni.fi.pa165.machrent.exceptions.MachrentServiceException;
import cz.muni.fi.pa165.machrent.exceptions.RentalUserServiceException;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Created by vaclav.kadera on 22-Nov-16.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RentalUserFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private RentalUserService rentalUserService;

    @InjectMocks
    private RentalUserFacade rentalUserFacade = new RentalUserFacadeImpl();

    private RentalUserDto validUserDto;
    private RentalUserDto invalidUserDto;
    private RentalUser validUser;
    private String validPassword;
    private List<RentalUser> users;

    @BeforeClass
    public void initTestClass() throws RentalUserServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initTestMethods() {

        validUserDto = new RentalUserDto();
        validUserDto.setId(1L);
        validUserDto.setUsername("validUser");
        validUserDto.setEmail("valid@email.com");
        validUserDto.setName("Valid User");
        validUserDto.setLegalPersonality(LegalPersonality.NATURAL);
        validUserDto.setRoles(EnumSet.of(RentalUserRole.CUSTOMER));

        validUser = new RentalUser();
        validUser.setId(1L);
        validUser.setUsername("validUser");
        validUser.setEmail("valid@email.com");
        validUser.setName("Valid User");
        validUser.setLegalPersonality(LegalPersonality.NATURAL);
        validUser.setRoles(EnumSet.of(RentalUserRole.CUSTOMER));

        invalidUserDto = new RentalUserDto();

        validPassword = "123456*654321";

        users = new ArrayList<>();
        users.add(validUser);

    }

    @Test
    public void registerUser() {
        when(beanMappingService.mapTo(validUserDto, RentalUser.class)).thenReturn(validUser);

        rentalUserFacade.registerUser(validUserDto, validPassword);

        verify(rentalUserService).registerUser(validUser, validPassword);
    }


    @Test
    public void getAllUsers() {
        when(rentalUserService.getAllUsers()).thenReturn(users);

        rentalUserFacade.getAllUsers();

        verify(rentalUserService).getAllUsers();
    }

    @Test
    public void findUserByUsername() {
        when(rentalUserService.findUserByUsername("validUser")).thenReturn(validUser);
        when(beanMappingService.mapTo(validUser, RentalUserDto.class)).thenReturn(validUserDto);

        RentalUserDto actual = rentalUserFacade.findUserByUsername(validUserDto.getUsername());
        assertEquals(actual, validUserDto);
    }

    @Test
    public void findUserById() {
        when(rentalUserService.findUserById(1L)).thenReturn(validUser);
        when(beanMappingService.mapTo(validUser, RentalUserDto.class)).thenReturn(validUserDto);

        RentalUserDto actual = rentalUserFacade.findUserById(validUserDto.getId());
        assertEquals(actual, validUserDto);
    }

    @Test
    public void findUserByEmail() {
        when(rentalUserService.findUserByEmail("valid@email.com")).thenReturn(validUser);
        when(beanMappingService.mapTo(validUser, RentalUserDto.class)).thenReturn(validUserDto);

        RentalUserDto actual = rentalUserFacade.findUserByEmail(validUserDto.getEmail());
        assertEquals(actual, validUserDto);
    }

    @Test
    public void authenticate() {
        when(rentalUserService.findUserByUsername("validUser")).thenReturn(validUser);
        when(rentalUserService.authenticate(validUser, validPassword)).thenReturn(true);

        rentalUserFacade.authenticate(validUserDto.getUsername(), validPassword);

        verify(rentalUserService).findUserByUsername("validUser");
        verify(rentalUserService).authenticate(validUser, validPassword);
    }

    @Test
    public void deleteUser() {
        rentalUserFacade.deleteUser(validUserDto.getId());

        verify(rentalUserService).deleteUser(validUserDto.getId());
    }

    @Test
    public void updateUser(){
        when(beanMappingService.mapTo(validUserDto, RentalUser.class)).thenReturn(validUser);
        rentalUserFacade.updateUser(validUserDto);

        verify(rentalUserService).updateUser(validUser);
    }

}
