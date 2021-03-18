package com.crimedata.exceptions;

import org.springframework.http.HttpStatus;

public class CategoriesNotFoundException extends RuntimeException {

	
	
    public CategoriesNotFoundException() {
        super();
    }

    public CategoriesNotFoundException(String message, final Throwable cause) {
        super(message, cause);
    }

    public CategoriesNotFoundException(String message) {
        super(message);
    }

    public CategoriesNotFoundException(final Throwable cause) {
        super(cause);
    }
    
}