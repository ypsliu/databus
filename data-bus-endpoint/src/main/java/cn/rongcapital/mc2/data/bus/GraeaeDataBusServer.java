package cn.rongcapital.mc2.data.bus;

import cn.rongcapital.graeae.endpoint.AbstractGrpcServerEndpoint;
import cn.rongcapital.graeae.endpoint.annotation.GraeaeServer;
import cn.rongcapital.graeae.endpoint.bridge.ServiceBridgeStub;
import cn.rongcapital.mc2.data.bus.protobuf.DataBusService;
import io.grpc.ServerMethodDefinition;

@GraeaeServer
public class GraeaeDataBusServer extends AbstractGrpcServerEndpoint<DataBusService> {

  public GraeaeDataBusServer() {}

  public GraeaeDataBusServer(DataBusService dataBusService) {
      this.setService(dataBusService);
      this.start();
  }

  @Override
  protected ServiceBridgeStub<ServerMethodDefinition<?, ?>> loadStub(DataBusService service) {
      return new ServerStub(service);
  }
}

