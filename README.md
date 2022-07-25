# credit-suisse
Used framework- spring-boot
prerequisities
   java-8
   hsql-hsqldb-2.3.4
   maven
   git
   eclipse/sts
Steps to run :
        1. mvn clean install
        2. go to target folder and run below command
           java -jar -Dserver.port log-parser-0.0.1-SNAPSHOT.jar --file.path=logfile
           NOTE: logfile is txt file which should have log record as per the assignment docs in source code folder.
