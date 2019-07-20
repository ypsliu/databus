package cn.rongcapital.mc2.data.bus.tools.mock.common;

public interface Mocker<T extends Model> {

	void mock(T model);

}
