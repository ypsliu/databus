package cn.rongcapital.mc2.data.bus.tools.mock.common.process;

import cn.rongcapital.mc2.data.bus.tools.mock.common.mocker.OrderMocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.OrderInfo;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;
import cn.rongcapital.mc2.data.common.utils.BeanContextUtils;

@Component
public class MockOrderProcess extends AbstractAsyncProcessor {

	@Autowired
	private OrderMocker orderMocker;

	@Override
	public void asyncProcess(Exchange exchange) {
		JourneyInfo journeyInfo = exchange.getIn().getBody(JourneyInfo.class);
		if (null != journeyInfo) {
			OrderInfo orderInfo = BeanContextUtils.context().getBean(OrderInfo.class);
			orderInfo.accept(orderMocker);
			journeyInfo.setOrderInfo(orderInfo);
		}
	}

}
