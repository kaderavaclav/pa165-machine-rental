package cz.muni.fi.pa165.machrent.exceptions;

/**
 * Created by vaclav.kadera on 22-Nov-16.
 */
public class MachineServiceException extends MachrentServiceException {

    public MachineServiceException() {
    }

    public MachineServiceException(String message) {
        super(message);
    }

    public MachineServiceException(Throwable cause) {
        super(cause);
    }

    public MachineServiceException(String message, Throwable cause) {
        super(message, cause);
    }


}
