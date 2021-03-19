package com.crimedata.controller.exhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.crimedata.exceptions.CategoriesBadRequestException;
import com.crimedata.exceptions.CategoriesNotFoundException;
import com.crimedata.exceptions.InvalidPostcodeException;
import com.crimedata.exceptions.PostcodeBadRequestException;

/**
 * Exception handler class for app defined exceptions..
 * @author Clifford
 *
 */
@ControllerAdvice
public class MSExceptionHandler  {

	@Autowired
	Environment env;
	
	@Autowired
	HttpHeaders jsonHeader;

	/**
	 * Handles the InvalidPostcodeException class when thrown by the RestController.
	 * This method retrieves properties, from the associated properties file, of this application
	 * via the Environment object and uses this to retrieve an error response to populate 
	 * the MSErrorResponseBean. 
	 * It then returns a MSErrorResponseBean bean/pojo, containing the response, encapsulated
	 * within an ResponseEntity. The ResponseEntity will handle returning the object in a 
	 * specified format in this case JSON.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(InvalidPostcodeException.class)
	public ResponseEntity<MSErrorResponseBean> handleMSException(InvalidPostcodeException ex) {

		String type = env.getProperty("ms.error.postcode.error.invalid.uri");
		String title = env.getProperty("ms.error.postcode.error.invalid.title");
		String httpStatusCode = env.getProperty("ms.error.postcode.error.invalid.statuscode");
		String messageDetail = env.getProperty("ms.error.postcode.error.invalid.message");
		String errorInstance = env.getProperty("ms.error.postcode.error.invalid.errorcodeuri");
		
		MSErrorResponseBean response = new MSErrorResponseBean(type, title, httpStatusCode, ex.getMessage(), errorInstance);
		
		return new ResponseEntity<>(response, jsonHeader, HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * Handles the CategoriesNotFoundException class when thrown by the RestController.
	 * This method retrieves properties, from the associated properties file, of this application
	 * via the Environment object and uses this to retrieve an error response to populate 
	 * the MSErrorResponseBean. 
	 * It then returns a MSErrorResponseBean bean/pojo, containing the response, encapsulated
	 * within an ResponseEntity. The ResponseEntity will handle returning the object in a 
	 * specified format in this case JSON.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CategoriesNotFoundException.class)
	public ResponseEntity<MSErrorResponseBean> handleMSException(CategoriesNotFoundException ex) {
		
		String type = env.getProperty("ms.error.categories.error.notfound.uri");
		String title = env.getProperty("ms.error.categories.error.notfound.title");
		String httpStatusCode = env.getProperty("ms.error.categories.error.notfound.statuscode");
		String messageDetail = env.getProperty("ms.error.categories.error.notfound.message");
		String errorInstance = env.getProperty("ms.error.categories.error.notfound.errorcodeuri");
		
		MSErrorResponseBean response = new MSErrorResponseBean(type, title, httpStatusCode, ex.getMessage(), errorInstance);
		
		return new ResponseEntity<>(response, jsonHeader, HttpStatus.NOT_FOUND);
	}	

	/**
	 * Handles the CategoriesBadRequestException class when thrown by the RestController.
	 * This method retrieves properties, from the associated properties file, of this application
	 * via the Environment object and uses this to retrieve an error response to populate 
	 * the MSErrorResponseBean. 
	 * It then returns a MSErrorResponseBean bean/pojo, containing the response, encapsulated
	 * within an ResponseEntity. The ResponseEntity will handle returning the object in a 
	 * specified format in this case JSON.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CategoriesBadRequestException.class)
	public ResponseEntity<MSErrorResponseBean> handleMSException(CategoriesBadRequestException ex) {
		
		String type = env.getProperty("ms.error.categories.error.badreq.uri");
		String title = env.getProperty("ms.error.categories.error.badreq.title");
		String httpStatusCode = env.getProperty("ms.error.categories.error.badreq.statuscode");
		String messageDetail = env.getProperty("ms.error.categories.error.badreq.message");
		String errorInstance = env.getProperty("ms.error.categories.error.badreq.errorcodeuri");
		
		MSErrorResponseBean response = new MSErrorResponseBean(type, title, httpStatusCode, ex.getMessage(), errorInstance);
		
		return new ResponseEntity<>(response, jsonHeader, HttpStatus.BAD_REQUEST);
	}	
	
	/**
	 * Handles the PostcodeBadRequestException class when thrown by the RestController.
	 * This method retrieves properties, from the associated properties file, of this application
	 * via the Environment object and uses this to retrieve an error response to populate 
	 * the MSErrorResponseBean. 
	 * It then returns a MSErrorResponseBean bean/pojo, containing the response, encapsulated
	 * within an ResponseEntity. The ResponseEntity will handle returning the object in a 
	 * specified format in this case JSON.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(PostcodeBadRequestException.class)
	public ResponseEntity<MSErrorResponseBean> handleMSException(PostcodeBadRequestException ex) {

		String type = env.getProperty("ms.error.postcode.error.badreq.uri");
		String title = env.getProperty("ms.error.postcode.error.badreq.title");
		String httpStatusCode = env.getProperty("ms.error.postcode.error.badreq.statuscode");
		String messageDetail = env.getProperty("ms.error.postcode.error.badreq.message");
		String errorInstance = env.getProperty("ms.error.postcode.error.badreq.errorcodeuri");
		
		MSErrorResponseBean response = new MSErrorResponseBean(type, title, httpStatusCode, ex.getMessage(), errorInstance);
		
		return new ResponseEntity<>(response, jsonHeader, HttpStatus.BAD_REQUEST);
	}
	
}