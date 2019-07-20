package cn.rongcapital.mc2.data.bus.tools.mock.common.process;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.service.PathService;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockPathProcess extends AbstractAsyncProcessor {

	@Autowired
	private PathService pathService;

	@Override
	public void asyncProcess(Exchange exchange) {
		JourneyInfo journeyInfo = exchange.getIn().getBody(JourneyInfo.class);
		List<BehaviorInfo> behaviorInfoList = null;
		if (null != journeyInfo) {
			behaviorInfoList = pathService.match(journeyInfo);
		}
		exchange.getIn().setBody(behaviorInfoList);
	}

}
