package cz.muni.fi.pa165.machrent.enums;

/**
 * List of all possible user roles in the evidence system.
 * 
 * @author  Josef Plch
 * @since   2016-10-26
 * @version 2017-01-08
 */
public enum RentalUserRole {
    CUSTOMER ("customer"),
    EMPLOYEE ("employee");
    private final String code;
    
    private RentalUserRole (String code) {
        this.code = code;
    }
    
    @Override
    public String toString () {
        return this.code;
    }
}
