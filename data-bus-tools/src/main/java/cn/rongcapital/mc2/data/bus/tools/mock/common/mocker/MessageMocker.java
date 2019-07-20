package cn.rongcapital.mc2.data.bus.tools.mock.common.mocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.message.BehaviorMessageBuilder;
import cn.rongcapital.mc2.data.bus.tools.mock.common.Mocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.MessageInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.OrderInfo;
import cn.rongcapital.mc2.data.common.utils.GsonUtils;

@Component
public class MessageMocker implements Mocker<MessageInfo> {

	@Autowired
	private BuyerMocker buyerMocker;

	@Autowired
	private OrderMocker orderMocker;

	@Autowired
	private BuyerInfo buyerInfo;

	@Autowired
	private OrderInfo orderInfo;

	@Override
	public void mock(MessageInfo model) {
		buyerInfo.accept(buyerMocker);
		orderInfo.accept(orderMocker);
		String subjectAttrs = GsonUtils.create().toJson(buyerInfo);
		String objectAttrs = GsonUtils.create().toJson(orderInfo);
		String messageBody = BehaviorMessageBuilder.build("1", "2017-10-01", null, "WYB", "Nick", subjectAttrs, "OID", "Order", objectAttrs);
		model.setTenantId("1");
		model.setUserId("1");
		model.setMessage(messageBody);
	}

}
