package cz.muni.fi.pa165.machrent.dao;
import cz.muni.fi.pa165.machrent.entities.Revision;

import java.util.List;

/**
 * Created by zuz-schwarzova on 27. 10. 2016.
 */
public interface RevisionDao {

    /**
     * Finds revision with given id database.
     *
     * @throws IllegalArgumentException when parameter is null
     *
     * @param id to find
     * @return revision with given id
     */
    public Revision findById(Long id);

    /**
     * Finds revision with given id database.
     *
     * @throws IllegalArgumentException when parameter is null
     *
     * @param id to of machine
     * @return revision with given id
     */
    public List<Revision> findAllByMachineId(Long id);

    /**
     * Saves created revision entity given by parameter into database.
     *
     * @throws IllegalArgumentException when parameter is null
     *
     * @param r revision to create
     */
    public void create(Revision r);

    /**
     * Remove given revision from database.
     *
     * @throws IllegalArgumentException when parameter is null
     *
     * @param r revision to delete
     */
    public void delete(Revision r);

    /**
     * Update entry in database with new values given by revision entity in parameter.
     *
     * @throws IllegalArgumentException when parameter is null
     *
     * @param r revision to update
     */
    public void update(Revision r);

    /**
     * Select all revisions saved in database.
     *
     * @return List of revisions entities.
     */
    public List<Revision> findAll();
}