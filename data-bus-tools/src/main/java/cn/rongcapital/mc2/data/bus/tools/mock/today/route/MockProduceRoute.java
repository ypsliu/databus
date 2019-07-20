package cn.rongcapital.mc2.data.bus.tools.mock.today.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockBuyerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockJourneyProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockOrderProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockPathProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockSellerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockTimeProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.today.process.MockDateProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.today.process.MockSaveProcess;

@Component
public class MockProduceRoute extends RouteBuilder {

	@Value("${mock.today.produce.cron}")
	private String cron;

	@Autowired
	private MockDateProcess mockDateProcess;

	@Autowired
	private MockBuyerProcess mockBuyerProcess;

	@Autowired
	private MockJourneyProcess mockJourneyProcess;

	@Autowired
	private MockSellerProcess mockSellerProcess;

	@Autowired
	private MockOrderProcess mockOrderProcess;

	@Autowired
	private MockPathProcess mockPathProcess;

	@Autowired
	private MockTimeProcess mockTimeProcess;

	@Autowired
	private MockSaveProcess mockSaveProcess;

	@Override
	public void configure() throws Exception {

		from("quartz2://produce-foo?cron=" + cron).to("disruptor://mock-today-date");

		from("disruptor://mock-today-date?waitStrategy=Yielding").threads().process(mockDateProcess).to("disruptor://mock-today-buyer");

		from("disruptor://mock-today-buyer?waitStrategy=Yielding").threads().process(mockBuyerProcess).split().body().parallelProcessing().to("disruptor://mock-today-journey");

		from("disruptor://mock-today-journey?waitStrategy=Yielding").threads().process(mockJourneyProcess).split().body().parallelProcessing().to("disruptor://mock-today-seller");

		from("disruptor://mock-today-seller?waitStrategy=Yielding").threads().process(mockSellerProcess).to("disruptor://mock-today-order");

		from("disruptor://mock-today-order?waitStrategy=Yielding").threads().process(mockOrderProcess).to("disruptor://mock-today-path");

		from("disruptor://mock-today-path?waitStrategy=Yielding").threads().process(mockPathProcess).to("disruptor://mock-today-time");

		from("disruptor://mock-today-time?waitStrategy=Yielding").threads().process(mockTimeProcess).to("disruptor://mock-today-save");

		from("disruptor://mock-today-save?waitStrategy=Yielding").threads().process(mockSaveProcess);

	}

}
