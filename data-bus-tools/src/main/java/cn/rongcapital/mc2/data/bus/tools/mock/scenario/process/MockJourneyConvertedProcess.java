package cn.rongcapital.mc2.data.bus.tools.mock.scenario.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.scenario.mocker.JourneyConvertedMocker;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;
import cn.rongcapital.mc2.data.common.utils.BeanContextUtils;

@Component
public class MockJourneyConvertedProcess extends AbstractAsyncProcessor {

	@Value("${mock.journey.count:10}")
	private Integer journeyCount;

	@Autowired
	private JourneyConvertedMocker journeyConvertedMocker;

	@Override
	public void asyncProcess(Exchange exchange) {
		BuyerInfo buyerInfo = exchange.getIn().getBody(BuyerInfo.class);
		List<JourneyInfo> list = new ArrayList<JourneyInfo>();
		for (int i = 0; i < journeyCount; i++) {
			JourneyInfo journeyInfo = BeanContextUtils.context().getBean(JourneyInfo.class);
			journeyInfo.accept(journeyConvertedMocker);
			journeyInfo.setBuyerInfo(buyerInfo);
			list.add(journeyInfo);
		}
		exchange.getIn().setBody(list);
	}

}
