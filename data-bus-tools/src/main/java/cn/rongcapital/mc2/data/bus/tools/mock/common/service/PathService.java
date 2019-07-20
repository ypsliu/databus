package cn.rongcapital.mc2.data.bus.tools.mock.common.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyEdge;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyNode;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.BehaviorFinder;
import cn.rongcapital.mc2.data.common.utils.BeanContextUtils;

@Component
public class PathService {

	@Autowired
	private BehaviorFinder behaviorFinder;

	@Transactional
	public List<BehaviorInfo> match(JourneyInfo journeyInfo) {
		String startBid = journeyInfo.getStartBid();
		String endBid = journeyInfo.getEndBid();
		List<BehaviorInfo> pathList = new ArrayList<BehaviorInfo>();
		if (null == startBid || null == endBid) {
			return pathList;
		}
		List<List<JourneyEdge>> relGroupList = behaviorFinder.findRel(startBid, endBid);
		if (!relGroupList.isEmpty()) {
			int index = RandomUtils.nextInt(0, relGroupList.size());
			List<JourneyEdge> relList = relGroupList.get(index);
			for (JourneyEdge rel : relList) {
				JourneyNode startNode = rel.getInNode();
				JourneyNode endNode = rel.getOutNode();
				String bid = startNode.getBid();
				String nextBid = endNode.getBid();
				Integer timeout = rel.getTimeout();
				BehaviorInfo path = BeanContextUtils.context().getBean(BehaviorInfo.class);
				path.setBid(bid);
				path.setNextBid(nextBid);
				path.setTimeout(timeout);
				path.setJourneyInfo(journeyInfo);
				pathList.add(path);
			}
			BehaviorInfo path = BeanContextUtils.context().getBean(BehaviorInfo.class);
			path.setBid(endBid);
			path.setJourneyInfo(journeyInfo);
			pathList.add(path);
		}
		return pathList;
	}

}
