package com.test.logparser;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.logparser.processor.ILogProcessor;

import ch.qos.logback.classic.Logger;

/**
 * 
 * @author pakumar
 *
 */
@SpringBootApplication
public class LogParserApplication implements ApplicationRunner{
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LogParserApplication.class);

	@Autowired
	private ILogProcessor processor;
	
	public static void main(String[] args) {
		SpringApplication.run(LogParserApplication.class, args);
	}

	/**
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	    String strArgs = Arrays.stream(args.getSourceArgs()).collect(Collectors.joining("|"));
	    logger.info("Application started with arguments:" + strArgs);

        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
        logger.info("OptionNames: {}", args.getOptionNames());

        for (String name : args.getOptionNames()){
            logger.info("arg-" + name + "=" + args.getOptionValues(name));
        }

        boolean containsOption = args.containsOption("file.path");
        logger.info("Contains file.path: " + containsOption);
        if(containsOption)
	    	processor.process(args.getOptionValues("file.path").get(0));
        else
        	 logger.info("file.path does not exist to process logs!");
	}

}
