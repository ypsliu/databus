package cn.rongcapital.mc2.data.bus.tools.mock.common.mocker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Mocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.SellerInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.TenantRandom;

@Component
public class SellerMocker implements Mocker<SellerInfo> {

	@Autowired
	private TenantRandom tenantRandom;

	@Override
	public void mock(SellerInfo model) {
		Map<String, Object> tenant = tenantRandom.random();
		if (null != tenant) {
			model.setTenantId((String) tenant.get("id"));
			model.setNick((String) tenant.get("nick"));
		}
	}

}
