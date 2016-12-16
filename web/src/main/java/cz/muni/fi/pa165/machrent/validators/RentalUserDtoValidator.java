package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * RentalUserDto validator.
 * 
 * @author   Josef Plch
 * @since    2016-12-16
 * @version  2016-12-16
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
        if (email == null || email.isEmpty ()) {
            errors.rejectValue ("email", "empty email");
        }
        else if (! email.matches ("[a-zA-Z0-9.-_]+@[a-zA-Z0-9.-_]+[.][a-zA-Z]+")) {
            errors.rejectValue ("email", email, "invalid email");
        }
        
        String username = rentalUserDto.getUsername ();
        if (username == null || username.isEmpty ()) {
            errors.rejectValue ("username", "empty username");
        }
    }
}