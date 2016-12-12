package cz.muni.fi.pa165.machrent.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vaclav.kadera on 11-Dec-16.
 */

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists")
public class AlreadyExistsException extends RuntimeException {
}
