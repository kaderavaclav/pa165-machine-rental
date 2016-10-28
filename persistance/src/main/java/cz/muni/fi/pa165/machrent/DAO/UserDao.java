package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.User;
import java.util.List;

/**
 * Data access interface for entity User.
 * 
 * @author  Josef Plch
 * @since   2016-10-27
 * @version 2016-10-28
 */
public interface UserDao {
    public void create (User user);
    
    public void delete (User user);
    
    public User findById (Long id);
    
    public User findByEmail (String email);
    
    public List <User> findAll ();
    
    public void update (User user);
}
