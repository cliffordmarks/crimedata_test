package com.crimedata.ms.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.crimedata.config.CrimeDataConfig;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableAutoConfiguration
@EnableEurekaClient 
@Import(CrimeDataConfig.class)
public class CrimeDataServer {

	/**
	 * Start our client service server
	 * @param args
	 */
    public static void main(String[] args) {
        //Using crimedata-server.yml for service configuration
        System.setProperty("spring.config.name", "crimedata-server");

        SpringApplication.run(CrimeDataServer.class, args);
    }
}