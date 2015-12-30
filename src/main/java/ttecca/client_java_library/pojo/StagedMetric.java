package ttecca.client_java_library.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StagedMetric {
	private String applicationName;
	private String operation;
	private String marketplace;
	private String hostName;
	private long timeStamp;
	private String metricName;
	private double value;
}
