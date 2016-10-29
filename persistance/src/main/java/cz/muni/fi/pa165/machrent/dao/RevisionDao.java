package cz.muni.fi.pa165.machrent.dao;
import cz.muni.fi.pa165.machrent.entities.Revision;

import java.util.List;

/**
 * Created by Falka on 27. 10. 2016.
 */
public interface RevisionDao {
    public Revision findById(Long id);
    public void create(Revision r);
    public void delete(Revision r);
    public void update(Revision r);
    public List<Revision> findAll();
}