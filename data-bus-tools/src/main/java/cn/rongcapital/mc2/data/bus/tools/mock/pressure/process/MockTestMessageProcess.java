package cn.rongcapital.mc2.data.bus.tools.mock.pressure.process;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.mocker.MessageMocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.MessageInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.service.MessageService;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;
import cn.rongcapital.mc2.data.common.utils.BeanContextUtils;

@Component
public class MockTestMessageProcess extends AbstractAsyncProcessor {

	@Value("${mock.camel.rest.uri}")
	private String uri;

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageMocker messageMocker;

	@Override
	public void asyncProcess(Exchange exchange) {
		MessageInfo messageInfo = BeanContextUtils.context().getBean(MessageInfo.class);
		messageInfo.accept(messageMocker);
		//messageService.send(messageInfo);
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json; charset=utf-8");
		exchange.getIn().setHeader(Exchange.HTTP_METHOD, "post");
		exchange.getIn().setHeader(Exchange.HTTP_URI, uri + "/" + messageInfo.getTenantId() + "/" + messageInfo.getUserId());
		exchange.getIn().setBody(messageInfo.getMessage());
	}

}
