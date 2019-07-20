package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.bus.tools.mock.common.MockPool;
import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;

@DependsOn("beanContextUtils")
@Component
public class BuyerPool implements MockPool<BuyerInfo>, InitializingBean {

	final static List<BuyerInfo> BUYER_QUEUE = new ArrayList<BuyerInfo>();

	final static ReentrantLock ACQUIRE_LOCK = new ReentrantLock();

	@Value("${mock.buyer.pool.maxSize:1000}")
	private Integer maxSize;

	@Value("${mock.buyer.pool.maxAcquire:100}")
	private Integer maxAcquire;

	@Value("${mock.buyer.pool.maxAfresh:100}")
	private Integer maxAfresh;

	@Override
	public BuyerInfo acquire() {
		ACQUIRE_LOCK.lock();
		try {
			int r = RandomUtils.nextInt(0, BUYER_QUEUE.size());
			int i = 0;
			for (BuyerInfo buyerInfo : BUYER_QUEUE) {
				if (i == r) {
					return buyerInfo;
				}
				i++;
			}
		} finally {
			ACQUIRE_LOCK.unlock();
		}
		return null;
	}

	@Override
	public List<BuyerInfo> acquires() {
		ACQUIRE_LOCK.lock();
		Collections.shuffle(BUYER_QUEUE);
		try {
			return BUYER_QUEUE.subList(0, maxAcquire);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ACQUIRE_LOCK.unlock();
		}
		return null;
	}

	@Override
	public List<BuyerInfo> afreshs() {
		return BuyerFactory.getInstances(maxAfresh);
	}

	@Override
	public void release(BuyerInfo buyerInfo) {
	}

	public void releaseAll() {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<BuyerInfo> buyerInfoList = BuyerFactory.getInstances(maxSize);
		BUYER_QUEUE.addAll(buyerInfoList);
	}

}
