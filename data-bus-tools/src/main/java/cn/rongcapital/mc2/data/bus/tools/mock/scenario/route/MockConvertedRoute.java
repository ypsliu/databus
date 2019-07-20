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
import cn.rongcapital.mc2.data.bus.tools.mock.scenario.process.MockJourneyConvertedProcess;
import cn.rongcapital.mc2.data.common.camel.NettyRestRouteBuilder;

//@Component
public class MockConvertedRoute extends NettyRestRouteBuilder {

	@Value("${mock.scenario.converted.rest.uri}")
	private String uri;

	@Autowired
	private MockRestParamProcess mockRestParamProcess;

	@Autowired
	private MockBuyerProcess mockBuyerProcess;

	@Autowired
	private MockJourneyConvertedProcess mockJourneyConvertedProcess;

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

		rest(uri).post().produces("application/json").consumes("application/json").route().threads().to("disruptor://mock-converted-param");

		from("disruptor://mock-converted-param").process(mockRestParamProcess).split().body().parallelProcessing().to("disruptor://mock-converted-buyer");

		from("disruptor://mock-converted-buyer?waitStrategy=Yielding").threads().process(mockBuyerProcess).split().body().parallelProcessing().to("disruptor://mock-converted-journey");

		from("disruptor://mock-converted-journey?waitStrategy=Yielding").threads().process(mockJourneyConvertedProcess).split().body().parallelProcessing().to("disruptor://mock-converted-seller");

		from("disruptor://mock-converted-seller?waitStrategy=Yielding").threads().process(mockSellerProcess).to("disruptor://mock-converted-order");

		from("disruptor://mock-converted-order?waitStrategy=Yielding").threads().process(mockOrderProcess).to("disruptor://mock-converted-path");

		from("disruptor://mock-converted-path?waitStrategy=Yielding").threads().process(mockPathProcess).to("disruptor://mock-converted-time");

		from("disruptor://mock-converted-time?waitStrategy=Yielding").threads().process(mockNotTimeoutProcess).split().body().parallelProcessing().to("disruptor://mock-converted-transform");

		from("disruptor://mock-converted-transform?waitStrategy=Yielding").threads().transform(mockMessageTransform).to("disruptor://mock-converted-message");

		from("disruptor://mock-converted-message?waitStrategy=Yielding").threads().process(mockMessageProcess).to("disruptor://mock-converted-rest");

		from("disruptor://mock-converted-rest?waitStrategy=Yielding").threads().to("http4://data-bus-camel");

	}

}
