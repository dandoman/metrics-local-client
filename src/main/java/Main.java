import ttecca.client_java_library.dao.MetricWriter;


public class Main {

	public static void main(String[] args) {
		MetricWriter mw = new MetricWriter("app_test", "host_test", "market_test");
		mw.writeMetric("blowme", "blowmemore", 69);
	}

}
