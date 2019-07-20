package cn.rongcapital.mc2.data.bus.tools.mock.common.mocker;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jsonzou.jmockdata.JMockData;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Mocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.OrderInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.SkuRandom;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.TitleRandom;

@Component
public class OrderMocker implements Mocker<OrderInfo> {

	@Autowired
	private TitleRandom titleRandom;

	@Autowired
	private SkuRandom skuRandom;

	@Override
	public void mock(OrderInfo model) {
		model.setOid(JMockData.mockSimpleType(String.class));
		model.setAdjustFee(JMockData.mockSimpleType(Double.class));
		model.setBuyerRate(JMockData.mockSimpleType(Integer.class));
		model.setCreated(JMockData.mockSimpleType(Date.class));
		model.setDiscountFee(JMockData.mockSimpleType(Double.class));
		model.setItemMealName(JMockData.mockSimpleType(String.class));
		model.setModified(JMockData.mockSimpleType(Date.class));
		model.setNum(JMockData.mockSimpleType(Integer.class));
		model.setNumIid(JMockData.mockSimpleType(Integer.class));
		model.setPayment(JMockData.mockSimpleType(Double.class));
		model.setPrice(JMockData.mockSimpleType(Double.class));
		model.setRefundId(JMockData.mockSimpleType(Integer.class));
		model.setRefundStatus(JMockData.mockSimpleType(String.class));
		model.setSellerRate(JMockData.mockSimpleType(Integer.class));
		model.setSkuId(JMockData.mockSimpleType(Integer.class));
		model.setSkuPropertiesName(skuRandom.random());
		model.setStatus(JMockData.mockSimpleType(String.class));
		model.setTitle(titleRandom.random());
		model.setTotalFee(JMockData.mockSimpleType(Double.class));
		model.setType(JMockData.mockSimpleType(String.class));
	}

}
