package cn.rongcapital.mc2.data.bus.tools.mock.pressure.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.pressure.process.MockTestMessageProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.pressure.process.MockTestSampleProcess;

//@Component
public class MockTestRoute extends RouteBuilder {

	@Value("${mock.pressure.timer.count:0}")
	private Integer count;

	@Value("${mock.pressure.timer.period:1s}")
	private String period;

	@Value("${mock.pressure.timer.delay:1s}")
	private String delay;

	@Autowired
	private MockTestSampleProcess mockTestSampleProcess;

	@Autowired
	private MockTestMessageProcess mockTestMessageProcess;

	@Override
	public void configure() throws Exception {
		StringBuilder uriBuilder = new StringBuilder();
		uriBuilder.append("timer:foo?period=")
				  .append(period)
				  .append("&delay=")
				  .append(delay)
				  .append("&repeatCount=")
				  .append(count);

		from(uriBuilder.toString()).process(mockTestSampleProcess).split().body().parallelProcessing().to("disruptor://pressure-message");

		from("disruptor://pressure-message?multipleConsumers=true&concurrentConsumers=16&waitStrategy=Yielding").threads().process(mockTestMessageProcess).to("disruptor://mock-rest");

		from("disruptor://mock-rest?multipleConsumers=true&concurrentConsumers=16&waitStrategy=Yielding").threads().to("http4://data-bus-camel");

	}

}
