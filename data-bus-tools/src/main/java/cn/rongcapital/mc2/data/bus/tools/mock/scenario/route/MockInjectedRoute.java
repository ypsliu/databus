package cn.rongcapital.mc2.data.bus.tools.mock.scenario.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockBuyerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockMessageProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockOrderProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockPathProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockRestParamProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockSellerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockTimeWithTimeoutProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.transform.MockMessageTransform;
import cn.rongcapital.mc2.data.bus.tools.mock.scenario.process.MockJourneyInjectedProcess;
import cn.rongcapital.mc2.data.common.camel.NettyRestRouteBuilder;

@Component
public class MockInjectedRoute extends NettyRestRouteBuilder {

	@Value("${mock.scenario.injected.rest.uri}")
	private String uri;

	@Autowired
	private MockRestParamProcess mockRestParamProcess;

	@Autowired
	private MockBuyerProcess mockBuyerProcess;

	@Autowired
	private MockJourneyInjectedProcess mockJourneyInjectedProcess;

	@Autowired
	private MockSellerProcess mockSellerProcess;

	@Autowired
	private MockOrderProcess mockOrderProcess;

	@Autowired
	private MockPathProcess mockPathProcess;

	@Autowired
	private MockTimeWithTimeoutProcess mockTimeWithTimeoutProcess;

	@Autowired
	private MockMessageProcess mockMessageProcess;

	@Autowired
	private MockMessageTransform mockMessageTransform;

	@Override
	public void configure() throws Exception {

		rest(uri).post().produces("application/json").consumes("application/json").route().threads().to("disruptor://mock-injected-param");

		from("disruptor://mock-injected-param").process(mockRestParamProcess).split().body().parallelProcessing().to("disruptor://mock-injected-buyer");

		from("disruptor://mock-injected-buyer?waitStrategy=Yielding").threads().process(mockBuyerProcess).split().body().parallelProcessing().to("disruptor://mock-injected-journey");

		from("disruptor://mock-injected-journey?waitStrategy=Yielding").threads().process(mockJourneyInjectedProcess).split().body().parallelProcessing().to("disruptor://mock-injected-seller");

		from("disruptor://mock-injected-seller?waitStrategy=Yielding").threads().process(mockSellerProcess).to("disruptor://mock-injected-order");

		from("disruptor://mock-injected-order?waitStrategy=Yielding").threads().process(mockOrderProcess).to("disruptor://mock-injected-path");

		from("disruptor://mock-injected-path?waitStrategy=Yielding").threads().process(mockPathProcess).to("disruptor://mock-injected-time");

		from("disruptor://mock-injected-time?waitStrategy=Yielding").threads().process(mockTimeWithTimeoutProcess).split().body().parallelProcessing().to("disruptor://mock-injected-transform");

		from("disruptor://mock-injected-transform?waitStrategy=Yielding").threads().transform(mockMessageTransform).to("disruptor://mock-injected-message");

		from("disruptor://mock-injected-message?waitStrategy=Yielding").threads().process(mockMessageProcess).to("disruptor://mock-injected-rest");

		from("disruptor://mock-injected-rest?waitStrategy=Yielding").threads().to("http4://data-bus-camel");

	}

}
