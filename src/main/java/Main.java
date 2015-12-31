import java.io.File;

import ttecca.client_java_library.dao.MetricLogger;


public class Main {

	public static void main(String[] args) {
		//TODO this needs to be set here and cannot be set in java code due to permissions
		//TODO try this on linux system, windows might just be mucking it up
		System.setProperty("ttecca.logfile.name", "/tmp/service_log");
		MetricLogger mw = new MetricLogger("/tmp","app_test", "host_test", "market_test");
		mw.writeMetric("op_test", "metric_test", 12);
		/*File d = new File("/tmp");
		for(File f : d.listFiles()) {
			System.out.println(f);
		}*/
	}

}
