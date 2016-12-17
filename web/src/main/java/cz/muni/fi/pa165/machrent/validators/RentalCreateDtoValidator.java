/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.validators;

import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.facade.RentalFacade;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Peter Benus
 */
public class RentalCreateDtoValidator implements Validator{

    private RentalFacade rentalFacade;
    
    public void setRentalFacade(RentalFacade rentalFacade) {
        this.rentalFacade = rentalFacade;
    }

    @Override
    public boolean supports(Class<?> type) {
        return RentalCreateDto.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RentalCreateDto rentalCreateDto = (RentalCreateDto) o;
        
        Long machineId = rentalCreateDto.getMachineId();
        Long customerId = rentalCreateDto.getCustomerId();
        Long employeeId = rentalCreateDto.getEmployeeId();
                
        if (machineId == null){
            errors.rejectValue("machineId", "notNull", "MachineId cannot be empty!");
        }
        if (customerId == null){
            errors.rejectValue("customerId", "notNull", "CustomerId cannot be empty!");
        }
        if (employeeId == null){
            errors.rejectValue("employeeId", "notNull", "EmployeeId cannot be empty!");
        }
    }
    
}
