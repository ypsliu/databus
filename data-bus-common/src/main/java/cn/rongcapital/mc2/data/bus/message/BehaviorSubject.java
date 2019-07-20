package cn.rongcapital.mc2.data.bus.message;

import java.util.Map;

/**
 * 行为主体
 * @author WUYB
 *
 */
public class BehaviorSubject {

	/**
	 * 主体标识类型:昵称、手机号...
	 */
	private String sType;

	/**
	 * 主体标识值
	 */
	private String sid;

	/**
	 * 主体属性内容
	 */
	private Map<String, Object> attributes;

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
