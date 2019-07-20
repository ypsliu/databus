package cn.rongcapital.mc2.data.bus.tools.mock.common.process;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.service.TimeService;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockNotTimeoutProcess extends AbstractAsyncProcessor {

	@Autowired
	private TimeService timeService;

	@SuppressWarnings("unchecked")
	@Override
	public void asyncProcess(Exchange exchange) {
		List<BehaviorInfo> behaviorInfoList = exchange.getIn().getBody(List.class);
		String startTime = exchange.getProperty("PATH_START_TIME", String.class);
		timeService.settingNotTimeout(startTime, behaviorInfoList);
		exchange.getIn().setBody(behaviorInfoList);
	}

}
