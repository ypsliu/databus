package cn.rongcapital.mc2.data.bus.tools.mock.common.process;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.service.BuyerService;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockBuyerProcess extends AbstractAsyncProcessor {

	@Autowired
	private BuyerService buyerService;

	@Override
	public void asyncProcess(Exchange exchange) {
		List<BuyerInfo> buyerQueue = buyerService.getNewBuyerQueue();
		String startTime = exchange.getIn().getBody(String.class);
		exchange.setProperty("PATH_START_TIME", startTime);
		exchange.getIn().setBody(buyerQueue);
	}

}
