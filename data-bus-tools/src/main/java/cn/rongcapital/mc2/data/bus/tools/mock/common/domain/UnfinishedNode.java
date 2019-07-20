package cn.rongcapital.mc2.data.bus.tools.mock.common.domain;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "UnfinishedNode")
public class UnfinishedNode extends BaseEntity {

	private String bid;

	private String time;

	private Integer timeout;

	private String status;

	private String journeyData;

	@Relationship(type = "UnfinishedEdge")
	private List<UnfinishedEdge> unfinishedEdges;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJourneyData() {
		return journeyData;
	}

	public void setJourneyData(String journeyData) {
		this.journeyData = journeyData;
	}

	public List<UnfinishedEdge> getUnfinishedEdges() {
		return unfinishedEdges;
	}

	public void setUnfinishedEdges(List<UnfinishedEdge> unfinishedEdges) {
		this.unfinishedEdges = unfinishedEdges;
	}

}
