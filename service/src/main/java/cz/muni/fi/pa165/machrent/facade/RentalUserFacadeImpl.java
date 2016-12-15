package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.RentalUserService;
import cz.muni.fi.pa165.machrent.dto.RentalUserAuthenticateDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author  Josef Plch
 * @since   2016-11-21
 * @version 2016-11-23
 */
@Service
@Transactional
public class RentalUserFacadeImpl implements RentalUserFacade {
    @Autowired
    private BeanMappingService beanMappingService; 
    
    @Autowired 
    private RentalUserService rentalUserService;
    
    @Override
    public boolean authenticate (String username, String password) {
        return rentalUserService.authenticate (
            rentalUserService.findUserByUsername (username),
            password
        );
    }

    private RentalUserDto convertToDto (RentalUser user) {
        return (user == null) ? null : beanMappingService.mapTo (user, RentalUserDto.class);
    }
    
    private RentalUser convertToEntity (RentalUserDto userDto) {
        return (userDto == null) ? null : beanMappingService.mapTo (userDto, RentalUser.class);
    }
    
    @Override
    public void deleteUser (Long userId) {
        rentalUserService.deleteUser (userId);
    }

    @Override
    public RentalUserDto findUserByEmail (String email) {
        return convertToDto (rentalUserService.findUserByEmail (email));
    }

    @Override
    public RentalUserDto findUserById (Long id) {
        return convertToDto (rentalUserService.findUserById (id));
    }

    @Override
    public RentalUserDto findUserByUsername (String username) {
        return convertToDto (rentalUserService.findUserByUsername (username));
    }

    @Override
    public Collection <RentalUserDto> getAllUsers () {
        return beanMappingService.mapTo (rentalUserService.getAllUsers (), RentalUserDto.class);
    }

    @Override
    public void registerUser (RentalUserDto userDto, String password) {
        rentalUserService.registerUser (convertToEntity (userDto), password);
    }
    
    @Override
    public void updateUser (RentalUserDto userDto) {
        rentalUserService.updateUser (convertToEntity (userDto));
    }
    
    @Override
    public RentalUserDto authUser(RentalUserAuthenticateDto userDto) {
        RentalUser user = rentalUserService.findUserByEmail(userDto.getEmail());
        if (user == null) {
            return null;
        }
        if (rentalUserService.authUser(user, userDto.getPassword())) {
            return beanMappingService.mapTo(user, RentalUserDto.class);
        }
        return null;
    }
    
    @Override
    public boolean isUserAdmin(long userId) {
        return rentalUserService.isAdmin(userId);
    }
}
