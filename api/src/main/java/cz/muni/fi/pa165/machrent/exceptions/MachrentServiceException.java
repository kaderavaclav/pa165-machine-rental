package cz.muni.fi.pa165.machrent.exceptions;

/**
 * Created by zschwarz on 11/20/16.
 */
public class MachrentServiceException extends RuntimeException{

	public MachrentServiceException() {
	}

	public MachrentServiceException(String message) {
		super(message);
	}

	public MachrentServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MachrentServiceException(Throwable cause) {
		super(cause);
	}


}
