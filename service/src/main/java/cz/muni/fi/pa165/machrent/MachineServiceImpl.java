package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.dao.MachineDao;
import cz.muni.fi.pa165.machrent.dao.RentalDao;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.entities.Rental;
import java.util.Date;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by vaclav.kadera on 16-Nov-16.
 */
@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineDao machineDao;

    @Autowired
    private RentalDao rentalDao;

    @Override
    public Machine createMachine(Machine m) {
        machineDao.create(m);
        return m;
    }

    @Override
    public void deleteMachine(Machine m) {

        machineDao.delete(m);
    }

    @Override
    public Machine updateMachine(Machine m){

        return machineDao.update(m);
    }

    @Override
    public List<Machine> findAllMachines() {

        return machineDao.findAll();
    }

    @Override
    public Machine findById(Long id) {
        return machineDao.findById(id);
    }
    
    @Override
    public List <Machine> findAvailableMachines (Date from, Date to) {
        List <Rental> rentals = rentalDao.findAllEffectiveBetween (from, to);
        Set <Machine> usedMachines = new HashSet <> ();
        for (Rental rental : rentals) {
            usedMachines.add (rental.getMachine());
        }
        
        List <Machine> machineList = machineDao.findAll();
        machineList.removeAll (usedMachines);
        return machineList;
    }
}
