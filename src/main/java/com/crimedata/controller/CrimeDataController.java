package com.crimedata.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.crimedata.controller.exhandlers.RestTemplateResponseErrorHandler;
import com.crimedata.exceptions.CategoriesBadRequestException;
import com.crimedata.exceptions.CategoriesNotFoundException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.web.bind.annotation.PathVariable;


/**
 * This RestController will handle requests for coming in for /crime/* 
 * @author Clifford
 *
 */
@RequestMapping(value="crime")
@RestController
public class CrimeDataController extends BaseController {

	private static final String CRIME_CAT_URL_KEY = "crimedata.categories.url";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	@Autowired
	HttpHeaders jsonHeader;

	@RequestMapping(method=RequestMethod.GET)
	public void invalidRequests() {
		throw new CategoriesBadRequestException("Not a valid categories request path");
	}
	
	/**
	 * It handles GET requests for the categories request path which in turn uses an external service
	 * to retrieve all crime data categories. It then returns this to the caller of this service as json.
	 * It also handles exceptions thrown by resttemplate using an error handler 
	 * RestTemplateResponseErrorHandler. 
	 * This method will throw a CategoriesNotFoundException if a 404 is returned by the external service.
	 * or a CategoriesBadRequestException for any other.
	 * A successful request will return the requested data in json.
	 * @return
	 */
    @RequestMapping(value="/categories",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCategoriesResponseEntity() {
    	
        HttpEntity <String> entity = new HttpEntity<String>(jsonHeader);
        
        ResponseEntity<String> myResponseEntity = null;
        ResponseEntity<String> serviceResponseEntity = null;

        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        serviceResponseEntity = restTemplate.exchange(env.getProperty(CRIME_CAT_URL_KEY), HttpMethod.GET, entity, String.class);
        
        String body = serviceResponseEntity.getBody();
        
        if(serviceResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) { 
        	//not found 404
        	throw new CategoriesNotFoundException(body);
        }
        else if (serviceResponseEntity.getStatusCode() == HttpStatus.OK) { 
        	//success 200
        	myResponseEntity = ResponseEntity.ok(body);
        }
        else {
        	//bad request 400
        	throw new CategoriesBadRequestException(body);
        }
        
        return myResponseEntity;
    }
}
