package cn.rongcapital.mc2.data.bus.tools.mock.scenario.process;

import java.util.Date;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockJourneyPresentParamProcess extends AbstractAsyncProcessor {

	@SuppressWarnings("unchecked")
	@Override
	public void asyncProcess(Exchange exchange) {
		Map<String, String> body = exchange.getIn().getBody(Map.class);
		String presentBid = body.get("presentBid");
		exchange.setProperty("SCENARIO_PRESENT_BID", presentBid);
		exchange.getIn().setBody(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
	}

}
