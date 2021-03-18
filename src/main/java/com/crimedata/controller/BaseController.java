package com.crimedata.controller;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

abstract public class BaseController {

	/**
	 * Convenience method to return a HttpsHeaders object with a JSON Media type set.
	 * 
	 * @return
	 */
	/*protected HttpHeaders getJSONHeader() {

		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        return headers;
		
	}*/

}
