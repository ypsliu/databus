package cn.rongcapital.mc2.data.bus.tools.mock.today.process;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.today.service.UnfinishedBehaviorService;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockSaveProcess extends AbstractAsyncProcessor {

	@Autowired
	private UnfinishedBehaviorService unfinishedBehaviorService;

	@SuppressWarnings("unchecked")
	@Override
	public void asyncProcess(Exchange exchange) {
		List<BehaviorInfo> behaviorInfoList = exchange.getIn().getBody(List.class);
		unfinishedBehaviorService.save(behaviorInfoList);
	}

}
