package cn.rongcapital.mc2.data.bus.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyEdge;
import cn.rongcapital.mc2.data.bus.tools.mock.common.domain.JourneyNode;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyEdgeRepository;
import cn.rongcapital.mc2.data.bus.tools.mock.common.repository.JourneyNodeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {

	@Autowired
	private JourneyNodeRepository journeyNodeRepository;

	@Autowired
	private JourneyEdgeRepository journeyEdgeRepository;

	@Test
	public void test() {
		/*buildNode("1", "浏览商品", 0, "ON_START");
		buildNode("2", "添加购物车", 0, "ON_START");
		buildNode("4", "下单", 1, "ON_PROCESS");
		buildNode("11", "取消订单", 2, "ON_BREAK");
		buildNode("6", "支付", 2, "ON_PROCESS");
		buildNode("7", "发货", 3, "ON_PROCESS");
		buildNode("10", "支付后退款", 3, "ON_PROCESS");
		buildNode("9", "收货", 4, "ON_PROCESS");
		buildNode("14", "评价", 5, "ON_END");

		buildEdge("1", "2", 2);
		buildEdge("1", "4", 1);
		buildEdge("2", "4", 3);
		buildEdge("4", "11", 1);
		buildEdge("4", "6", 1);
		buildEdge("6", "11", 1);
		buildEdge("6", "7", 3);
		buildEdge("6", "10", 1);
		buildEdge("7", "9", 7);
		buildEdge("9", "14", 2);*/
	}

	void buildNode(String bid, String name, int index, String type) {
		JourneyNode node = journeyNodeRepository.findOneByBid(bid);
		if (null == node) {
			node = new JourneyNode();
			node.setBid(bid);
			node.setIndex(index);
			node.setName(name);
			node.setType(type);
			journeyNodeRepository.save(node);
		}
	}

	void buildEdge(String inBid, String outBid, int timeout) {
		JourneyEdge edge = journeyEdgeRepository.findOneByInNodeBidAndOutNodeBid(inBid, outBid);
		if (null == edge) {
			edge = new JourneyEdge();
			JourneyNode inNode = journeyNodeRepository.findOneByBid(inBid);
			JourneyNode outNode = journeyNodeRepository.findOneByBid(outBid);
			edge.setInNode(inNode);
			edge.setOutNode(outNode);
			edge.setTimeout(timeout);
			journeyEdgeRepository.save(edge);
		}
	}

}
