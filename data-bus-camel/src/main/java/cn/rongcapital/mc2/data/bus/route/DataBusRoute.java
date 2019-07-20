package cn.rongcapital.mc2.data.bus.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.DataBusUris;
import cn.rongcapital.mc2.data.bus.process.KafkaProcess;
import cn.rongcapital.mc2.data.common.camel.NettyRestRouteBuilder;

@Component
public class DataBusRoute extends NettyRestRouteBuilder {

	@Value("${journey.kafka.host}")
	private String host;

	@Value("${journey.kafka.port}")
	private String port;

	@Value("${journey.kafka.group}")
	private String group;

	@Value("${journey.kafka.brokers}")
	private String brokers;

	@Value("${databus.rest.uri}")
	private String uri;

	@Autowired
	private KafkaProcess kafkaProcess;

	@Override
	public void configure() throws Exception {
		StringBuilder kafkaUriBuilder = new StringBuilder();
		kafkaUriBuilder.append("kafka:")
					   .append(host)
					   .append(":")
					   .append(port)
					   .append("?groupId=")
					   .append(group)
					   .append("&brokers=")
					   .append(brokers);

		rest(uri).post().produces("application/json").consumes("application/json").route().threads().to(DataBusUris.BUS_KAFAK);

		from(DataBusUris.BUS_KAFAK).threads().process(kafkaProcess).to(kafkaUriBuilder.toString());
	}

}
