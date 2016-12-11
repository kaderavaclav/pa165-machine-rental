package cz.muni.fi.pa165.machrent.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vaclav.kadera on 11-Dec-16.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found")
public class NotFoundException extends RuntimeException {
}
