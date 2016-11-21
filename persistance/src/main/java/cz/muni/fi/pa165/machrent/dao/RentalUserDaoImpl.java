package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.RentalUser;
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
 * @version 2016-11-21
 */
@Repository
public class RentalUserDaoImpl implements RentalUserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create (RentalUser user) {
        em.persist (user);
    }

    @Override
    public void delete (RentalUser user) {
        em.remove (user);
    }
    
    @Override
    public List <RentalUser> findAll () {
        TypedQuery <RentalUser> query = em.createQuery ("select u from RentalUser u", RentalUser.class);
        return (List <RentalUser>) query.getResultList ();
    }
    
    @Override
    public RentalUser findByEmail (String email) {
        if (email == null || email.isEmpty ()) {
            throw new IllegalArgumentException ("null e-mail");
        }
        
        try {
            RentalUser user =
                em.createQuery ("select u from RentalUser u where u.email=:email", RentalUser.class)
                .setParameter ("email", email)
                .getSingleResult ();
            return user;
        }
        catch (NoResultException exception) {
            return null;
        }
    }

    @Override
    public RentalUser findById (Long id) {
        return em.find (RentalUser.class, id);
    }
    
    @Override
    public RentalUser findByUsername (String username) {
        if (username == null || username.isEmpty ()) {
            throw new IllegalArgumentException ("null username");
        }
        
        try {
            RentalUser user =
                em.createQuery ("select u from RentalUser u where u.username=:username", RentalUser.class)
                .setParameter ("username", username)
                .getSingleResult ();
            return user;
        }
        catch (NoResultException exception) {
            return null;
        }
    }

    @Override
    public void update (RentalUser user) {
        em.merge (user);
    }
}
