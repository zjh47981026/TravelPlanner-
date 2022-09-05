package com.laioffer.travelplanner.exception;

public class StopNotExistException extends RuntimeException{
    public StopNotExistException(String message) {
        super(message);
    }
}
