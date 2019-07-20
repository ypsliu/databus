package cn.rongcapital.mc2.data.bus.consts;

public class TradeStatus {

	// WAIT_BUYER_PAY：等待买家付款
	public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

	// WAIT_SELLER_SEND_GOODS：等待卖家发货
	public static final String WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";

	// SELLER_CONSIGNED_PART：卖家部分发货
	public static final String SELLER_CONSIGNED_PART = "SELLER_CONSIGNED_PART";

	// WAIT_BUYER_CONFIRM_GOODS：等待买家确认收货
	public static final String WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";

	// TRADE_BUYER_SIGNED：买家已签收（货到付款专用）
	public static final String TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";

	// TRADE_FINISHED：交易成功
	public static final String TRADE_FINISHED = "TRADE_FINISHED";

	// TRADE_CLOSED：交易关闭
	public static final String TRADE_CLOSED = "TRADE_CLOSED";

	// TRADE_CLOSED_BY_TAOBAO：交易被淘宝关闭
	public static final String TRADE_CLOSED_BY_TAOBAO = "TRADE_CLOSED_BY_TAOBAO";

	// TRADE_NO_CREATE_PAY：没有创建外部交易（支付宝交易）
	public static final String TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";

	// WAIT_PRE_AUTH_CONFIRM：余额宝0元购合约中
	public static final String WAIT_PRE_AUTH_CONFIRM = "WAIT_PRE_AUTH_CONFIRM";

	// PAY_PENDING：外卡支付付款确认中
	public static final String PAY_PENDING = "PAY_PENDING";

	// ALL_WAIT_PAY：所有买家未付款的交易（包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY）
	public static final String ALL_WAIT_PAY = "ALL_WAIT_PAY";

	// ALL_CLOSED：所有关闭的交易（包含：TRADE_CLOSED、TRADE_CLOSED_BY_TAOBAO）
	public static final String ALL_CLOSED = "ALL_CLOSED";

}
