package ttecca.client_java_library.dao;

import org.joda.time.DateTime;

import ttecca.client_java_library.pojo.StagedMetric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
public class MetricLogger {
	private final String applicationName;
	private final String hostName;
	private final String marketPlace;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	//This could also be specified by the constructor
	//If we want a user specified log output
	static {
		//This line is required in order to set the service_log output folder
		//TODO Should add tmp folder checking here
		System.setProperty("logfile.name", "/tmp/service_log");
	}
	
	public void writeMetric(String operationName, String metricName, double metricValue) {
		long currentTime = DateTime.now().getMillis();
		StagedMetric metric = new StagedMetric(applicationName,operationName,marketPlace,hostName,currentTime,metricName,metricValue);
		try {
			String jsonString = mapper.writeValueAsString(metric);
			log.info(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		}
	}
}
