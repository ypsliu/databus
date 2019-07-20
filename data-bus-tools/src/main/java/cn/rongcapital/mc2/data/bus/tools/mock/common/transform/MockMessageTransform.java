package cn.rongcapital.mc2.data.bus.tools.mock.common.transform;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.message.BehaviorMessageBuilder;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.MessageInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.OrderInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.SellerInfo;
import cn.rongcapital.mc2.data.common.utils.BeanContextUtils;
import cn.rongcapital.mc2.data.common.utils.GsonUtils;

@Component
public class MockMessageTransform implements Expression {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T evaluate(Exchange exchange, Class<T> type) {
		BehaviorInfo behaviorInfo = exchange.getIn().getBody(BehaviorInfo.class);
		JourneyInfo journeyInfo = behaviorInfo.getJourneyInfo();
		SellerInfo sellerInfo = journeyInfo.getSellerInfo();
		BuyerInfo buyerInfo = journeyInfo.getBuyerInfo();
		OrderInfo orderInfo = journeyInfo.getOrderInfo();

		String behaviorId = behaviorInfo.getBid();
		String behaviorTime = behaviorInfo.getTime();
		String subjectId = buyerInfo.getBuyerNick();
		String subjectIdType = "Nick";
		String subjectAttrs = GsonUtils.create().toJson(buyerInfo);
		String objectId = orderInfo.getOid();
		String objectIdType = "Order";
		String objectAttrs = GsonUtils.create().toJson(orderInfo);

		String messageBody = BehaviorMessageBuilder.build(behaviorId, behaviorTime, null, subjectId, subjectIdType, subjectAttrs, objectId, objectIdType, objectAttrs);

		MessageInfo messageInfo = BeanContextUtils.context().getBean(MessageInfo.class);
		messageInfo.setId(behaviorInfo.getId());
		messageInfo.setTenantId(sellerInfo.getTenantId());
		messageInfo.setUserId(buyerInfo.getUserId());
		messageInfo.setMessage(messageBody);
		return (T) messageInfo;
	}

}
