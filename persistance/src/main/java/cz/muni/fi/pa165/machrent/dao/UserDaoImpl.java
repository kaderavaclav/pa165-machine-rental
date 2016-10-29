package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery; 
import org.springframework.stereotype.Repository;
 
/**
 * Data access object for entity User.
 * 
 * @author  Josef Plch
 * @since   2016-10-27
 * @version 2016-10-28
 */
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create (User user) {
        em.persist (user);
    }

    @Override
    public void delete (User user) {
        em.remove (user);
    }
    
    @Override
    public List <User> findAll () {
        TypedQuery <User> query = em.createQuery ("select u from User u", User.class); // Not sure whether User or RentalUser.
        return (List <User>) query.getResultList ();
    }
    
    @Override
    public User findByEmail (String email) {
        if (email == null || email.isEmpty ()) {
            throw new IllegalArgumentException ("null e-mail");
        }
        
        try {
            User user =
                em.createQuery ("select u from User u where email=:email", User.class) // Not sure whether User or RentalUser.
                .setParameter ("email", email)
                .getSingleResult ();
            return user;
        }
        catch (NoResultException exception) {
            return null;
        }
    }

    @Override
    public User findById (Long id) {
        return em.find (User.class, id);
    }

    @Override
    public void update (User user) {
        em.merge (user);
    }
}
