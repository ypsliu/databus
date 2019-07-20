package cn.rongcapital.mc2.data.bus.tools.mock.today.process;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.today.service.UnfinishedBehaviorService;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockQueryProcess extends AbstractAsyncProcessor {

	@Autowired
	private UnfinishedBehaviorService unfinishedBehaviorService;

	@Override
	public void asyncProcess(Exchange exchange) {
		String queryTime = exchange.getProperty("QUERY_TIME", String.class);
		List<BehaviorInfo> list = unfinishedBehaviorService.query(queryTime);
		exchange.getIn().setBody(list);
	}

}
