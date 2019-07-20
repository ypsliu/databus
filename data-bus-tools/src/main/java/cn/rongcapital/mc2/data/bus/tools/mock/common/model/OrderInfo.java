package cn.rongcapital.mc2.data.bus.tools.mock.common.model;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.Model;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class OrderInfo extends Model {

	// 商品的guid
	private String topOrderGuid;

	// 商品的最小库存单位Sku的id.可以通过taobao.item.sku.get获取详细的Sku信息
	private Integer skuId;

	// SKU的值
	// 如：机身颜色:黑色; 手机套餐:官方标配
	private String skuPropertiesName;

	// 套餐的值
	// 如：M8原装电池:便携支架:M8专用座充:莫凡保护袋
	private String itemMealName;

	// 购买数量
	// 取值范围:大于零的整数
	private Integer num;

	// 商品标题
	private String title;

	// 商品价格
	// 精确到2位小数, 单位:元; 如:200.07,表示:200元7分
	private Double price;

	// 交易类型
	private String type;

	// 退款状态
	private String refundStatus;

	// 子订单编号
	private String oid;

	// 应付金额
	// 精确到2位小数, 单位:元; 如:200.07,表示:200元7分
	private Double totalFee;

	// 买家实付金额
	// 精确到2位小数, 单位:元; 如:200.07, 表示:200元7分
	private Double payment;

	// 系统优惠金额
	// 精确到2位小数, 单位:元; 如:200.07, 表示:200元7分
	private Double discountFee;

	// 系统优惠金额
	// 精确到2位小数, 单位:元; 如:200.07, 表示:200元7分
	private Double adjustFee;

	// 订单状态
	private String status;

	// 买家是否已评价
	private Integer buyerRate;

	// 卖家是否已评价
	private Integer sellerRate;

	// 退款中的退款ID
	private Integer refundId;

	// 商品数字ID
	private Integer numIid;

	// 交易创建时间
	private Date created;

	// 交易修改时间
	private Date modified;

	private Date rxInsertTime;

	private Date rxUpdateTime;

	public String getTopOrderGuid() {
		return topOrderGuid;
	}

	public void setTopOrderGuid(String topOrderGuid) {
		this.topOrderGuid = topOrderGuid;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getSkuPropertiesName() {
		return skuPropertiesName;
	}

	public void setSkuPropertiesName(String skuPropertiesName) {
		this.skuPropertiesName = skuPropertiesName;
	}

	public String getItemMealName() {
		return itemMealName;
	}

	public void setItemMealName(String itemMealName) {
		this.itemMealName = itemMealName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}

	public Double getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(Double adjustFee) {
		this.adjustFee = adjustFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}

	public Integer getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Integer sellerRate) {
		this.sellerRate = sellerRate;
	}

	public Integer getRefundId() {
		return refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	public Integer getNumIid() {
		return numIid;
	}

	public void setNumIid(Integer numIid) {
		this.numIid = numIid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getRxInsertTime() {
		return rxInsertTime;
	}

	public void setRxInsertTime(Date rxInsertTime) {
		this.rxInsertTime = rxInsertTime;
	}

	public Date getRxUpdateTime() {
		return rxUpdateTime;
	}

	public void setRxUpdateTime(Date rxUpdateTime) {
		this.rxUpdateTime = rxUpdateTime;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

}
