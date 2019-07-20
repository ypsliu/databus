package cn.rongcapital.mc2.data.bus.tools.mock.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.BuyerPool;

@Component
public class BuyerService {

	@Autowired
	private BuyerPool buyerPool;

	public BuyerInfo getBuyer() {
		return buyerPool.acquire();
	}

	public List<BuyerInfo> getBuyerQueue() {
		return buyerPool.acquires();
	}

	public List<BuyerInfo> getNewBuyerQueue() {
		return buyerPool.afreshs();
	}

}
