package cn.rongcapital.mc2.data.bus.tools.mock.common.domain;

import org.neo4j.ogm.annotation.GraphId;

public abstract class BaseEntity {

	@GraphId
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BaseEntity) {
			if (null == id) {
				return false;
			}

			BaseEntity entity = (BaseEntity) obj;
			if (null == entity.getId()) {
				return false;
			}

			if (id.equals(entity.getId())) {
				return true;
			}

			return false;
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public int hashCode() {
		if (null == id) {
			return super.hashCode();
		}
		return id.hashCode() + id.intValue();
	}

}
