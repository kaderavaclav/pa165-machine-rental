package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by vaclav.kadera on 16-Dec-16.
 */
public class MachineCreateDtoValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return MachineCreateDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MachineCreateDto machineCreateDto = (MachineCreateDto) target;

        String name = machineCreateDto.getName();
        if (name == null || StringUtils.trimAllWhitespace(name).length() == 0) {
            errors.rejectValue("name", "notNull", "Machine name cannot be empty or whitespace!");
        }
    }


}
