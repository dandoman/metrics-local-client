package ttecca.client_java_library.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.joda.time.DateTime;

import ttecca.client_java_library.pojo.StagedMetric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;

@Setter
public class MetricLogger {
	private String applicationName;
	private String hostName;
	private String marketPlace;
	private final Logger logger = Logger.getLogger(this.getClass());

	private final ObjectMapper mapper = new ObjectMapper();

	public MetricLogger(String applicationName, String marketPlace, String logFilePath) {
		this.applicationName = applicationName;
		this.marketPlace = marketPlace;
		try {
			this.hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			this.hostName = "UNKNOWN";
		}
		createAppender(logFilePath);
	}

	public void writeMetric(String operationName, String metricName,
			double metricValue) {

		long currentTime = DateTime.now().getMillis();
		StagedMetric metric = new StagedMetric(applicationName, operationName,
				marketPlace, hostName, currentTime, metricName, metricValue);
		try {
			String jsonString = mapper.writeValueAsString(metric);
			logger.info(jsonString);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		}
	}
	
	private void createAppender(String logFilePath) {
		DailyRollingFileAppender appender = new DailyRollingFileAppender();
	    appender.setName("metricsAppender");
	    appender.setDatePattern("'.'yyyy-MM-dd-HH-mm");
	    appender.setLayout(new PatternLayout("%m%n"));
	    appender.setFile(logFilePath + "/service_log");
	    appender.setAppend(true);
	    appender.setThreshold(Level.INFO);
	    appender.activateOptions();
	    logger.addAppender(appender);
	    logger.setAdditivity(false);
	}

}
