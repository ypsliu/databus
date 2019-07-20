package cn.rongcapital.mc2.data.bus.protobuf;

import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;

import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.VoidResponse;

public class CommonStub {

  public static final String SERVICE_NAME = "DataBus";

  public static final int METHOD_PUSH_BEHAVIOR_ID  = 0;

  public static final MethodDescriptor<BehaviorMessageRequest,
      VoidResponse> METHOD_PUSH_BEHAVIOR = MethodDescriptor.create(
      MethodDescriptor.MethodType.UNARY,
      MethodDescriptor.generateFullMethodName(CommonStub.SERVICE_NAME, "pushBehavior"),
      ProtoUtils.marshaller(BehaviorMessageRequest.getDefaultInstance()),
      ProtoUtils.marshaller(VoidResponse.getDefaultInstance()));

}
