# credit-suisse


### Getting Started

Prerequisites:
               Java JDK (not JRE) 8 must be installed and in the path and JAVA_HOME environment variables
               hsql-hsqldb-2.3.4
               maven
               git
               eclipse/tst
               Used framework- spring-boot
	git clone (https://github.com/pkumar-2/credit-suisse.git)
   switch to project root folder
	run this command =  mvn clean install

### How to run application with required properties

	
	 ## go to target folder and run below command byb passing log file.path which will get process with logic 
           java -jar -Dserver.port log-parser-0.0.1-SNAPSHOT.jar --file.path=logfile
           NOTE: logfile is txt file which should have log record as per the assignment docs in source code folder.
  
	
