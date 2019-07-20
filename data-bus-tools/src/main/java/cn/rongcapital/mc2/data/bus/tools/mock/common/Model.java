package cn.rongcapital.mc2.data.bus.tools.mock.common;

public abstract class Model {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void accept(Mocker mocker) {
		mocker.mock(this);
	}

	public abstract void clear();

}
