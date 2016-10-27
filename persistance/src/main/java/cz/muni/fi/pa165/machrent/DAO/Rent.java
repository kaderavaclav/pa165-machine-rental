/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.RentEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Peter Benus
 */
@Repository
public class Rent implements RentDAO{

    @PersistenceContext
	private EntityManager em; 
    
    public void create(RentEntity r) {
        em.persist(r);
    }

    public void delete(RentEntity r) {
        em.remove(r);
    }

    public void update(RentEntity r) {
        em.merge(r);
    }

    public List<RentEntity> get(RentEntity r) {  
        return em.createQuery("SELECT r FROM rent r", RentEntity.class).getResultList();
    }

    
    
}
