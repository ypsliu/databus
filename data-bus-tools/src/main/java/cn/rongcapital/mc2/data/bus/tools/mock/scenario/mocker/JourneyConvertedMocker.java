package cn.rongcapital.mc2.data.bus.tools.mock.scenario.mocker;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Mocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyNode;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.JourneyInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyNodeRepository;

@Component
public class JourneyConvertedMocker implements Mocker<JourneyInfo> {

	@Autowired
	private JourneyNodeRepository journeyNodeRepository;

	@Override
	public void mock(JourneyInfo model) {
		List<JourneyNode> starts = journeyNodeRepository.findByType("ON_START");
		List<JourneyNode> ends = journeyNodeRepository.findByType("ON_END");

		if (!starts.isEmpty()) {
			JourneyNode start = starts.get(RandomUtils.nextInt(0, starts.size()));
			model.setStartBid(start.getBid());
		}
		if (!ends.isEmpty()) {
			JourneyNode end = ends.get(RandomUtils.nextInt(0, ends.size()));
			model.setEndBid(end.getBid());
		}
	}

}
