package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.dao.MachineDao;
import cz.muni.fi.pa165.machrent.entities.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vaclav.kadera on 16-Nov-16.
 */
@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineDao machineDao;

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
}
