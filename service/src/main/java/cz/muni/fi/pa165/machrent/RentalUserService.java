package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * Service layer for RentalUser.
 * 
 * @author  Josef Plch
 * @since   2016-11-21
 * @version 2016-11-21
 */
@Service
public interface RentalUserService {
    /**
     * Try to authenticate a user. Return true when the hashed password matches
     * the records.
     * 
     * @param user     The user to be authenticated.
     * @param password The password to try.
     * @return         Password matches (yes/no).
     */
    public boolean authenticate (RentalUser user, String password);

    public void deleteUser (Long userId);
    
    public RentalUser findUserByEmail (String email);
    
    public RentalUser findUserById (Long id);
    
    public RentalUser findUserByUsername (String username);
    
    /**
     * Get all the registered users.
     * 
     * @return A collection of all users.
     */
    public Collection <RentalUser> getAllUsers ();

    /**
     * Check whether the given user is employee.
     * 
     * @param user The user to be checked.
     * @return     Yes/no.
     */
    public boolean isEmployee (RentalUser user);
    
    /**
     * Register the given user with the given unencrypted password.
     * 
     * @param user     The user to be registered.
     * @param password Unencrypted password.
     */
    public void registerUser (RentalUser user, String password);
}
