package cn.rongcapital.mc2.data.bus;

import cn.rongcapital.graeae.endpoint.AbstractGrpcClientEndpoint;
import cn.rongcapital.graeae.endpoint.ClientEnvironment;
import cn.rongcapital.graeae.endpoint.GraeaeCallback;
import cn.rongcapital.graeae.endpoint.annotation.GraeaeClient;
import io.grpc.ManagedChannel;

import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.VoidResponse;

@GraeaeClient
public class GraeaeDataBusClient extends AbstractGrpcClientEndpoint<ClientStub> {

  public GraeaeDataBusClient() {}
  public GraeaeDataBusClient(boolean isStart) {
      if (isStart) {
          this.start();
      }
  }

  public VoidResponse pushBehavior(BehaviorMessageRequest request) {
      return this.getStub().pushBehaviorInBlock(request);
  }

  public void pushBehaviorAsync(BehaviorMessageRequest request, GraeaeCallback<VoidResponse> graeaeCallback) {
      this.getStub().pushBehaviorAsync(request, graeaeCallback);
  }

  @Override
  protected ClientStub loadStub(ManagedChannel managedChannel, ClientEnvironment env) {
      return ClientStub.newStub(managedChannel, env);
  }

}

