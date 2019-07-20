package cn.rongcapital.mc2.data.bus.tools.mock.today.process;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.today.service.UnfinishedBehaviorService;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockCleanProcess extends AbstractAsyncProcessor {

	@Autowired
	private UnfinishedBehaviorService unfinishedBehaviorService;

	@Override
	public void asyncProcess(Exchange exchange) {
		Long unfinishedId = exchange.getProperty("UNFINISHED_ID", Long.class);
		unfinishedBehaviorService.delete(unfinishedId);
	}

}
