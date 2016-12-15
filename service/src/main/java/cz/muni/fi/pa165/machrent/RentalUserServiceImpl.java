package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.dao.RentalUserDao;
import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * @author  Josef Plch
 * @since   2016-11-21
 * @version 2016-11-23
 */
@Service
public class RentalUserServiceImpl implements RentalUserService {
    @Inject
    private RentalUserDao rentalUserDao;

    @Override
    public boolean authenticate (RentalUser user, String password) {
        return user.verifyPassword (password);
    }

    @Override
    public void deleteUser (Long userId) {
        rentalUserDao.delete (rentalUserDao.findById (userId));
    }

    @Override
    public RentalUser findUserByEmail (String email) {
        return rentalUserDao.findByEmail (email);
    }

    @Override
    public RentalUser findUserById (Long id) {
        return rentalUserDao.findById (id);
    }

    @Override
    public RentalUser findUserByUsername (String username) {
        return rentalUserDao.findByUsername (username);
    }

    @Override
    public Collection <RentalUser> getAllUsers () {
        return rentalUserDao.findAll ();
    }

    @Override
    public void registerUser (RentalUser user, String password) {
        user.setPassword (password);
        rentalUserDao.create (user);
    }
    
    public void updateUser (RentalUser user) {
        rentalUserDao.update (user);
    }

    @Override
    public boolean isAdmin(long userId) {
        RentalUser u = findUserById(userId);
        if (u == null) {
            return false;
        }
        return u.isAdmin();
    }
}
