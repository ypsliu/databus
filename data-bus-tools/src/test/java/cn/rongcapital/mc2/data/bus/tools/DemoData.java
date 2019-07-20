package cn.rongcapital.mc2.data.bus.tools;

import cn.rongcapital.mc2.data.bus.tools.mock.common.model.BuyerInfo;
import cn.rongcapital.mc2.data.bus.tools.mock.common.utils.BuyerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:lhz
 * @Description:
 * @Date:14:13 2018-1-15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoData {


  @Test
  public void createData()
  {
    for(int i=1;i<10;i++) {
      BuyerInfo bifo = BuyerFactory.getInstance();
      System.out.println("uuid : " + bifo.toString());
    }
  }


}
