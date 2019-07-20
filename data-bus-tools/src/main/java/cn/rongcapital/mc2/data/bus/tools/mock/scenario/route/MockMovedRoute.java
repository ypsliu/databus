package cn.rongcapital.mc2.data.bus.tools.mock.scenario.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockBuyerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockNotTimeoutProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockRestParamProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockMessageProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockOrderProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockPathProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockSellerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.transform.MockMessageTransform;
import cn.rongcapital.mc2.data.bus.tools.mock.scenario.process.MockJourneyMovedProcess;
import cn.rongcapital.mc2.data.common.camel.NettyRestRouteBuilder;

@Component
public class MockMovedRoute extends NettyRestRouteBuilder {

	@Value("${mock.scenario.moved.rest.uri}")
	private String uri;

	@Autowired
	private MockRestParamProcess mockRestParamProcess;

	@Autowired
	private MockBuyerProcess mockBuyerProcess;

	@Autowired
	private MockJourneyMovedProcess mockJourneyMovedProcess;

	@Autowired
	private MockSellerProcess mockSellerProcess;

	@Autowired
	private MockOrderProcess mockOrderProcess;

	@Autowired
	private MockPathProcess mockPathProcess;

	@Autowired
	private MockNotTimeoutProcess mockNotTimeoutProcess;

	@Autowired
	private MockMessageProcess mockMessageProcess;

	@Autowired
	private MockMessageTransform mockMessageTransform;

	@Override
	public void configure() throws Exception {

		rest(uri).post().produces("application/json").consumes("application/json").route().threads().to("disruptor://mock-moved-param");

		from("disruptor://mock-moved-param").process(mockRestParamProcess).split().body().parallelProcessing().to("disruptor://mock-moved-buyer");

		from("disruptor://mock-moved-buyer?waitStrategy=Yielding").threads().process(mockBuyerProcess).split().body().parallelProcessing().to("disruptor://mock-moved-journey");

		from("disruptor://mock-moved-journey?waitStrategy=Yielding").threads().process(mockJourneyMovedProcess).split().body().parallelProcessing().to("disruptor://mock-moved-seller");

		from("disruptor://mock-moved-seller?waitStrategy=Yielding").threads().process(mockSellerProcess).to("disruptor://mock-moved-order");

		from("disruptor://mock-moved-order?waitStrategy=Yielding").threads().process(mockOrderProcess).to("disruptor://mock-moved-path");

		from("disruptor://mock-moved-path?waitStrategy=Yielding").threads().process(mockPathProcess).to("disruptor://mock-moved-time");

		from("disruptor://mock-moved-time?waitStrategy=Yielding").threads().process(mockNotTimeoutProcess).split().body().parallelProcessing().to("disruptor://mock-moved-transform");

		from("disruptor://mock-moved-transform?waitStrategy=Yielding").threads().transform(mockMessageTransform).to("disruptor://mock-moved-message");

		from("disruptor://mock-moved-message?waitStrategy=Yielding").threads().process(mockMessageProcess).to("disruptor://mock-moved-rest");

		from("disruptor://mock-moved-rest?waitStrategy=Yielding").threads().to("http4://data-bus-camel");

	}

}
