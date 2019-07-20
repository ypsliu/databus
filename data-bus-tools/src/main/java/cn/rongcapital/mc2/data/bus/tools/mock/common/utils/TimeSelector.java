package cn.rongcapital.mc2.data.bus.tools.mock.common.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class TimeSelector {

	public String select(String time) {
		Date date = null;
		try {
			date = DateUtils.parseDate(time, "yyyy-MM-dd");
			date = DateUtils.addHours(date, RandomUtils.nextInt(0, 24));
			date = DateUtils.addMinutes(date, RandomUtils.nextInt(0, 60));
			date = DateUtils.addSeconds(date, RandomUtils.nextInt(0, 60));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public String select(Date time, int timeout, int pn) {
		int coefficient = Math.round(timeout * 0.75f);
		int randomNum = RandomUtils.nextInt(0, (timeout + coefficient) * pn);
		Date date = DateUtils.addDays(time, randomNum);
		date = DateUtils.addHours(date, RandomUtils.nextInt(0, 24) * pn);
		date = DateUtils.addMinutes(date, RandomUtils.nextInt(0, 60) * pn);
		date = DateUtils.addSeconds(date, RandomUtils.nextInt(0, 60) * pn);
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public String select(String time, int timeout, int pn) {
		try {
			return select(DateUtils.parseDate(time, "yyyy-MM-dd HH:mm:ss"), timeout, pn);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String selectNotTimeout(Date time, int timeout, int pn) {
		int randomNum = RandomUtils.nextInt(0, timeout);
		Date date = DateUtils.addDays(time, randomNum * pn);
		date = DateUtils.addHours(date, RandomUtils.nextInt(0, 24) * pn);
		date = DateUtils.addMinutes(date, RandomUtils.nextInt(0, 60) * pn);
		date = DateUtils.addSeconds(date, RandomUtils.nextInt(0, 60) * pn);
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public String selectNotTimeout(String time, int timeout, int pn) {
		try {
			return selectNotTimeout(DateUtils.parseDate(time, "yyyy-MM-dd HH:mm:ss"), timeout, pn);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String selectTimeout(Date time, Integer timeout, int pn) {
		int coefficient = Math.round(timeout * 1.75f);
		int randomNum = RandomUtils.nextInt(timeout + 1, timeout + coefficient);
		Date date = DateUtils.addDays(time, randomNum * pn);
		date = DateUtils.addHours(date, RandomUtils.nextInt(0, 24) * pn);
		date = DateUtils.addMinutes(date, RandomUtils.nextInt(0, 60) * pn);
		date = DateUtils.addSeconds(date, RandomUtils.nextInt(0, 60) * pn);
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public String selectTimeout(String time, int timeout, int pn) {
		try {
			return selectTimeout(DateUtils.parseDate(time, "yyyy-MM-dd HH:mm:ss"), timeout, pn);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
