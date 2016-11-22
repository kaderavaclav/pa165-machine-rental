package cz.muni.fi.pa165.machrent.facade;

import cz.muni.fi.pa165.machrent.BeanMappingService;
import cz.muni.fi.pa165.machrent.MachineService;
import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.MachineUpdateDto;
import cz.muni.fi.pa165.machrent.entities.Machine;
import cz.muni.fi.pa165.machrent.exceptions.MachineServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vaclav.kadera on 16-Nov-16.
 */
@Service
@Transactional
public class MachineFacadeImpl implements MachineFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private MachineService ms;

    @Override
    public Long createMachine(MachineCreateDto m) {

        Machine n = new Machine();
        n.setName(m.getName());
        n.setDescription(m.getDescription());

        ms.createMachine(n);

        return n.getId();
    }

    @Override
    public void deleteMachine(Long id) {
        Machine m = ms.findById(id);

        if(m == null)
            throw new MachineServiceException("Machine with id: " + id + " doesn't exist!");

        ms.deleteMachine(ms.findById(id));
    }

    @Override
    public void updateMachine(MachineUpdateDto m) {

        Machine updated = beanMappingService.mapTo(m, Machine.class);
        ms.updateMachine(updated);
    }

    @Override
    public List<MachineDto> findAllMachines() {

        return beanMappingService.mapTo(ms.findAllMachines(), MachineDto.class);
    }

    @Override
    public MachineDto findById(Long id) {

        return beanMappingService.mapTo(ms.findById(id), MachineDto.class);
    }
}
