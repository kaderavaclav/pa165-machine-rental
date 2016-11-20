package cz.muni.fi.pa165.machrent.exceptions;

/**
 * Created by zschwarz on 11/20/16.
 */
public class SomethingWrongException  extends RuntimeException{

	public SomethingWrongException() {
	}

	public SomethingWrongException(String message) {
		super(message);
	}

	public SomethingWrongException(String message, Throwable cause) {
		super(message, cause);
	}

	public SomethingWrongException(Throwable cause) {
		super(cause);
	}


}
