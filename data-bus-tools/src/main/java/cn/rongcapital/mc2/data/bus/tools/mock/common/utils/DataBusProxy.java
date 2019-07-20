package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.graeae.endpoint.GraeaeCallback;
import cn.rongcapital.mc2.data.bus.GraeaeDataBusClient;
import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.VoidResponse;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.MessageInfo;

/**
 * 数据服务总线代理
 * @author WUYB
 *
 */
@Component
public class DataBusProxy {

	protected Logger logger = LoggerFactory.getLogger(DataBusProxy.class);

	@Autowired
	private GraeaeDataBusClient dataBusClient;

	public void push(MessageInfo messageInfo) {
		BehaviorMessageRequest request = BehaviorMessageRequest.newBuilder().setTenantId(messageInfo.getTenantId()).setMessage(messageInfo.getMessage()).build();
		VoidResponse response = dataBusClient.pushBehavior(request);
		logger.info("the bus response is {}", response.getResult());
	}

	public void asyncPush(MessageInfo messageInfo) {
		BehaviorMessageRequest request = BehaviorMessageRequest.newBuilder().setTenantId(messageInfo.getTenantId()).setMessage(messageInfo.getMessage()).build();
		dataBusClient.pushBehaviorAsync(request, new GraeaeCallback<VoidResponse>() {

			@Override
			public void onResult(VoidResponse response) {
				logger.info("the bus response result is {}", response.getResult());
			}
			
			@Override
			public void onError(Throwable response) {
				logger.error("the bus response error is {}", response.getMessage());
			}

		});
	}

}
