package cz.muni.fi.pa165.machrent.dao;

import cz.muni.fi.pa165.machrent.entities.Machine;
import java.util.List;

/**
 * Created by vaclav.kadera on 27-Oct-16.
 */
public interface MachineDao {

    void create(Machine m);
    void delete(Machine m);
    void update(Machine m);
    List<Machine> findAll();
    Machine findById(long id);

}
