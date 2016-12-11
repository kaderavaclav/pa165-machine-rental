package cz.muni.fi.pa165.machrent.rest.controllers;

import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import cz.muni.fi.pa165.machrent.rest.ApiUris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by vaclav.kadera on 11-Dec-16.
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_MACHINES)
public class MachineController {

    @Autowired
    private MachineFacade machineFacade;

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MachineDto> findAllMachines(){
        return machineFacade.findAllMachines();
    }

}
