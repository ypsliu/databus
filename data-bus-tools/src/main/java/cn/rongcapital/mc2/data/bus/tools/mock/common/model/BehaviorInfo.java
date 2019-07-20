package cn.rongcapital.mc2.data.bus.tools.mock.common.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Model;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class BehaviorInfo extends Model {

	private Long id;

	private String bid;

	private String preBid;

	private String nextBid;

	private String time;

	private Integer timeout;

	private String status;

	private JourneyInfo journeyInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPreBid() {
		return preBid;
	}

	public void setPreBid(String preBid) {
		this.preBid = preBid;
	}

	public String getNextBid() {
		return nextBid;
	}

	public void setNextBid(String nextBid) {
		this.nextBid = nextBid;
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

	public JourneyInfo getJourneyInfo() {
		return journeyInfo;
	}

	public void setJourneyInfo(JourneyInfo journeyInfo) {
		this.journeyInfo = journeyInfo;
	}

	@Override
	public void clear() {
		this.journeyInfo = null;
	}

}
