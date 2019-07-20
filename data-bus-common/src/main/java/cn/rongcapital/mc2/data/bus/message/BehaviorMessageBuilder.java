package cn.rongcapital.mc2.data.bus.message;

import com.google.common.base.Function;

/**]
 *
 * @author WUYB
 */
public class BehaviorMessageBuilder {

	private final static StringBuilder behaviorTemplate = new StringBuilder();

	static {
		behaviorTemplate.append("{")
						.append("\"behavior\":{")		// 行为
						.append("\"bid\":\"%s\",")		// 行为id
						.append("\"time\":\"%s\",")		// 行为发生的日期时间
						.append("\"attributes\":%s")	// 行为属性
						.append("},")
						.append("\"subject\":{")		// 主体
						.append("\"sid\":\"%s\",")		// 主体标识, 如: 1300000000
						.append("\"sType\":\"%s\",")	// 主体标识类型, 如: 手机号码、身份证、用户名
						.append("\"attributes\":%s")	// 主体属性, 保留部分属性, 如: mobile、email
						.append("},")
						.append("\"object\":{")			// 客体
						.append("\"oid\":\"%s\",")		// 客体标识内容, 如: 32354354322
						.append("\"oType\":\"%s\",")	// 客体标识类型, 如: 订单、session
						.append("\"attributes\":%s")	// 客体属性
						.append("}")
						.append("}");
	}

	public static String build(String behaviorId, String behaviorTime, String behaviorAttrs, 
								String subjectId, String subjectIdType, String subjectAttrs, 
								String objectId, String objectIdType, String objectAttrs) {

		Function<String, String> fnStr = (str) -> {
			str = null == str ? "" : str;
			return str;
		};

		Function<String, String> fnObj = (str) -> {
			str = null == str ? "{}" : str;
			return str;
		};

		return String.format(behaviorTemplate.toString(), 
							fnStr.apply(behaviorId), 
							fnStr.apply(behaviorTime), 
							fnObj.apply(behaviorAttrs), 
							fnStr.apply(subjectId), 
							fnStr.apply(subjectIdType), 
							fnObj.apply(subjectAttrs), 
							fnStr.apply(objectId), 
							fnStr.apply(objectIdType), 
							fnObj.apply(objectAttrs));
}

}
