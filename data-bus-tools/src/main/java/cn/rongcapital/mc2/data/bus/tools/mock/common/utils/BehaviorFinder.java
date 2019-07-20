package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.internal.compiler.v3_2.commands.expressions.PathImpl;
import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyEdge;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyNode;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyEdgeRepository;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyNodeRepository;

/**
 * 利用Neo4j-CQL方式实现
 * @author WUYB
 *
 */
@Component
public class BehaviorFinder {

	@Autowired
	private JourneyNodeRepository journeyNodeRepository;

	@Autowired
	private JourneyEdgeRepository journeyEdgeRepository;

	public List<List<JourneyNode>> findPath(String startBid, String endBid) {
		JourneyNode start = journeyNodeRepository.findOneByBid(startBid);
		JourneyNode end = journeyNodeRepository.findOneByBid(endBid);
		return findPath(start, end);
	}

	@SuppressWarnings("unchecked")
	public List<List<JourneyNode>> findPath(JourneyNode start, JourneyNode end) {
		List<List<JourneyNode>> pathGroupList = new ArrayList<List<JourneyNode>>();
		Result rs = journeyEdgeRepository.findPaths(start.getBid(), end.getBid());
		rs.queryResults().forEach(map -> {
			List<JourneyNode> nodeList = new ArrayList<JourneyNode>();
			Object p = map.get("p");
			if (p instanceof PathImpl) {
				PathImpl pathImpl = (PathImpl) p;
				pathImpl.relationships().forEach(r -> {
					nodeList.add(journeyNodeRepository.findOne(r.getId()));
				});
			} else if (p instanceof Map) {
				Map<String, Object> m = (Map<String, Object>) p;
				List<String> list = (List<String>) m.get("relationships");
				list.forEach(str -> {
					Long id = Long.parseLong(str.substring(str.lastIndexOf("/") + 1));
					nodeList.add(journeyNodeRepository.findOne(id));
				});
			} else {
				throw new RuntimeException("未知的journeyEdgeRepository.findPaths查询结果");
			}
			pathGroupList.add(nodeList);
		});
		return pathGroupList;
	}

	public List<List<JourneyEdge>> findRel(String startBid, String endBid) {
		JourneyNode start = journeyNodeRepository.findOneByBid(startBid);
		JourneyNode end = journeyNodeRepository.findOneByBid(endBid);
		return findRel(start, end);
	}

	@SuppressWarnings("unchecked")
	public List<List<JourneyEdge>> findRel(JourneyNode start, JourneyNode end) {
		List<List<JourneyEdge>> pathList = new ArrayList<List<JourneyEdge>>();
		Result rs = journeyEdgeRepository.findPaths(start.getBid(), end.getBid());
		rs.queryResults().forEach(map -> {
			List<JourneyEdge> relList = new ArrayList<JourneyEdge>();
			Object p = map.get("p");
			if (p instanceof PathImpl) {
				PathImpl pathImpl = (PathImpl) p;
				pathImpl.relationships().forEach(r -> {
					relList.add(journeyEdgeRepository.findOne(r.getId()));
				});
			} else if (p instanceof Map) {
				Map<String, Object> m = (Map<String, Object>) p;
				List<String> list = (List<String>) m.get("relationships");
				list.forEach(str -> {
					Long id = Long.parseLong(str.substring(str.lastIndexOf("/") + 1));
					relList.add(journeyEdgeRepository.findOne(id));
				});
			} else {
				throw new RuntimeException("未知的journeyEdgeRepository.findPaths查询结果");
			}
			pathList.add(relList);
		});
		return pathList;
	}

}
