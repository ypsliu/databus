package cn.rongcapital.mc2.data.bus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.rongcapital.mc2.data.bus.App;
import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.DataBusService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class DataBusServiceTest {

	@Autowired
	private DataBusService dataBusService;

	@Test
	public void test() {
		BehaviorMessageRequest req = BehaviorMessageRequest.newBuilder().setTenantId("1").setMessage("{\"abc\":123}").build();
		dataBusService.pushBehavior(req);
	}

}
