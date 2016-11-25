package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.MachineUpdateDto;

import java.util.Date;
import java.util.List;

/**
 * Created by vaclav.kadera on 16-Nov-16.
 */
public interface MachineFacade {

    /**
     * Method that creates {@code Machine} in database.
     * @param m {@code Machine} object to insert
     * @return {@code Long id} id of created {@code Machine}
     */
    Long createMachine(MachineCreateDto m);

    /**
     * Method that removes {@code Machine} from database.
     * @param id {@code Long} of Machine to remove
     */
    void deleteMachine(Long id);

    /**
     * Method that updates {@code Machine} object values in database.
     * @param m {@code MachineUpdateDto} object with modified values.
     */
    void updateMachine(MachineUpdateDto m);

    /**
     * Method that returns all {@code Machine} objects from database.
     * @return {@code List<MachineDto>} that contains all {@code Machine} objects in database.
     */
    List<MachineDto> findAllMachines();

    /**
     * Method that returns {@code MachineDto} with specified {@code Long id}.
     * @param id {@code Long} of Machine to get
     * @return {@code MachineDto} object
     */
    MachineDto findById(Long id);

    /**
     * Method that returns all available {@code Machine} within specified dates.
     * @param from {@code java.util.Date}
     * @param to {@code java.util.Date}
     * @return {@code MachineDto} object
     */
    List<MachineDto> findAvailableMachines(Date from, Date to);

}
