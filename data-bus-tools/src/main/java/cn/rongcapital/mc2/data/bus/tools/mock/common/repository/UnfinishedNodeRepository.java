package cn.rongcapital.mc2.data.bus.tools.mock.common.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.UnfinishedNode;

@Repository
public interface UnfinishedNodeRepository extends GraphRepository<UnfinishedNode> {

	List<UnfinishedNode> findByTimeLessThanEqual(String time);

}
