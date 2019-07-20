package cn.rongcapital.mc2.data.bus;

import cn.rongcapital.graeae.endpoint.ClientEnvironment;
import cn.rongcapital.graeae.endpoint.GraeaeCallback;
import cn.rongcapital.graeae.endpoint.faulttolerance.BizCallback;
import cn.rongcapital.graeae.endpoint.faulttolerance.HystrixFaultTolerance;
import io.grpc.ServerMethodDefinition;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.StreamObserver;

import cn.rongcapital.mc2.data.bus.protobuf.CommonStub;

import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.VoidResponse;

public class ClientStub extends AbstractStub<ClientStub> {

  private HystrixFaultTolerance faultTolerance;

  private ClientStub(Channel channel) {
    super(channel);
  }

  private ClientStub(Channel channel, CallOptions callOptions) {
    super(channel, callOptions);
  }

  private ClientStub(Channel channel, HystrixFaultTolerance faultTolerance) {
    this(channel);
    this.faultTolerance = faultTolerance;
  }

  public static ClientStub newStub(Channel channel, ClientEnvironment env) {
    return new ClientStub(channel, new HystrixFaultTolerance(env.getExecuteTimeout()));
  }

  @Override
  protected ClientStub build(Channel channel, CallOptions callOptions) {
    return new ClientStub(channel, callOptions);
  }

  public VoidResponse pushBehaviorInBlock(BehaviorMessageRequest request) {
      return faultTolerance.executeWithFaultTolerance(CommonStub.SERVICE_NAME, String.valueOf(CommonStub.METHOD_PUSH_BEHAVIOR_ID),
          new BizCallback<VoidResponse>() {
              @Override
              public VoidResponse call() {
                  return ClientCalls.blockingUnaryCall(ClientStub.this.getChannel(),
                      CommonStub.METHOD_PUSH_BEHAVIOR, ClientStub.this.getCallOptions(), request);
              }

              @Override
              public VoidResponse fallback() {
                  return VoidResponse.getDefaultInstance();
              }
          });
  }

  public void pushBehaviorAsync(BehaviorMessageRequest request, GraeaeCallback<VoidResponse> graeaeCallback) {
      StreamObserver<VoidResponse> responseObserver = new StreamObserver<VoidResponse>() {
          @Override
          public void onNext(VoidResponse value) {
              graeaeCallback.onResult(value);
          }

          @Override
          public void onError(Throwable t) {
              graeaeCallback.onError(t);
          }

          @Override
          public void onCompleted() {
          }

      };

      faultTolerance.executeWithFaultTolerance(CommonStub.SERVICE_NAME, String.valueOf(CommonStub.METHOD_PUSH_BEHAVIOR_ID),
          new BizCallback<Void>() {
              @Override
              public Void call() {
                  ClientCalls.asyncUnaryCall(ClientStub.this.getChannel().newCall(
                      CommonStub.METHOD_PUSH_BEHAVIOR,
                      ClientStub.this.getCallOptions()), request, responseObserver);
                  return null;
              }
              @Override
              public Void fallback() {
                  responseObserver.onNext(VoidResponse.getDefaultInstance());
                  return null;
              }
          });
              
  }

}

