package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * RentalUserDto validator.
 * 
 * @author   Josef Plch
 * @since    2016-12-16
 * @version  2016-12-17
 */
public class RentalUserDtoValidator implements Validator {

    @Override
    public boolean supports (Class <?> type) {
        return RentalUserDto.class.isAssignableFrom (type);
    }

    @Override
    public void validate (Object target, Errors errors) {
        RentalUserDto rentalUserDto = (RentalUserDto) target;
        
        String email = rentalUserDto.getEmail ();
        if (email == null || StringUtils.trimAllWhitespace(email).length() == 0) {
            errors.rejectValue ("email", "Email cannot be empty or whitespace.");
        }
        else if (! email.matches ("[a-zA-Z0-9.-_]+@[a-zA-Z0-9.-_]+[.][a-zA-Z]+")) {
            errors.rejectValue ("email", "Inserted email: " + email + " is invalid.");
        }
        
        String username = rentalUserDto.getUsername ();
        if (username == null || StringUtils.trimAllWhitespace(username).length() == 0) {
            errors.rejectValue ("username", "Username cannot be empty or whitespace.");
        }
    }
}