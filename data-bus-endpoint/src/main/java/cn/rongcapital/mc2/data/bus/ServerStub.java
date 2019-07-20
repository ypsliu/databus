package cn.rongcapital.mc2.data.bus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.rongcapital.graeae.endpoint.bridge.ServiceBridgeStub;
import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.CommonStub;
import cn.rongcapital.mc2.data.bus.protobuf.DataBusService;
import cn.rongcapital.mc2.data.bus.protobuf.VoidResponse;
import io.grpc.ServerCallHandler;
import io.grpc.ServerMethodDefinition;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;

public class ServerStub implements ServiceBridgeStub<ServerMethodDefinition<?, ?>> {

  private DataBusService dataBusService;

  public ServerStub(DataBusService dataBusService) {
    this.dataBusService = dataBusService;
  }

  @Override
  public String getServiceName() {
      return CommonStub.SERVICE_NAME;
  }

  @Override
  public Collection<ServerMethodDefinition<?, ?>> getServerMethodDefinition() {
      List<ServerMethodDefinition<?, ?>> serverMethodDefinitions = new ArrayList<>();
    ServerCallHandler<BehaviorMessageRequest, VoidResponse> serverCallHandler0 = 
        ServerCalls.asyncUnaryCall(
    new MethodHandlers<>(dataBusService, CommonStub.METHOD_PUSH_BEHAVIOR_ID));
    serverMethodDefinitions.add(ServerMethodDefinition.create(
        CommonStub.METHOD_PUSH_BEHAVIOR, serverCallHandler0));
    return serverMethodDefinitions;
  }

    @SuppressWarnings("unchecked")
    private class MethodHandlers<Req, Resp> implements
        ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>,
        ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final DataBusService serviceImpl;
        private final int methodId;
        public MethodHandlers(DataBusService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        public void invoke(Req request, StreamObserver<Resp> responseObserver) {
            switch(this.methodId) {
                case CommonStub.METHOD_PUSH_BEHAVIOR_ID:
                    VoidResponse reply0;
                    reply0 = this.serviceImpl.pushBehavior((BehaviorMessageRequest)request);
                    ((StreamObserver<VoidResponse>)responseObserver).onNext(reply0);
                    responseObserver.onCompleted();
                    return;
                default:
                    throw new AssertionError();
            }
        }

        public StreamObserver<Req> invoke(StreamObserver<Resp> responseObserver) {
            switch(this.methodId) {
                default:
                    throw new AssertionError();
           }
        }
      }
    }
