package com.crimedata.ms.services.reg_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistrationServer {

	/**
	 * Start our discovery server
	 * @param args
	 */
    public static void main(String[] args) {
        
    	System.setProperty("spring.config.name", "registration-server");
                
        SpringApplication.run(RegistrationServer.class, args);
    }

}
