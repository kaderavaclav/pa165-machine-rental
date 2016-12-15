package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * Service layer for RentalUser.
 *
 * @author Josef Plch
 * @since 2016-11-21
 * @version 2016-11-23
 */
@Service
public interface RentalUserService {

    /**
     * Try to authenticate a user. Return true when the hashed password matches
     * the records.
     *
     * @param user The user to be authenticated.
     * @param password The password to try.
     * @return Password matches (yes/no).
     */
    public boolean authenticate(RentalUser user, String password);

    /**
     * Delete user from the database.
     *
     * @param userId ID of the user to be deleted.
     */
    public void deleteUser(Long userId);

    /**
     * Find user by e-mail address.
     *
     * @param email E-mail address to search by.
     * @return User with the given e-mail address (if any).
     */
    public RentalUser findUserByEmail(String email);

    /**
     * Find user by ID.
     *
     * @param id ID of the wanted user.
     * @return User with the given ID (if any).
     */
    public RentalUser findUserById(Long id);

    /**
     * Find user by username.
     *
     * @param username Username to search by.
     * @return User with the given username (if any).
     */
    public RentalUser findUserByUsername(String username);

    /**
     * Get all the registered users.
     *
     * @return A collection of all users.
     */
    public Collection<RentalUser> getAllUsers();

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param user The user to be registered.
     * @param password Unencrypted password.
     */
    public void registerUser(RentalUser user, String password);

    /**
     * Save the data of an existing user.
     *
     * @param user The use to be updated.
     */
    public void updateUser(RentalUser user);

    /**
     * Authenticate user
     *
     * @param user user to authenticate
     * @param password password to check
     * @return true if password is correct, false otherwise
     */
    public boolean authUser(RentalUser user, String password);

    public boolean isAdmin(long userId);
}
