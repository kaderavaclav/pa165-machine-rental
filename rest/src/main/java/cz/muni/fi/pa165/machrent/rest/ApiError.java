package cz.muni.fi.pa165.machrent.rest;

import java.util.List;

/**
 * Created by vaclav.kadera on 11-Dec-16.
 */
public class ApiError {

    private List<String> errors;

    public ApiError() {
    }

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
