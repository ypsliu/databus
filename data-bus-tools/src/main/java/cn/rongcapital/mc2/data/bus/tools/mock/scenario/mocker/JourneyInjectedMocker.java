package cn.rongcapital.mc2.data.bus.tools.mock.scenario.mocker;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Mocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyEdge;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyNode;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyEdgeRepository;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyNodeRepository;

@Component
public class JourneyInjectedMocker implements Mocker<JourneyInfo> {

	@Autowired
	private JourneyNodeRepository journeyNodeRepository;

	@Autowired
	private JourneyEdgeRepository journeyEdgeRepository;

	@Override
	public void mock(JourneyInfo model) {
		List<JourneyNode> starts = journeyNodeRepository.findByType("ON_START");
		List<JourneyNode> ends = journeyNodeRepository.findByType("ON_END");

		if (!starts.isEmpty()) {
			JourneyNode start = starts.get(RandomUtils.nextInt(0, starts.size()));
			List<JourneyEdge> edges = journeyEdgeRepository.findByInNodeBid(start.getBid());
			JourneyEdge edge = edges.get(RandomUtils.nextInt(0, edges.size()));
			JourneyNode node = edge.getOutNode();
			model.setStartBid(node.getBid());
		}
		if (!ends.isEmpty()) {
			JourneyNode end = ends.get(RandomUtils.nextInt(0, ends.size()));
			List<JourneyEdge> edges = journeyEdgeRepository.findByOutNodeBid(end.getBid());
			JourneyEdge edge = edges.get(RandomUtils.nextInt(0, edges.size()));
			JourneyNode node = edge.getInNode();
			model.setEndBid(node.getBid());
		}
	}

}
