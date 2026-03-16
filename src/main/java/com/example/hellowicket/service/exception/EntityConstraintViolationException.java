package com.example.hellowicket.service.exception;

public class EntityConstraintViolationException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityConstraintViolationException() {
        super("Entity constraint violation.");
    }
}
