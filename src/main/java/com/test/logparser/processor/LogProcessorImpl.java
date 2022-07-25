/**
 * 
 */
package com.test.logparser.processor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.logparser.dao.LogEventsEntity;
import com.test.logparser.dao.LogRepository;
import com.test.logparser.model.LogModel;

import ch.qos.logback.classic.Logger;

/**
 * @author pakumar
 *
 */
@Service
public class LogProcessorImpl implements ILogProcessor{
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LogProcessorImpl.class);

	@Autowired
	private static ObjectMapper objectMapper;
	
	@Autowired
	private   LogRepository repository;

	private Map<String,List<LogModel>> events;
	@Override
	public void process(String filePath) {
		
		try {
			
			 
			 Files.lines(Paths.get(filePath)) 
			.map(s -> s.trim()) 
			.filter(s -> !s.isEmpty())
			.map(this::collectEventPairs)
			.forEach(System.out::println);
			

		    processLogEvents();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
	}

	private void processLogEvents() {
		List<LogEventsEntity> loginfos= new ArrayList<LogEventsEntity>();

		Set<String> keys= events.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			logger.debug("************ record for the {} ",key);
			events.get(key).stream().forEach(System.out::println);
			
			List<LogModel> logmodels= events.get(key);
			if (logmodels.size() > 1) {
				long startTime = Long.valueOf(logmodels.get(0).getTimestamp());
				long endtime = Long.valueOf(logmodels.get(1).getTimestamp());
				LogEventsEntity info = new LogEventsEntity();
				info.setId(key);

				int timediff = (int) (endtime - startTime);
				info.setEventDuration(timediff);
				if (timediff > 4) {
					info.setAlert(String.valueOf(true));

				} else {
					info.setAlert(String.valueOf(false));

				}
				logger.info("Logevnts processed for {} with starttime {} | endtime {} | time-duration {}", key,
						startTime, endtime, timediff);

				info = repository.save(info);
				loginfos.add(info);

			}else {
				logger.info("Logevnts {} does not have pairs to calculate the timeduration",key);
			}
			
		}
		
		loginfos.forEach(System.out::println);
	}

	public Map<String, List<LogModel>> collectEventPairs(String line) {
		LogModel logModel = null;
		try {
			if(objectMapper==null) {
				objectMapper = new ObjectMapper();
			}
			logModel = objectMapper.readValue(line , LogModel.class);
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		if(events==null) {
             events= new HashMap<String, List<LogModel>>();
		}
        
       if(events.containsKey(logModel.getId())) {
    	   events.get(logModel.getId()).add(logModel);
    	   logger.debug("existing element for {}",logModel.getId());
       }else {
    	   List<LogModel> logs = new ArrayList<LogModel>();
    	   logs.add(logModel);
    	   events.put(logModel.getId(), logs);
    	   logger.debug("fresh element for {}",logModel.getId());

       }
       logger.debug("-------------------------------------completed collect event pairs-----------------------------------------------");
       return events;
	}
	
	

}
