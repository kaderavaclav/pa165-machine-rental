package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Machine;

import java.util.List;

/**
 * Created by vaclav.kadera on 16-Nov-16.
 */
public interface MachineService {
    /**
     * Method that creates {@code Machine} in database.
     * @param m {@code Machine} object to insert
     * @return {@code Long id} id of created {@code Machine}
     */
    Machine createMachine(Machine m);

    /**
     * Method that removes {@code Machine} from database.
     * @param m {@code Machine} to remove
     */
    void deleteMachine(Machine m);

    /**
     * Method that updates {@code Machine} object values in database.
     * @param m {@code Machine} object with modified values.
     */
    Machine updateMachine(Machine m);

    /**
     * Method that returns all {@code Machine} objects from database.
     * @return {@code List<Machine>} that contains all {@code Machine} objects in database.
     */
    List<Machine> findAllMachines();

    /**
     * Method that returns {@code MachineDto} with specified {@code Long id}.
     * @param id {@code Long} of Machine to get
     * @return {@code Machine} object
     */
    Machine findById(Long id);
}
