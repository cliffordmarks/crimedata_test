package com.crimedata.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


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

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}	
}
