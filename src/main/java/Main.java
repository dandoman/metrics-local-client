import java.io.File;

import ttecca.client_java_library.dao.MetricLogger;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		//TODO This should be set through cmd line with daemon
		System.setProperty("ttecca.logfile.name", "/tmp/service/service_log");
		
		//Need to call this before using to instantiate
		MetricLogger.build("/tmp/service","app_test", "host_test", "market_test");
		
		long startTime = System.currentTimeMillis();
		while((startTime - System.currentTimeMillis())<310*1000) {
			double random = Math.random() * 1000;
			MetricLogger.writeMetric("op_test", "day", random);
			Thread.sleep(10000);
		}
		File d = new File("/tmp");
		for(File f : d.listFiles()) {
			System.out.println(f);
		}
	}

}
