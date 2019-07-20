package cn.rongcapital.mc2.data.bus.tools.mock.common.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "UnfinishedEdge")
public class UnfinishedEdge extends BaseEntity {

	private Integer timeout;

	@StartNode
	private UnfinishedNode inNode;

	@EndNode
	private UnfinishedNode outNode;

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public UnfinishedNode getInNode() {
		return inNode;
	}

	public void setInNode(UnfinishedNode inNode) {
		this.inNode = inNode;
	}

	public UnfinishedNode getOutNode() {
		return outNode;
	}

	public void setOutNode(UnfinishedNode outNode) {
		this.outNode = outNode;
	}

}
