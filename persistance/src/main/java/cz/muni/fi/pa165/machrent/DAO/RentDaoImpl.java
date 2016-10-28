/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.Rent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Peter Benus
 */
@Repository
public class RentDaoImpl implements RentDAO{

    @PersistenceContext
	private EntityManager em; 
    
    @Override
    public void create(Rent r) {
        em.persist(r);
    }

    @Override
    public void delete(Rent r) {
        em.remove(r);
    }

    @Override
    public void update(Rent r) {
        em.merge(r);
    }

    @Override
    public Rent findById(Long id) {
        return em.find (Rent.class, id);
    }

    @Override
    public List<Rent> findAll(Rent r) {
        return em.createQuery("SELECT r FROM Rent r", Rent.class).getResultList();
    }

    
    
}
