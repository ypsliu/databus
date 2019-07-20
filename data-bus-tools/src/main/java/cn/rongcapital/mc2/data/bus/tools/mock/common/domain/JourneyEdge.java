package cn.rongcapital.mc2.data.bus.tools.mock.common.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "JourneyEdge")
public class JourneyEdge extends BaseEntity {

	private Integer timeout;

	@StartNode
	private JourneyNode inNode;

	@EndNode
	private JourneyNode outNode;

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public JourneyNode getInNode() {
		return inNode;
	}

	public void setInNode(JourneyNode inNode) {
		this.inNode = inNode;
	}

	public JourneyNode getOutNode() {
		return outNode;
	}

	public void setOutNode(JourneyNode outNode) {
		this.outNode = outNode;
	}

}
