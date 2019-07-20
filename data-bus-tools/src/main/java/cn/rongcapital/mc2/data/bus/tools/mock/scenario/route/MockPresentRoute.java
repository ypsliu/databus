package cn.rongcapital.mc2.data.bus.tools.mock.scenario.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockBuyerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockMessageProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockOrderProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockReversePathProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockReverseTimeNotTimeoutProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockSellerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.transform.MockMessageTransform;
import cn.rongcapital.mc2.data.bus.tools.mock.scenario.process.MockJourneyPresentProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.scenario.process.MockJourneyPresentParamProcess;
import cn.rongcapital.mc2.data.common.camel.NettyRestRouteBuilder;

@Component
public class MockPresentRoute extends NettyRestRouteBuilder {

	@Value("${mock.scenario.present.rest.uri}")
	private String uri;

	@Autowired
	private MockJourneyPresentParamProcess journeyPresentParamProcess;

	@Autowired
	private MockBuyerProcess mockBuyerProcess;

	@Autowired
	private MockJourneyPresentProcess mockJourneyPresentProcess;

	@Autowired
	private MockSellerProcess mockSellerProcess;

	@Autowired
	private MockOrderProcess mockOrderProcess;

	@Autowired
	private MockReversePathProcess mockReversePathProcess;

	@Autowired
	private MockReverseTimeNotTimeoutProcess mockReverseTimeNotTimeoutProcess;

	@Autowired
	private MockMessageProcess mockMessageProcess;

	@Autowired
	private MockMessageTransform mockMessageTransform;

	@Override
	public void configure() throws Exception {

		rest(uri).post().produces("application/json").consumes("application/json").route().threads().to("disruptor://mock-present-param");

		from("disruptor://mock-present-param").process(journeyPresentParamProcess).to("disruptor://mock-present-buyer");

		from("disruptor://mock-present-buyer?waitStrategy=Yielding").threads().process(mockBuyerProcess).split().body().parallelProcessing().to("disruptor://mock-present-journey");

		from("disruptor://mock-present-journey?waitStrategy=Yielding").threads().process(mockJourneyPresentProcess).split().body().parallelProcessing().to("disruptor://mock-present-seller");

		from("disruptor://mock-present-seller?waitStrategy=Yielding").threads().process(mockSellerProcess).to("disruptor://mock-present-order");

		from("disruptor://mock-present-order?waitStrategy=Yielding").threads().process(mockOrderProcess).to("disruptor://mock-present-path");

		from("disruptor://mock-present-path?waitStrategy=Yielding").threads().process(mockReversePathProcess).to("disruptor://mock-present-time");

		from("disruptor://mock-present-time?waitStrategy=Yielding").threads().process(mockReverseTimeNotTimeoutProcess).split().body().parallelProcessing().to("disruptor://mock-present-transform");

		from("disruptor://mock-present-transform?waitStrategy=Yielding").threads().transform(mockMessageTransform).to("disruptor://mock-present-message");

		from("disruptor://mock-present-message?waitStrategy=Yielding").threads().process(mockMessageProcess).to("disruptor://mock-present-rest");

		from("disruptor://mock-present-rest?waitStrategy=Yielding").threads().to("http4://data-bus-camel");

	}

}
