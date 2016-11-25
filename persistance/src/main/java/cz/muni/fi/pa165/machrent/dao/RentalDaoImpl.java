/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Rental;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Peter Benus
 */
@Repository
public class RentalDaoImpl implements RentalDao{

    @PersistenceContext
	private EntityManager em; 
    
    @Override
    public void create(Rental r) {
        em.persist(r);
    }

    @Override
    public void delete(Rental r) {
        em.remove(r);
    }

    @Override
    public void update(Rental r) {
        em.merge(r);
    }

    @Override
    public Rental findById(Long id) {
        return em.find (Rental.class, id);
    }

    @Override
    public List<Rental> findAll() {
        return em.createQuery("SELECT r FROM Rental r", Rental.class).getResultList();
    }

    @Override
    public List<Rental> findAllEffectiveBetween(Date startDate, Date endDate) {
        String sqlQuery =
            "SELECT r FROM Rental r WHERE ("
                + "(r.dateStart   >= :startDate AND r.dateCreated <= :endDate)"
                + " OR (r.dateEnd >= :startDate AND r.dateEnd     <= :endDate)"
            + ")";
        
        TypedQuery <Rental> query =
            em.createQuery (sqlQuery, Rental.class)
            .setParameter ("startDate", startDate)
            .setParameter ("endDate",   endDate);
        
        return query.getResultList ();
    }
    
    @Override
    public List<Rental> findAllCreatedBetween(Date startDate, Date endDate) {
        return em.createQuery("SELECT r FROM Rental r WHERE r.dateCreated >= :startDate" 
                + " AND r.dateCreated <= :endDate", Rental.class).
                setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }
}
