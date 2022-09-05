package com.laioffer.travelplanner.exception;

public class StopAlreadyExistException extends RuntimeException{
    public StopAlreadyExistException(String message) {
        super(message);
    }
}
