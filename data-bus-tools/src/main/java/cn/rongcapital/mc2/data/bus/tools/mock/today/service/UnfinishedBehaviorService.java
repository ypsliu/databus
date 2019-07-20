package cn.rongcapital.mc2.data.bus.tools.mock.today.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.UnfinishedEdge;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.UnfinishedNode;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BehaviorInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.UnfinishedEdgeRepository;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.UnfinishedNodeRepository;
import cn.rongcapital.mc2.data.common.utils.GsonUtils;

@Service
public class UnfinishedBehaviorService {

	@Autowired
	private UnfinishedNodeRepository unfinishedNodeRepository;

	@Autowired
	private UnfinishedEdgeRepository unfinishedEdgeRepository;

	public void save(List<BehaviorInfo> behaviorInfoList) {
		int i = 0;
		UnfinishedNode tempNode = null;
		for (BehaviorInfo behaviorInfo : behaviorInfoList) {
			UnfinishedNode node = new UnfinishedNode();
			node.setBid(behaviorInfo.getBid());
			node.setTime(behaviorInfo.getTime());
			node.setStatus(behaviorInfo.getStatus());
			node.setTimeout(behaviorInfo.getTimeout());
			node.setJourneyData(GsonUtils.create().toJson(behaviorInfo.getJourneyInfo()));
			unfinishedNodeRepository.save(node);
			if (0 < i) {
				UnfinishedEdge edge = new UnfinishedEdge();
				edge.setInNode(tempNode);
				edge.setOutNode(node);
				unfinishedEdgeRepository.save(edge);
			}
			tempNode = node;
			i++;
		}
	}

	public List<BehaviorInfo> query(String queryTime) {
		List<UnfinishedNode> list = unfinishedNodeRepository.findByTimeLessThanEqual(queryTime);
		return list.stream().map(n -> {
			BehaviorInfo behaviorInfo = new BehaviorInfo();
			behaviorInfo.setId(n.getId());
			behaviorInfo.setBid(n.getBid());
			behaviorInfo.setTime(n.getTime());
			behaviorInfo.setTimeout(n.getTimeout());
			behaviorInfo.setJourneyInfo(GsonUtils.create().fromJson(n.getJourneyData(), JourneyInfo.class));
			return behaviorInfo;
		}).collect(Collectors.toList());
	}

	public void delete(Long id) {
		unfinishedNodeRepository.delete(id);
	}

}
