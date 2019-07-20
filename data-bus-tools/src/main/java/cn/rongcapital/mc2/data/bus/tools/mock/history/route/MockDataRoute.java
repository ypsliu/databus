package cn.rongcapital.mc2.data.bus.tools.mock.history.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockBuyerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockRestParamProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockJourneyProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockMessageProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockOrderProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockPathProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockSellerProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.process.MockTimeProcess;
import cn.rongcapital.mc2.data.bus.tools.mock.common.transform.MockMessageTransform;
import cn.rongcapital.mc2.data.common.camel.NettyRestRouteBuilder;

@Component
public class MockDataRoute extends NettyRestRouteBuilder {

	@Value("${mock.history.rest.uri}")
	private String uri;

	@Autowired
	private MockRestParamProcess mockRestParamProcess;

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
	private MockMessageProcess mockMessageProcess;

	@Autowired
	private MockMessageTransform mockMessageTransform;

	@Override
	public void configure() throws Exception {

		rest(uri).post().produces("application/json").consumes("application/json").route().threads().to("disruptor://mock-history-param");

		from("disruptor://mock-history-param").process(mockRestParamProcess).split().body().parallelProcessing().to("disruptor://mock-history-buyer");

		from("disruptor://mock-history-buyer?waitStrategy=Yielding").threads().process(mockBuyerProcess).split().body().parallelProcessing().to("disruptor://mock-history-journey");

		from("disruptor://mock-history-journey?waitStrategy=Yielding").threads().process(mockJourneyProcess).split().body().parallelProcessing().to("disruptor://mock-history-seller");

		from("disruptor://mock-history-seller?waitStrategy=Yielding").threads().process(mockSellerProcess).to("disruptor://mock-history-order");

		from("disruptor://mock-history-order?waitStrategy=Yielding").threads().process(mockOrderProcess).to("disruptor://mock-history-path");

		from("disruptor://mock-history-path?waitStrategy=Yielding").threads().process(mockPathProcess).to("disruptor://mock-history-time");

		from("disruptor://mock-history-time?waitStrategy=Yielding").threads().process(mockTimeProcess).split().body().parallelProcessing().to("disruptor://mock-history-transform");

		from("disruptor://mock-history-transform?waitStrategy=Yielding").threads().transform(mockMessageTransform).to("disruptor://mock-history-message");

		from("disruptor://mock-history-message?waitStrategy=Yielding").threads().process(mockMessageProcess).to("disruptor://mock-history-rest");

		from("disruptor://mock-history-rest?waitStrategy=Yielding").threads().to("http4://data-bus-camel");

	}

}
