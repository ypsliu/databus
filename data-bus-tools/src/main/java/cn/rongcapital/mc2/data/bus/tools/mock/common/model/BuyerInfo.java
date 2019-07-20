package cn.rongcapital.mc2.data.bus.tools.mock.common.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Model;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class BuyerInfo extends Model {

	private String userId;

	private String buyerNick;

	private String buyerEmail;

	private String receiverName;

	private String receiverMobile;

	private String receiverState;

	private String receiverCity;

	private String receiverDistrict;

	private Integer shoppingDesire;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public Integer getShoppingDesire() {
		return shoppingDesire;
	}

	public void setShoppingDesire(Integer shoppingDesire) {
		this.shoppingDesire = shoppingDesire;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

}
