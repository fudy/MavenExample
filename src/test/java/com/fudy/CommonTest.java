package com.fudy;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class CommonTest {

	@Test
	public void testTime() {
		String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSSZZ");
		System.out.println(format);
		try {
			Date date = DateUtils.parseDate(format, "yyyy-MM-dd HH:mm:ss.SSSZZ");
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTime2() throws Exception {
		long begin = System.currentTimeMillis();
		Date date = DateUtils.parseDate("2013-10-27T13:00:00.325+00:00", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		Timestamp tz = new Timestamp(date.getTime());
		System.out.println(date);
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
	
	@Test
	public void testTime3() throws ParseException {
		String timestamp = "2013-10-27T13:00:00.123456Z";
		String date = timestamp.replaceAll("\\.\\d+", "");	// get date and time
		System.out.println(date);
		Matcher matcher = Pattern.compile("\\.(\\d+)").matcher(timestamp);	// get nanoseconds
		int nanos = 0;
		if (matcher.find()) {
			String group = matcher.group(1);
			String nanosStr = StringUtils.rightPad(group, 9, '0');
			nanos = Integer.parseInt(nanosStr);
		}
		Date d = DateUtils.parseDate(date, "yyyy-MM-dd'T'HH:mm:ssXXX");
		Timestamp ts = new Timestamp(d.getTime());
		ts.setNanos(nanos);
		System.out.println(ts);
		
	}
	
	@Test
	public void testTime4() throws Exception {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String nanoseconds = "." + ts.getNanos()/1000;
		String tsStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
		String result = tsStr.replaceAll("\\.\\d+", nanoseconds);
		System.out.println(result);
	}
	
	@Test
	public void testTime5() {
		TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
		System.out.print(getCurrentTimezoneOffset(tz) + "\t");
		System.out.println(tz.getID());
	}
	
	public static String getCurrentTimezoneOffset(TimeZone tz) {
	    Calendar cal = GregorianCalendar.getInstance(tz);
	    int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

	    String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
	    offset = (offsetInMillis >= 0 ? "+" : "-") + offset;

	    return offset;
	} 


	
	
}
