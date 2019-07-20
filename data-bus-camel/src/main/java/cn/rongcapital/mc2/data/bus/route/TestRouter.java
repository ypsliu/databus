package cn.rongcapital.mc2.data.bus.route;

import cn.rongcapital.mc2.data.common.camel.NettyRestRouteBuilder;

/**
 * @Author:lhz
 * @Description:
 * @Date:22:32 2018-1-15
 */
public class TestRouter extends NettyRestRouteBuilder {

  String uri="/create";
  @Override
  public void configure() throws Exception {

    rest(uri).post().produces("application/json").consumes("application/json").route().threads().to("http4://data-bus-camel");
  }
}
