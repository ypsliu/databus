package cn.rongcapital.mc2.data.bus.process;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;
import cn.rongcapital.mc2.data.common.utils.GsonUtils;

@Component
public class KafkaProcess extends AbstractAsyncProcessor {

	@Value("${journey.kafka.topic_prefix}")
	private String topicPrefix;

	@SuppressWarnings("unchecked")
	@Override
	public void asyncProcess(Exchange exchange) {
		Map<String, Object> body = exchange.getIn().getBody(Map.class);
		String message = GsonUtils.create().toJson(body);
		String tenantId = exchange.getIn().getHeader("tenantId", String.class);
		String userId = exchange.getIn().getHeader("userId", String.class);
		exchange.getIn().setHeader(KafkaConstants.KEY, userId);
		exchange.getIn().setHeader(KafkaConstants.TOPIC, topicPrefix + tenantId);
		exchange.getIn().setBody(message);
		logger.info("message is {}", message);
		logger.info("jvm encoding {}", System.getProperty("file.encoding"));
	}

}
