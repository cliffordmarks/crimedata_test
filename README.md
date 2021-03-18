# crimedata_test
test for capgem
README:
1) use git to clone the crimedata repo from here
2) to build ( using maven) run the following command in the directory of the project root e.g:
	%ROOT_FOLDER%\crimedata_test\mvn clean install 
3) To run the application:
3.1) In command window run this command: java -jar target/crimedata-service-0.0.1-SNAPSHOT.jar regserver
3.2) In another command window run this command: java -jar target/crimedata-service-0.0.1-SNAPSHOT.jar crimedata


4) access the crimedat-service for categories using (example): http://localhost:8080/crime/categories
5) access the crimedat-service for postcodes using (example): http://localhost:8080/crimes/postcode=TF3 3AY&date=201802

