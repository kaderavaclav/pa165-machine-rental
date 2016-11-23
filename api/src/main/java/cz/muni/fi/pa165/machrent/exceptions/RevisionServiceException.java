package cz.muni.fi.pa165.machrent.exceptions;

/**
 * Created by zuz-schwarzova on 23. 11. 2016.
 */
public class RevisionServiceException extends MachrentServiceException {

    public RevisionServiceException() {
    }

    public RevisionServiceException(String message) {
        super(message);
    }

    public RevisionServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RevisionServiceException(Throwable cause) {
        super(cause);
    }

}
