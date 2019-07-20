package cn.rongcapital.mc2.data.bus.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.kafka.KafkaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import cn.rongcapital.graeae.endpoint.annotation.GraeaeService;
import cn.rongcapital.mc2.data.bus.DataBusRoutes;
import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.DataBusService;
import cn.rongcapital.mc2.data.bus.protobuf.VoidResponse;
import cn.rongcapital.mc2.data.common.validation.ValidationMessage;

/**
 * @author WUYB
 */
@ValidationMessage
@GraeaeService
public class DataBusServiceImpl implements DataBusService {

	private Logger logger = LoggerFactory.getLogger(DataBusServiceImpl.class);

	@Value("${journey.kafka.topic_prefix}")
	private String kafkaTopic;

	@EndpointInject
	private ProducerTemplate producerTemplate;

	@Override
	public VoidResponse pushBehavior(BehaviorMessageRequest req) {
		logger.info("the req is {}", req);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(KafkaConstants.KEY, req.getUserId());
		headers.put(KafkaConstants.TOPIC, kafkaTopic + req.getTenantId());
		//producerTemplate.asyncRequestBodyAndHeaders(DataBusRoutes.BUS_KAFAK, req.getMessage(), headers);
		return VoidResponse.newBuilder().setResult(1).build();
	}

}
