package ttecca.client_java_library.dao;

import org.joda.time.DateTime;

import ttecca.client_java_library.pojo.StagedMetric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
public class MetricWriter {
	private final String applicationName;
	private final String hostName;
	private final String marketPlace;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public void writeMetric(String operationName, String metricName, double metricValue) {
		StagedMetric metric = new StagedMetric(applicationName,operationName,marketPlace,hostName,DateTime.now().getMillis(),metricName,metricValue);
		try {
			String jsonString = mapper.writeValueAsString(metric);
			System.out.println(jsonString);
			log.info(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		}
	}
}
