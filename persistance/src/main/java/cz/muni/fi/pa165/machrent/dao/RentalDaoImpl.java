/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Rental;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    
    
}