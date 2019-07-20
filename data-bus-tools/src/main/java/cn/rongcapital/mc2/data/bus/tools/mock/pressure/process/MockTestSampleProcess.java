package cn.rongcapital.mc2.data.bus.tools.mock.pressure.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockTestSampleProcess extends AbstractAsyncProcessor {

	@Value("${mock.pressure.count}")
	private Integer count;

	@Override
	public void asyncProcess(Exchange exchange) {
		List<Byte> queue = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			queue.add(new Byte(Byte.MAX_VALUE));
		}
		exchange.getIn().setBody(queue);
	}

}
