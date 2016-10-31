package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.RentalUser;
import java.util.List;

/**
 * Data access interface for entity User.
 * 
 * @author  Josef Plch
 * @since   2016-10-27
 * @version 2016-10-28
 */
public interface UserDao {
    public void create (RentalUser user);
    
    public void delete (RentalUser user);
    
    public List <RentalUser> findAll ();
    
    public RentalUser findByEmail (String email);
    
    public RentalUser findById (Long id);
    
    public void update (RentalUser user);
}
