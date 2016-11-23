package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import java.util.Collection;

/**
 * Facade layer for RentalUser.
 *
 * @author  Josef Plch
 * @since   2016-11-21
 * @version 2016-11-23
 */
public interface RentalUserFacade {
    /**
     * Try to authenticate a user. Return true when the hashed password matches
     * the records.
     * 
     * @param username Username of the user to be authenticated.
     * @param password The password to try.
     * @return         Password matches (yes/no).
     */
    public boolean authenticate (String username, String password);

    public void deleteUser (Long userId);
    
    public RentalUserDto findUserByEmail (String email);
    
    public RentalUserDto findUserById (Long id);
    
    public RentalUserDto findUserByUsername (String username);
    
    /**
     * Get all the registered users.
     * 
     * @return A collection of all users.
     */
    public Collection <RentalUserDto> getAllUsers ();

    /**
     * Register the given user with the given unencrypted password.
     * 
     * @param user     The user to be registered.
     * @param password Unencrypted password.
     */
    public void registerUser (RentalUserDto user, String password);
    
    /**
     * Save the data of an existing user.
     * 
     * @param user The use to be updated.
     */
    public void updateUser (RentalUserDto user);
}
