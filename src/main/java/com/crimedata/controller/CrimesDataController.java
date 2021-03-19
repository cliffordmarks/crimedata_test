package com.crimedata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.crimedata.controller.exhandlers.RestTemplateResponseErrorHandler;
import com.crimedata.exceptions.CategoriesBadRequestException;
import com.crimedata.exceptions.CategoriesNotFoundException;
import com.crimedata.exceptions.InvalidPostcodeException;
import com.crimedata.exceptions.PostcodeBadRequestException;

import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping(value="crimes")
@RestController
public class CrimesDataController extends BaseController {

	private static final String CRIME_POSTCCODE_URL_KEY = "crimedata.postcode.url";
	private static final String CRIME_POSTCCODE_URL_DATE_KEY = "crimedata.postcode.url.datekey";
	private static final String CRIME_POSTCCODE_URL_USEDATE_KEY = "crimedata.postcode.url.usedate";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Autowired
	HttpHeaders jsonHeader;

	
	@RequestMapping(method=RequestMethod.GET)
	public void invalidRequests() {
		throw new PostcodeBadRequestException("Not a valid postcode request path");
	}

	/**
	 * It handles GET requests for the /postcode request that matches the RequestMapping.
	 * This method will return the, externally retrieved crime data by postcode.
	 * It also handles exceptions thrown by resttemplate using an error handler 
	 * RestTemplateResponseErrorHandler. 
	 * This method will throw a InvalidPostcodeException if a 404 is returned by the external service.
	 * or a PostcodeBadRequestException for any other.
	 * A successful request will return the requested data in json.
	 * @return
	 */
    @RequestMapping(value="/postcode={postcode}&date={yyyy-mm}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCrimesByPostcodeAndDate( @PathVariable("postcode") String postcode,
                         @PathVariable("yyyy-mm") String dateFormatYYYY_MM) {

        HttpEntity <String> entity = new HttpEntity<String>(jsonHeader);
        
        //Build the postcode API service request url
        String postcodeAPIURL = buildPostcodeURL(postcode,  dateFormatYYYY_MM);
        
        ResponseEntity<String> myResponseEntity = null;
        ResponseEntity<String> serviceResponseEntity = null;
        
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        serviceResponseEntity = restTemplate.exchange(postcodeAPIURL, HttpMethod.GET, entity, String.class);

        String body = serviceResponseEntity.getBody();

        if(serviceResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) { 
        	//not found 404
        	throw new InvalidPostcodeException(body);
        }
        else if (serviceResponseEntity.getStatusCode() == HttpStatus.OK) { 
        	//success 200
        	myResponseEntity = ResponseEntity.ok(body);
        }
        else {
        	//bad request 400
        	throw new PostcodeBadRequestException(body);
        }
        
        return myResponseEntity;
    }

    /**
     * This method builds the postcode API service request url. 
     * It uses an injected Environment object to retrieve properties from
     * the application.properties file to help it build the request url and
     * then returns this as a string.
     * 
     * @param postcode
     * @param dateFormatYYYY_MM
     * @return
     */
    private String buildPostcodeURL(String postcode, String dateFormatYYYY_MM) {
    	
        StringBuilder strBuilder = new StringBuilder();
        //https://postcodes.io/postcodes/b742er
        strBuilder.append(env.getProperty(CRIME_POSTCCODE_URL_KEY));
        
        if(strBuilder.lastIndexOf("/") != strBuilder.length() - 1)
        	strBuilder.append("/");
        strBuilder.append(postcode);
        
        if(env.getProperty(CRIME_POSTCCODE_URL_USEDATE_KEY) != null 
        		&& env.getProperty(CRIME_POSTCCODE_URL_USEDATE_KEY).equals("true")) {
        	
            strBuilder.append("&");
            strBuilder.append(env.getProperty(CRIME_POSTCCODE_URL_DATE_KEY));
            strBuilder.append("=");
            strBuilder.append(dateFormatYYYY_MM);
        }

        return strBuilder.toString();
    }
}
