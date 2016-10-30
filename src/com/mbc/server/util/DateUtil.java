package com.mbc.server.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date now() {
        return new Date();
    }

    public static Date addDays(Date date, int value) {
        return adjustDate(date, Calendar.DAY_OF_MONTH, value);
    }

    public static Date addMilliseconds(Date date, int value) {
        return adjustDate(date, Calendar.MILLISECOND, value);
    }

    public static Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static Date adjustDate(Date date, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    public static String toTimeStampString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy-dd_HH_mm_ss_SSS");
        return simpleDateFormat.format(date);
    }

    public static String convertDateToString(Date date, String dateFormat) {
        if (!ObjectUtil.isNull(date) && !ObjectUtil.isNull(dateFormat) && !dateFormat.equals("")) {
            DateFormat df = new SimpleDateFormat(dateFormat);
            return df.format(date);
        }
        return "";
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return false;
        }
        if (date1 == null && date2 != null) {
            return false;
        }
        if (date1 != null && date2 == null) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        boolean isSameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

        return isSameDay;
    }

    public static Date parseDate(final String dateString, final SimpleDateFormat formatter) {
        if (StringUtil.isEmptyOrNull(dateString) || ObjectUtil.isNull(formatter))
            return null;

        try {
            return formatter.parse(dateString);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String formatDate(final Date date, final SimpleDateFormat formatter) {
        if (ObjectUtil.isNull(date) || ObjectUtil.isNull(formatter))
            return null;
        return formatter.format(date);
    }

    public static String formatDate(final Date date, final String formatter) {
        if (ObjectUtil.isNull(date) || StringUtil.isEmptyOrNull(formatter))
            return null;
        return new SimpleDateFormat(formatter).format(date);
    }

    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);

        return cal.getTime();
    }
	
}
