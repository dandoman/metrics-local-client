package ttecca.client_java_library.dao;

import org.joda.time.DateTime;

import ttecca.client_java_library.pojo.StagedMetric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
public class MetricLogger {
	//TODO could check this property when running things from cmd line
	//http://stackoverflow.com/questions/1324053/configure-log4j-to-log-to-custom-file-at-runtime
	private final String logDirectory;
	private final String applicationName;
	private final String hostName;
	private final String marketPlace;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public MetricLogger(String logDirectory,String applicationName,String hostName,String marketPlace) {
		this.logDirectory = logDirectory;
		this.applicationName = applicationName;
		this.hostName = hostName;
		this.marketPlace = marketPlace;
		//TODO currently does not work on windows
		//System.setProperty("ttecca.logfile.name", "/tmp/service_log");
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
