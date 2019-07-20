package cn.rongcapital.mc2.data.bus.tools.mock.common;

import java.util.List;

public interface MockPool<T> {

	T acquire();

	List<T> acquires();

	List<T> afreshs();

	void release(T obj);

}
