package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.RevisionCreateDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Falka on 16. 12. 2016.
 */
public class RevisionCreateDtoValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return RevisionCreateDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RevisionCreateDto revisionCreateDto = (RevisionCreateDto) target;

        Date revisionDate = revisionCreateDto.getRevisionDate();
        Date today =  new Date();

        if (revisionDate != null && today != null && revisionDate.before(today)) {
            errors.rejectValue("today", "notbefore.today", "revisionDate cannot be before today.");
        }

    }
}