package cz.muni.fi.pa165.machrent.sampleInstances;

import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.entities.Machine;

/**
 * Collection of sample entities used in various tests.
 * 
 * @author Josef Plch
 * @since  2016-11-24
 * @since  2016-11-25
 */
public abstract class SampleMachines {
    public static Machine newMachineBigMax () {
        Machine machine = new Machine ();
        machine.setDescription ("a huge yellow crane");
        machine.setId          (201L);
        machine.setName        ("Big Max");
        return machine;
    }
    
    public static MachineDto newMachineBigMaxDto () {
        Machine machine = newMachineBigMax ();
        MachineDto machineDto = new MachineDto ();
        machineDto.setDescription (machine.getDescription ());
        machineDto.setId          (machine.getId ());
        machineDto.setName        (machine.getName ());
        return machineDto;
    }
}
