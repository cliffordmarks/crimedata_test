package com.crimedata.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.crimedata.controller.exhandlers.MSErrorResponseBean;

@PropertySource("classpath:/app.properties")
@ComponentScan("com.crimedata")
@Configuration
public class CrimeDataConfig {

	@Bean
	protected HttpHeaders getJSONHeader() {

		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       
        return headers;
	}

/*	@Bean
	public RestTemplate getRestTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> converters =  restTemplate.getMessageConverters() ;
		for(HttpMessageConverter<?> converter : converters) {
			
			System.out.println("converter: " + converter.toString());
			System.out.println("Has the following media types: " );

			List<MediaType> listOfMediaTypes = converter.getSupportedMediaTypes();
			
			for(MediaType mediaType : listOfMediaTypes) {

				System.out.println("MediaType: " + mediaType.toString());
				
				if(mediaType.equals(MediaType.APPLICATION_JSON)) {
					System.out.println("***Match on Media type!!: " + converter.toString());
					converter.canWrite(MSErrorResponseBean.LimitedErrorView.class, MediaType.APPLICATION_JSON);
				}
			}
			System.out.println();
		}
		
		return  restTemplate;
	}*/
	
	@Bean
	public RestTemplate getRestTemplate() {
		
		return  new RestTemplate();
	}
	
}
