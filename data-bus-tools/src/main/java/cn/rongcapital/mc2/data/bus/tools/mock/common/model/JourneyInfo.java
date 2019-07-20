package cn.rongcapital.mc2.data.bus.tools.mock.common.model;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Model;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class JourneyInfo extends Model {

	private String key;

	private BuyerInfo buyerInfo;

	private SellerInfo sellerInfo;

	private OrderInfo orderInfo;

	private String startBid;

	private String endBid;

	private List<BehaviorInfo> journeyPathList;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public BuyerInfo getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(BuyerInfo buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfo sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getStartBid() {
		return startBid;
	}

	public void setStartBid(String startBid) {
		this.startBid = startBid;
	}

	public String getEndBid() {
		return endBid;
	}

	public void setEndBid(String endBid) {
		this.endBid = endBid;
	}

	public List<BehaviorInfo> getJourneyPathList() {
		return journeyPathList;
	}

	public void setJourneyPathList(List<BehaviorInfo> journeyPathList) {
		this.journeyPathList = journeyPathList;
	}

	@Override
	public void clear() {
		this.buyerInfo = null;
		this.journeyPathList = null;
		this.orderInfo = null;
		this.sellerInfo = null;
	}

}
