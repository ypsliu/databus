package cn.rongcapital.mc2.data.bus.tools.mock.common.process;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.mocker.SellerMocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.SellerInfo;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;
import cn.rongcapital.mc2.data.common.utils.BeanContextUtils;

@Component
public class MockSellerProcess extends AbstractAsyncProcessor {

	@Autowired
	private SellerMocker sellerMocker;

	@Override
	public void asyncProcess(Exchange exchange) {
		JourneyInfo journeyInfo = exchange.getIn().getBody(JourneyInfo.class);
		if (null != journeyInfo) {
			SellerInfo sellerInfo = BeanContextUtils.context().getBean(SellerInfo.class);
			sellerInfo.accept(sellerMocker);
			journeyInfo.setSellerInfo(sellerInfo);
		}
	}

}
