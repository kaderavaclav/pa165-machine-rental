package cz.muni.fi.pa165.machrent.exceptions;

/**
 * @author  Josef Plch
 * @since   2016-11-23
 * @version 2016-11-23
 */
public class RentalUserServiceException extends MachrentServiceException {
    public RentalUserServiceException () {
    }

    public RentalUserServiceException (String message) {
        super (message);
    }

    public RentalUserServiceException (Throwable cause) {
        super (cause);
    }

    public RentalUserServiceException (String message, Throwable cause) {
        super (message, cause);
    }
}
