package cn.rongcapital.mc2.data.bus.tools.mock.common.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.UnfinishedEdge;

@Repository
public interface UnfinishedEdgeRepository extends GraphRepository<UnfinishedEdge> {

}
