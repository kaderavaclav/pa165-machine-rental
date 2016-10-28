package cz.muni.fi.pa165.machrent.DAO;

import cz.muni.fi.pa165.machrent.Entities.Machine;
import java.util.List;

/**
 * Created by vaclav.kadera on 27-Oct-16.
 */
public interface MachineDAO {

    void create(Machine m);
    void delete(Machine m);
    void update(Machine m);
    List<Machine> findAll();
    Machine findById(long id);

}
