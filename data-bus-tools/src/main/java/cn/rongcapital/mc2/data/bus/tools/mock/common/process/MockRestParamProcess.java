package cn.rongcapital.mc2.data.bus.tools.mock.common.process;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import cn.rongcapital.mc2.data.common.camel.AbstractAsyncProcessor;

@Component
public class MockRestParamProcess extends AbstractAsyncProcessor {

	@SuppressWarnings("unchecked")
	@Override
	public void asyncProcess(Exchange exchange) {
		List<String> startTimes = new ArrayList<String>();
		Map<String, String> body = exchange.getIn().getBody(Map.class);
		String fromTime = body.get("fromTime");
		String toTime = body.get("toTime");
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = DateUtils.parseDate(fromTime, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss");
			toDate = DateUtils.parseDate(toTime, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (fromDate.before(toDate)) {
			long fromDays = DateUtils.getFragmentInDays(fromDate, Calendar.YEAR);
			long toDays = DateUtils.getFragmentInDays(toDate, Calendar.YEAR);
			long interval = toDays - fromDays + 1;
			for (int i = 0; i < interval; i++) {
				startTimes.add(DateFormatUtils.format(DateUtils.addDays(fromDate, i), "yyyy-MM-dd"));
			}
		}
		exchange.getIn().setBody(startTimes);
	}

}
