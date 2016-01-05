package ttecca.client_java_library.dao;

import org.joda.time.DateTime;

import ttecca.client_java_library.pojo.StagedMetric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
public class MetricLogger {
	//Singleton metric logger
	private static MetricLoggerInit logger;
	
	//TODO could check this property when running things from cmd line
	//http://stackoverflow.com/questions/1324053/configure-log4j-to-log-to-custom-file-at-runtime
	@AllArgsConstructor
	@Getter
	private static class MetricLoggerInit {
		private final String logDirectory;
		private final String applicationName;
		private final String hostName;
		private final String marketPlace;
	}
	
	private final static ObjectMapper mapper = new ObjectMapper();
	
	public static void build(String logDirectory, String applicationName, String hostName, String marketPlace) {
		if(logger == null) {
			logger = new MetricLoggerInit(logDirectory,applicationName,hostName,marketPlace);
		}
	}

	public static void writeMetric(String operationName, String metricName, double metricValue) {
		if(logger == null) {
			throw new IllegalAccessError("Need to initialize/build MetricLogger before writing metrics");
		}
		long currentTime = DateTime.now().getMillis();
		StagedMetric metric = new StagedMetric(logger.getApplicationName(),
					operationName,logger.getMarketPlace(),logger.getHostName(),
					currentTime,metricName,metricValue);
		try {
			String jsonString = mapper.writeValueAsString(metric);
			log.info(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		}
	}
}
