package cn.rongcapital.mc2.data.bus.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.DataBusRoutes;

//@Component
public class DataKafakRoute extends RouteBuilder {

	@Value("${journey.kafka.host}")
	private String kafkaHost;

	@Value("${journey.kafka.port}")
	private String kafkaPort;

	@Value("${journey.kafka.topic_prefix}")
	private String kafkaTopic;

	@Value("${journey.kafka.group}")
	private String kafkaGroup;

	@Value("${journey.kafka.brokers}")
	private String kafkaBrokers;

	@Override
	public void configure() throws Exception {
		StringBuilder kafkaUriBuilder = new StringBuilder();
		kafkaUriBuilder.append("kafka:")
					   .append(kafkaHost)
					   .append(":")
					   .append(kafkaPort)
					   //.append("?topic=")
					   //.append(kafkaTopic)
					   //.append("-${header.tenantId}")
					   .append("?groupId=")
					   .append(kafkaGroup)
					   .append("&brokers=")
					   .append(kafkaBrokers);
		
		from(DataBusRoutes.BUS_KAFAK).threads().toD(kafkaUriBuilder.toString()).log("${body}");
	}

}
