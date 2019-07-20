package cn.rongcapital.mc2.data.bus.tools.mock.today.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockMessageProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.transform.MockMessageTransform;
import cn.rongcapital.mc2.data.bus.tools.mock.today.process.MockCleanProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.today.process.MockQueryProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.today.process.MockQueryTimeProcess;

@Component
public class MockConsumeRoute extends RouteBuilder {

	@Value("${mock.today.consume.cron}")
	private String cron;

	@Autowired
	private MockQueryTimeProcess mockQueryTimeProcess;

	@Autowired
	private MockQueryProcess mockQueryProcess;

	@Autowired
	private MockCleanProcess mockCleanProcess;

	@Autowired
	private MockMessageProcess mockMessageProcess;

	@Autowired
	private MockMessageTransform mockMessageTransform;

	@Override
	public void configure() throws Exception {

		from("quartz2://consume-foo?cron=" + cron).process(mockQueryTimeProcess).to("disruptor://mock-today-query");

		from("disruptor://mock-today-query?waitStrategy=Yielding").threads().process(mockQueryProcess).split().body().parallelProcessing().to("disruptor://mock-today-transform");

		from("disruptor://mock-today-transform?waitStrategy=Yielding").threads().transform(mockMessageTransform).to("disruptor://mock-today-message");

		from("disruptor://mock-today-message?waitStrategy=Yielding").threads().process(mockMessageProcess).to("disruptor://mock-today-rest");

		from("disruptor://mock-today-rest?waitStrategy=Yielding").threads().to("http4://data-bus-camel").to("disruptor://mock-today-clean");

		from("disruptor://mock-today-clean?waitStrategy=Yielding").threads().process(mockCleanProcess);

	}

}
