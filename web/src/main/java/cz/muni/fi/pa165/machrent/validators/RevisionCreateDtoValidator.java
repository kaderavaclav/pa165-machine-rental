package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.dto.RevisionCreateDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zuz-schwarzova on 16. 12. 2016.
 */
public class RevisionCreateDtoValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return RevisionCreateDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RevisionCreateDto revisionCreateDto = (RevisionCreateDto) target;

        String note = revisionCreateDto.getNote();
        if (note.length() > 254) {
            errors.rejectValue("note", "tooLong", "The note is too long.");
        }

        Date today = new Date();
        Date revisionDate = revisionCreateDto.getRevisionDate();
        if (revisionDate == null){
            errors.rejectValue("revisionDate","null","Shouldnt be null.");
        }
        if (revisionDate != null && today.before(revisionDate)) {
            errors.rejectValue("revisionDate", "invalidDate", "Revision should be in the past.");
        }

        Long machineId = revisionCreateDto.getMachineId();
        if (machineId == null){
            errors.rejectValue("machineId","null","Shouldnt be null.");
        }
        Long mechanicId = revisionCreateDto.getMechanicId();
        if (mechanicId == null){
            errors.rejectValue("mechanicId","null", "Shouldnt be null.");
        }
    }

}