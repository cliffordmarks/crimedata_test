package com.crimedata.exceptions;


public class CategoriesBadRequestException extends RuntimeException {

	
    public CategoriesBadRequestException() {
        super();
    }

    public CategoriesBadRequestException(String message, final Throwable cause) {
        super(message, cause);
    }

    public CategoriesBadRequestException(String message) {
        super(message);
    }

    public CategoriesBadRequestException(final Throwable cause) {
        super(cause);
    }
    
}