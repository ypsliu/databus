package cn.rongcapital.mc2.data.bus.tools.mock.common.repository;

import java.util.List;

import org.neo4j.ogm.model.Result;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyEdge;

@Repository
public interface JourneyEdgeRepository extends GraphRepository<JourneyEdge> {

	JourneyEdge findOneByInNodeBidAndOutNodeBid(String inBid, String outBid);

	List<JourneyEdge> findByOutNodeBid(String outBid);

	List<JourneyEdge> findByInNodeBid(String inBid);

	@Query("MATCH p=(s:JourneyNode{bid:{inBid}})-[r:JourneyEdge*]->(e:JourneyNode{bid:{outBid}}) RETURN p")
	Result findPaths(@Param("inBid") String inBid, @Param("outBid") String outBid);

}
