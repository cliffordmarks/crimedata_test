package com.crimedata.exceptions;

public class InvalidPostcodeException extends RuntimeException {

    public InvalidPostcodeException() {
        super();
    }

    public InvalidPostcodeException(String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidPostcodeException(String message) {
        super(message);
    }

    public InvalidPostcodeException(final Throwable cause) {
        super(cause);
    }
}