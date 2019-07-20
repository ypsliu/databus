package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyEdge;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyNode;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyEdgeRepository;

/**
 * 递归方式遍历实现
 * @author WUYB
 *
 */
@Component
public class BehaviorTraversal {

	@Autowired
	private JourneyEdgeRepository journeyEdgeRepository;

	public List<List<JourneyEdge>> traverse(JourneyNode start, JourneyNode end) {
		List<List<JourneyEdge>> pathList = new ArrayList<List<JourneyEdge>>();
		traverseRel(start.getBid(), end.getBid(), null, pathList);
		return pathList;
	}

	private void traverseRel(String inBid, String outBid, List<JourneyEdge> relList, List<List<JourneyEdge>> pathList) {
		List<JourneyEdge> edges = journeyEdgeRepository.findByOutNodeBid(outBid);
		for (JourneyEdge edge : edges) {
			List<JourneyEdge> _relList = new ArrayList<JourneyEdge>();
			if (null != relList) {
				relList.forEach(r -> {
					_relList.add(r);
				});
			}
			if (!_relList.contains(edge)) {
				_relList.add(edge);
			}
			JourneyNode node = edge.getInNode();
			if (null == node.getBid() || inBid.equals(node.getBid())) {
				parsePath(edge, _relList, pathList);
			} else {
				traverseRel(inBid, node.getBid(), _relList, pathList);
			}
		}
	}

	private void parsePath(JourneyEdge edge, List<JourneyEdge> relList, List<List<JourneyEdge>> pathList) {
		List<JourneyEdge> reverseRelList = new ArrayList<JourneyEdge>();
		relList.forEach(r -> {
			reverseRelList.add(r);
		});
		Collections.reverse(reverseRelList);
		pathList.add(reverseRelList);
	}

}
