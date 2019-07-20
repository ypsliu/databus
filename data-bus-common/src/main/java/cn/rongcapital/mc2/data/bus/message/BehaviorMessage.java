package cn.rongcapital.mc2.data.bus.message;

/**
 * 行为报文
 * @author WUYB
 *
 */
public class BehaviorMessage {

	/**
	 * 行为定义信息
	 */
	private BehaviorDefinition behavior;

	/**
	 * 行为主体
	 */
	private BehaviorSubject subject;

	/**
	 * 行为客体
	 */
	private BehaviorObject object;

	public BehaviorDefinition getBehavior() {
		return behavior;
	}

	public void setBehavior(BehaviorDefinition behavior) {
		this.behavior = behavior;
	}

	public BehaviorSubject getSubject() {
		return subject;
	}

	public void setSubject(BehaviorSubject subject) {
		this.subject = subject;
	}

	public BehaviorObject getObject() {
		return object;
	}

	public void setObject(BehaviorObject object) {
		this.object = object;
	}

}
