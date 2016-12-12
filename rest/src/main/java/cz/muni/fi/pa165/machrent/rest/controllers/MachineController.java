package cz.muni.fi.pa165.machrent.rest.controllers;

import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.MachineUpdateDto;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import cz.muni.fi.pa165.machrent.rest.ApiUris;
import cz.muni.fi.pa165.machrent.rest.exceptions.AlreadyExistsException;
import cz.muni.fi.pa165.machrent.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.machrent.rest.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    final static Logger logger = LoggerFactory.getLogger(MachineController.class);

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MachineDto> findAllMachines(){
        logger.debug("rest call findAllMachines()");
        return machineFacade.findAllMachines();
    }

    @RequestMapping(value = "/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MachineDto findMachine(@PathVariable("id") long id) throws Exception {
        logger.debug("rest call findById({id})", id);

        try{
            MachineDto machine = machineFacade.findById(id);
            return machine;
        } catch (Exception ex){
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteMachine(@PathVariable("id") long id) throws Exception{
        logger.debug("rest call deleteMachine({})", id);
        try{
            machineFacade.deleteMachine(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/create", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final MachineDto createMachine(@RequestBody MachineCreateDto machine) throws Exception{
        logger.debug("rest call createMachine()");
        try{
            Long id = machineFacade.createMachine(machine);
            return machineFacade.findById(id);
        } catch (Exception ex) {
            throw new AlreadyExistsException();
        }
    }

    @RequestMapping(value = "/update", method = PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final MachineDto updateMachine(@RequestBody MachineUpdateDto machine) throws Exception{
        logger.debug("rest call updateMachine({id})", machine.getId());
        try{
            machineFacade.updateMachine(machine);
            return machineFacade.findById(machine.getId());
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }


}
