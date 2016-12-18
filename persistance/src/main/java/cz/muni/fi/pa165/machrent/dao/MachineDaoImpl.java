package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import org.springframework.stereotype.Repository;

import javax.crypto.Mac;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by vaclav.kadera on 27-Oct-16.
 */
@Repository
public class MachineDaoImpl implements MachineDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Machine m) {
        em.persist(m);
    }

    @Override
    public void delete(Machine m) {
        em.remove(m);
    }

    @Override
    public Machine update(Machine m) { return em.merge(m); }

    @Override
    public List<Machine> findAll() {
        return em.createQuery("SELECT m FROM " + Machine.class.getName() + " m", Machine.class).getResultList();
    }

    @Override
    public Machine findById(long id) {
        return em.find(Machine.class, id);
    }
}
