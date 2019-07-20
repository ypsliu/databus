package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.MockRandom;

@Component
public class TenantRandom extends MockRandom<Map<String, Object>> {

	@Value("classpath:META-INF/tenant-data.json")
	private Resource resource;

	@Override
	public Resource getResource() {
		return resource;
	}

}
