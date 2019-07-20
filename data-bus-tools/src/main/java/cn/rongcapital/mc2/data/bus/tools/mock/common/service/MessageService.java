package cn.rongcapital.mc2.data.bus.tools.mock.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.MessageInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.DataBusProxy;

@Component
public class MessageService {

	@Autowired
	private DataBusProxy dataBusProxy;

	public void send(MessageInfo messageInfo) {
		if (null != messageInfo) {
			dataBusProxy.asyncPush(messageInfo);
		}
	}

}
