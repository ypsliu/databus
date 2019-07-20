package cn.rongcapital.mc2.data.bus.tools.mock.common.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyNode;

@Repository
public interface JourneyNodeRepository extends GraphRepository<JourneyNode> {

	JourneyNode findOneByBid(String bid);

	List<JourneyNode> findByType(String type);

}
