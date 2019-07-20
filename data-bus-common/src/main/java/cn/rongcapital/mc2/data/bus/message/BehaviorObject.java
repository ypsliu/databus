package cn.rongcapital.mc2.data.bus.message;

import java.util.Map;

/**
 * 行为客体
 * 
 * @author WUYB
 *
 */
public class BehaviorObject {

	/**
	 * 客体标识类型
	 */
	private String oType;

	/**
	 * 客体标识值
	 */
	private String oid;

	/**
	 * 客体属性内容
	 */
	private Map<String, Object> attributes;

	public String getoType() {
		return oType;
	}

	public void setoType(String oType) {
		this.oType = oType;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
