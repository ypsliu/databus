package cn.rongcapital.mc2.data.bus.tools.mock.today.process;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockQueryTimeProcess extends AbstractAsyncProcessor {

	@Override
	public void asyncProcess(Exchange exchange) {
		String queryTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
		exchange.setProperty("QUERY_TIME", queryTime);
	}

}
