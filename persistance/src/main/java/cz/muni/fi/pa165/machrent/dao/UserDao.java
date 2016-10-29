package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.User;
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
    
    public List <User> findAll ();
    
    public User findByEmail (String email);
    
    public User findById (Long id);
    
    public void update (User user);
}
