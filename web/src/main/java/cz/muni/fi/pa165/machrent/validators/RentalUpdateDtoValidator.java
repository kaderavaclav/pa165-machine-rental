/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.RentalUpdateDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.facade.RentalFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Peter Benus
 */
public class RentalUpdateDtoValidator implements Validator{

    private RentalFacade rentalFacade;
    
    public void setRentalFacade(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @Override
    public boolean supports(Class<?> type) {
        return RentalUpdateDtoValidator.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RentalUpdateDto rentalUpdateDto = (RentalUpdateDto) o;
        
        RentalUserDto customer = rentalUpdateDto.getCustomer();
        
        if (customer == null){
            errors.rejectValue("customer", "notNull", "Customer cannot be empty!");
        }
    }
    
}
