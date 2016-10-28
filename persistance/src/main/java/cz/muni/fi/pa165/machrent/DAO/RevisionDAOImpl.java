package cz.muni.fi.pa165.machrent.DAO;
import cz.muni.fi.pa165.machrent.Entities.Revision;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Created by Falka on 27. 10. 2016.
 */
public class RevisionDAOImpl implements RevisionDAO {

    @PersistenceContext
    private EntityManager em;

    public Revision findById(Long id) {
        return em.find(Revision.class, id);
    }

    public void create(Revision r) {
        em.persist(r);

    }

    public void delete(Revision r) {
        em.remove(r);

    }

    public void update(Revision r) {
        em.merge(r);

    }

    public List<Revision> findAll() {
        return em.createQuery("SELECT r FROM revision r", Revision.class).getResultList();

    }

}