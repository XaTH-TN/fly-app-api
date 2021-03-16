package com.edu.fly.exception;

public class NotFoundException extends GenericException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
        this.status = 404;
    }

    public NotFoundException(String resource, String field, Object value) {
        super(String.format("Cannot found %s by [%s=%s]", resource, field, value.toString()));
    }

}
