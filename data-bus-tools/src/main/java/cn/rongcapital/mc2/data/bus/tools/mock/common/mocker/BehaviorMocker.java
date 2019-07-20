package cn.rongcapital.mc2.data.bus.tools.mock.common.mocker;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Mocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import com.github.jsonzou.jmockdata.JMockData;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

@Component
public class BehaviorMocker implements Mocker<BehaviorInfo> {

	@Override
	public void mock(BehaviorInfo model) {
		Date date = JMockData.mockSimpleType(Date.class);
		model.setTime(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
	}

}
