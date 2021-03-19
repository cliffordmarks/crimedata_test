package com.crimedata;

import com.crimedata.ms.services.CrimeDataServer;
import com.crimedata.ms.services.reg_server.RegistrationServer;

public class MainClass {

	/**
	 * Convenience class to automate starting up of the chosen discovery server and 
	 * client service server 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		if(args != null && args.length > 0) {
			
			String serviceToRun = args[0];
		
			if (serviceToRun.equals("regserver")) {
				RegistrationServer.main(args);
			} 
			else if (serviceToRun.equals("crimedata")) {
				CrimeDataServer.main(args);
			} 
			else {
			    System.out.println("Service provided not recognised: " + serviceToRun);
			    System.out.println("Usage: ");
			    System.out.println("Run the following in seperate command windows in order");
			    System.out.println("java -jar nameOfJarFile.jar regserver");
			    System.out.println("java -jar nameOfJarFile.jar crimedata");
			    
			}		
		}
	}
	
}
