package cn.rongcapital.mc2.data.bus.message;

import java.util.Map;

/**
 * 行为定义
 * 
 * @author WUYB
 *
 */
public class BehaviorDefinition {

	/**
	 * 行为标识: 与行为管理中相对应
	 */
	private String id;

	/**
	 * 行为发生时间, 精确到秒
	 */
	private String time;

	/**
	 * 主体属性内容
	 */
	private Map<String, Object> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
