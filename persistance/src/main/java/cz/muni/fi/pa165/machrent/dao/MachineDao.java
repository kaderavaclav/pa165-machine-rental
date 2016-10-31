package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import java.util.List;

/**
 *  Interface that provides basic persistent operations for entity Machine.
 *  @author vaclav.kadera
 */
public interface MachineDao {

    /**
     * Method that creates {@code Machine} in database.
     * @param m {@code Machine} object to insert
     */
    void create(Machine m);

    /**
     * Method that removes {@code Machine} from database.
     * @param m {@code Machine} object to remove
     */
    void delete(Machine m);

    /**
     * Method that updates {@code Machine} object values in database.
     * @param m {@code Machine} object with modified values.
     */
    Machine update(Machine m);

    /**
     * Method that returns all {@code Machine} objects from database.
     * @return {@code List<Machine>} that contains all {@code Machine} objects in database.
     */
    List<Machine> findAll();

    /**
     * Method that returns {@code Machine} with specified {@code id} from database.
     * @param id {@code Long id} of {@code Machine}
     * @return {@code Machine} object with specified {@code id}
     */
    Machine findById(long id);

}
