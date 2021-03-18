package com.crimedata.exceptions;

public class PostcodeBadRequestException  extends RuntimeException {

	
    public PostcodeBadRequestException() {
        super();
    }

    public PostcodeBadRequestException(String message, final Throwable cause) {
        super(message, cause);
    }

    public PostcodeBadRequestException(String message) {
        super(message);
    }

    public PostcodeBadRequestException(final Throwable cause) {
        super(cause);
    }
    
}
