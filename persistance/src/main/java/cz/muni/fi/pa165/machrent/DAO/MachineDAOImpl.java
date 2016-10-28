package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.Machine;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by vaclav.kadera on 27-Oct-16.
 */
public class MachineDAOImpl implements MachineDAO {

    private EntityManager em;

    public void create(Machine m) {
        em.persist(m);
    }

    public void delete(Machine m) {
        em.remove(m);
    }

    public void update(Machine m) {
        em.merge(m);
    }

    public List<Machine> findAll() {
        return em.createQuery("SELECT m FROM Machine m", Machine.class).getResultList();
    }

    public Machine findById(long id) {
        return em.find(Machine.class, id);
    }
}
