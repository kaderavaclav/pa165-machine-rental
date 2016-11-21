package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.List;

/**
 * Data access interface for entity RentalUser.
 * 
 * @author  Josef Plch
 * @since   2016-10-27
 * @version 2016-11-21
 */
public interface RentalUserDao {
    /**
     * Persist the user (save him/her into some storage).
     * 
     * @param user The user to be created.
     */
    public void create (RentalUser user);
    
    /**
     * Delete the user from the storage.
     * 
     * @param user The user to be deleted.
     */
    public void delete (RentalUser user);
    
    /**
     * Get all known users from the storage.
     * 
     * @return List of all users.
     */
    public List <RentalUser> findAll ();
    
    /**
     * Find user with the given e-mail address. (The adresses are unique.)
     * 
     * @param email The e-mail address to search by.
     * @return A user with the given e-mail address (if any).
     */
    public RentalUser findByEmail (String email);
    
    /**
     * Find user with the given ID.
     * 
     * @param id The ID of the wanted user.
     * @return A user with the given ID (if any).
     */
    public RentalUser findById (Long id);
    
    /**
     * Find user with the given username. (The usernames are unique.)
     * 
     * @param username The username address to search by.
     * @return A user with the given username (if any).
     */
    public RentalUser findByUsername (String username);    
    
    /**
     * Persist the changes – basicly the same as create, but for an already-
     * -existing user.
     * 
     * @param user A user to be updated.
     */
    public void update (RentalUser user);
}
