package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import java.util.ArrayList;
import java.util.List;

import cn.rongcapital.mc2.data.bus.tools.mock.common.mocker.BuyerMocker;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.common.utils.BeanContextUtils;

public class BuyerFactory {

	public static BuyerInfo getInstance() {
		BuyerMocker mocker = BeanContextUtils.context().getBean(BuyerMocker.class);
		BuyerInfo buyerInfo = BeanContextUtils.context().getBean(BuyerInfo.class);
		buyerInfo.accept(mocker);
		return buyerInfo;
	}

	public static List<BuyerInfo> getInstances(int count) {
		BuyerMocker mocker = BeanContextUtils.context().getBean(BuyerMocker.class);
		List<BuyerInfo> buyerInfos = new ArrayList<BuyerInfo>(count);
		for (int i = 0; i < count; i++) {
			BuyerInfo buyerInfo = BeanContextUtils.context().getBean(BuyerInfo.class);
			buyerInfo.accept(mocker);
			buyerInfos.add(buyerInfo);
		}
		return buyerInfos;
	}

}
