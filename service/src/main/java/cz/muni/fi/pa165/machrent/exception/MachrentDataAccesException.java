package cz.muni.fi.pa165.machrent.exception;

import org.springframework.dao.DataAccessException;

/**
 * Created by zuz-schwarzova on 23. 11. 2016.
 */
public class MachrentDataAccesException extends DataAccessException{

    public MachrentDataAccesException(String msg) {
        super(msg);
    }

    public MachrentDataAccesException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
