package cz.muni.fi.pa165.machrent.dao;
import cz.muni.fi.pa165.machrent.entities.Revision;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Created by Falka on 27. 10. 2016.
 */
@Repository
public class RevisionDaoImpl implements RevisionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Revision findById(Long id) {
        return em.find(Revision.class, id);
    }

    @Override
    public void create(Revision r) {
        em.persist(r);
    }

    @Override
    public void delete(Revision r) {
        em.remove(r);
    }

    @Override
    public void update(Revision r) {
        em.merge(r);
    }

    public List<Revision> findAll() {
        return em.createQuery("SELECT r FROM Revision r", Revision.class).getResultList();
    }

}