package com.crimedata.controller.exhandlers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;


/**
 * This cals will handle the unexpected rest exceptions and either ignore them or throw a
 * meaningful application specific exception.
 * @author Clifford
 *
 */
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
    	Series SERVER_ERROR = HttpStatus.Series.SERVER_ERROR;
    	Series CLIENT_ERROR = HttpStatus.Series.CLIENT_ERROR;

    	//Determine whether this is a server or client error 
    	boolean bRtrn = clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
    			|| clientHttpResponse.getStatusCode().series() == SERVER_ERROR;
    	
        return bRtrn;
   }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

    	//Do nothing let my application handle the error.
    	
    }
}