package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by vaclav.kadera on 16-Dec-16.
 */
public class MachineCreateDtoValidator implements Validator{

    private MachineFacade machineFacade;

    public void setMachineFacade(MachineFacade machineFacade) {
        this.machineFacade = machineFacade;
    }

    @Override
    public boolean supports(Class<?> type) {
        return MachineCreateDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MachineCreateDto machineCreateDto = (MachineCreateDto) target;

        String name = machineCreateDto.getName();


        if (name == null || name == "") {
            errors.rejectValue("name", "notNull", "Machine name cannot be empty!");
        }
    }


}
