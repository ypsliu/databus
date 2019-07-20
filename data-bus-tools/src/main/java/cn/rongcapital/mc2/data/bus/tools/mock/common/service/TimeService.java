package cn.rongcapital.mc2.data.bus.tools.mock.common.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.TimeSelector;

@Component
public class TimeService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TimeSelector timeSelector;

	public void setting(String startTime, List<BehaviorInfo> behaviorInfoList) {
		if (null != behaviorInfoList) {
			Integer timeout = 0;
			String nextTime = null;
			int i = 0;
			for (BehaviorInfo behaviorInfo : behaviorInfoList) {
				if (0 == i) {
					nextTime = timeSelector.select(startTime);
				} else {
					nextTime = timeSelector.select(nextTime, timeout, 1);
				}
				behaviorInfo.setTime(nextTime);
				timeout = behaviorInfo.getTimeout();
				i++;
			}
		}
	}

	public void settingNotTimeout(String startTime, List<BehaviorInfo> behaviorInfoList) {
		if (null != behaviorInfoList) {
			Integer timeout = 0;
			String nextTime = null;
			int i = 0;
			for (BehaviorInfo behaviorInfo : behaviorInfoList) {
				if (0 == i) {
					nextTime = timeSelector.select(startTime);
				} else {
					nextTime = timeSelector.selectNotTimeout(nextTime, timeout, 1);
				}
				behaviorInfo.setTime(nextTime);
				timeout = behaviorInfo.getTimeout();
				i++;
			}
		}
	}

	public void settingTimeout(String startTime, List<BehaviorInfo> behaviorInfoList) {
		if (null != behaviorInfoList) {
			Integer timeout = 0;
			String nextTime = null;
			int i = 0;
			for (BehaviorInfo behaviorInfo : behaviorInfoList) {
				if (0 == i) {
					nextTime = timeSelector.select(startTime);
				} else {
					nextTime = timeSelector.selectTimeout(nextTime, timeout, 1);
				}
				behaviorInfo.setTime(nextTime);
				timeout = behaviorInfo.getTimeout();
				i++;
			}
		}
	}

}
