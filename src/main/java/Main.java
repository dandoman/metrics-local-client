import java.io.File;

import ttecca.client_java_library.dao.MetricLogger;


public class Main {

	public static void main(String[] args) {
		MetricLogger mw = new MetricLogger("app_test", "host_test", "market_test");
		mw.writeMetric("blowme", "blowmemore", 69);
		File d = new File("/tmp");
		for(File f : d.listFiles()) {
			System.out.println(f);
		}
	}

}
