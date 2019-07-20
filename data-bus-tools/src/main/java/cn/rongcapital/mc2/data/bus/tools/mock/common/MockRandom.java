package cn.rongcapital.mc2.data.bus.tools.mock.common;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.google.common.reflect.TypeToken;

import cn.rongcapital.mc2.data.common.utils.GsonUtils;

public abstract class MockRandom<T> implements InitializingBean {

	protected List<T> json;

	protected int jsonSize;

	@SuppressWarnings("serial")
	@Override
	public void afterPropertiesSet() throws Exception {
		Reader reader = new InputStreamReader(getResource().getInputStream());
		try {
			json = GsonUtils.create().fromJson(reader, new TypeToken<List<T>>() {}.getType());
			jsonSize = json.size();
		} finally {
			reader.close();
		}
	}

	public T random() {
		if (0 < jsonSize) {
			return json.get(RandomUtils.nextInt(0, jsonSize));
		}
		return null;
	}

	public abstract Resource getResource();

}
