package cn.rongcapital.mc2.data.bus.tools.mock.common.mocker;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.github.jsonzou.jmockdata.JMockData;
import com.google.common.reflect.TypeToken;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Mocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.common.utils.GsonUtils;

@Component
public class BuyerMocker implements Mocker<BuyerInfo>, InitializingBean {

	@Value("${mock.buyer.pool.maxSize}")
	private Integer maxSize;

	@Value("classpath:META-INF/nick-data.json")
	private Resource resource;

	List<String> nickList;

	@Override
	public void mock(BuyerInfo model) {
		model.setUserId(UUID.randomUUID().toString());
		model.setBuyerEmail(JMockData.mockSimpleType(String.class));
		model.setBuyerNick(JMockData.mockSimpleType(String.class));
		model.setBuyerNick(nickList.get(RandomUtils.nextInt(0, nickList.size())));
		model.setReceiverState(JMockData.mockSimpleType(String.class));
		model.setReceiverCity(JMockData.mockSimpleType(String.class));
		model.setReceiverDistrict(JMockData.mockSimpleType(String.class));
		model.setReceiverMobile(JMockData.mockSimpleType(String.class));
		model.setShoppingDesire(JMockData.mockSimpleType(Integer.class));

	}

	@SuppressWarnings("serial")
	@Override
	public void afterPropertiesSet() throws Exception {
		Reader reader = new InputStreamReader(resource.getInputStream());
		try {
			nickList = GsonUtils.create().fromJson(reader, new TypeToken<List<String>>() {}.getType());
		} finally {
			reader.close();
		}
	}

}
