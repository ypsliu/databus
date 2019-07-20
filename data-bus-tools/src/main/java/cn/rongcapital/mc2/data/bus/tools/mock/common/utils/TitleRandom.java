package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.MockRandom;

@Component
public class TitleRandom extends MockRandom<String> {

	@Value("classpath:META-INF/attrs-title-data.json")
	private Resource resource;

	@Override
	public Resource getResource() {
		return resource;
	}

}
