package cn.rongcapital.mc2.data.bus.tools.mock.common.process;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.MessageInfo;
import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

/**
 * 调用数据总线服务处理器
 * @author WUYB
 */
@Component
public class MockMessageProcess extends AbstractAsyncProcessor {

	@Value("${mock.camel.rest.uri}")
	private String uri;

	@Override
	public void asyncProcess(Exchange exchange) {
		MessageInfo messageInfo = exchange.getIn().getBody(MessageInfo.class);
		if (null != messageInfo.getId()) {
			exchange.setProperty("UNFINISHED_ID", messageInfo.getId());
		}
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json; charset=utf-8");
		exchange.getIn().setHeader(Exchange.HTTP_METHOD, "post");
		exchange.getIn().setHeader(Exchange.HTTP_URI, uri + "/" + messageInfo.getTenantId() + "/" + messageInfo.getUserId());
		exchange.getIn().setBody(messageInfo.getMessage());
	}

}
