package cn.rongcapital.mc2.data.bus.protobuf;

import cn.rongcapital.mc2.data.bus.protobuf.BehaviorMessageRequest;
import cn.rongcapital.mc2.data.bus.protobuf.VoidResponse;

public interface DataBusService {

  VoidResponse pushBehavior(BehaviorMessageRequest req);

}

