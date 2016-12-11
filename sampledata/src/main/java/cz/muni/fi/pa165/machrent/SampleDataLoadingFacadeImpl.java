package cz.muni.fi.pa165.machrent;

import cz.muni.fi.pa165.machrent.entities.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by vaclav.kadera on 11-Dec-16.
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private MachineService machineService;

    @Override
    public void loadData() throws IOException {

        Machine("my Machine", "Its desc");


    }

    private Machine Machine(String name, String description){
        Machine m = new Machine();
        m.setName("myMachine");
        m.setDescription("machine desc");

        machineService.createMachine(m);
        return m;
    }
}
