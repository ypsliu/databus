package cn.rongcapital.mc2.data.bus.tools.mock.common.domain;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "JourneyNode")
public class JourneyNode extends BaseEntity {

	private String bid;

	private String name;

	private Integer index;

	private String type;

	private Double coefficient;

	@Relationship(type = "JourneyEdge")
	private List<JourneyEdge> journeyEdges;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public List<JourneyEdge> getJourneyEdges() {
		return journeyEdges;
	}

	public void setJourneyEdges(List<JourneyEdge> journeyEdges) {
		this.journeyEdges = journeyEdges;
	}

}
