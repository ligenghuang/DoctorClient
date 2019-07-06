package com.example.doctorclient.util.data;

import android.support.annotation.NonNull;

import com.lgh.huanglib.util.L;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 动态时间格式化
 * Created by SCWANG on 2017/6/17.
 */

public class DynamicTimeFormat extends SimpleDateFormat {

    private static Locale locale = Locale.CHINA;
    private static String weeks[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static String moments[] = {"中午", "凌晨", "早上", "下午", "晚上"};

    private String mFormat = "%s";

    public DynamicTimeFormat() {
        this("%s", "yyyy年", "M月d日", "HH:mm");
    }

    public DynamicTimeFormat(String format) {
        this();
        this.mFormat = format;
    }

    public DynamicTimeFormat(String yearFormat, String dateFormat, String timeFormat) {
        super(String.format(locale, "%s %s %s", yearFormat, dateFormat, timeFormat), locale);
    }

    public DynamicTimeFormat(String format, String yearFormat, String dateFormat, String timeFormat) {
        this(yearFormat, dateFormat, timeFormat);
        this.mFormat = format;
    }

    @Override
    public StringBuffer format(@NonNull Date date, @NonNull StringBuffer toAppendTo, @NonNull FieldPosition pos) {
        toAppendTo = super.format(date, toAppendTo, pos);

        Calendar otherCalendar = calendar;
        Calendar todayCalendar = Calendar.getInstance();

        int hour = otherCalendar.get(Calendar.HOUR_OF_DAY);

        String[] times = toAppendTo.toString().split(" ");
        String moment = hour == 12 ? moments[0] : moments[hour / 6 + 1];
        String timeFormat = moment + " " + times[2];
        String dateFormat = times[1] + " " + timeFormat;
        String yearFormat = times[0] + dateFormat;
        toAppendTo.delete(0, toAppendTo.length());

        boolean yearTemp = todayCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
        if (yearTemp) {
            int todayMonth = todayCalendar.get(Calendar.MONTH);
            int otherMonth = otherCalendar.get(Calendar.MONTH);
            if (todayMonth == otherMonth) {//表示是同一个月
                int temp = todayCalendar.get(Calendar.DATE) - otherCalendar.get(Calendar.DATE);
                switch (temp) {
                    case 0:
                        toAppendTo.append(timeFormat);
                        break;
                    case 1:
                        toAppendTo.append("昨天 ");
                        toAppendTo.append(timeFormat);
                        break;
                    case 2:
                        toAppendTo.append("前天 ");
                        toAppendTo.append(timeFormat);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        int dayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);
                        int todayOfMonth = todayCalendar.get(Calendar.WEEK_OF_MONTH);
                        if (dayOfMonth == todayOfMonth) {//表示是同一周
                            int dayOfWeek = otherCalendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek != 1) {//判断当前是不是星期日     如想显示为：周日 12:09 可去掉此判断
                                toAppendTo.append(weeks[otherCalendar.get(Calendar.DAY_OF_WEEK) - 1]);
                                toAppendTo.append(' ');
                                toAppendTo.append(timeFormat);
                            } else {
                                toAppendTo.append(dateFormat);
                            }
                        } else {
                            toAppendTo.append(dateFormat);
                        }
                        break;
                    default:
                        toAppendTo.append(dateFormat);
                        break;
                }
            } else {
                toAppendTo.append(dateFormat);
            }
        } else {
            toAppendTo.append(yearFormat);
        }

        int length = toAppendTo.length();
        toAppendTo.append(String.format(locale, mFormat, toAppendTo.toString()));
        toAppendTo.delete(0, length);
        return toAppendTo;
    }


    public static String LongToString(long time) {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        return format2.format(new Date(time));
    }

    public static String LongToString2(long time) {
        SimpleDateFormat format2 = new SimpleDateFormat("MM月dd日 HH:mm");
        return format2.format(new Date(time));
    }

    public static String LongToString3(long time) {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
        return format2.format(new Date(time));
    }

    public static String LongToString4(long time) {
        SimpleDateFormat format2 = new SimpleDateFormat("MM月dd日 HH:mm:ss");
        return format2.format(new Date(time));
    }

    public static String LongToString5(long time) {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format2.format(new Date(time));
    }

    /**
     * 获取今天日期
     *
     * @return
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }

    /**
     * 获取日期
     *
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取今天周几
     *
     * @return
     */
    public static int getPosition() {
        Calendar calendar = Calendar.getInstance();
        int Position = 0;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                Position = 6;
                break;
            case Calendar.MONDAY:
                Position = 0;
                break;
            case Calendar.TUESDAY:
                Position = 1;
                break;
            case Calendar.WEDNESDAY:
                Position = 2;
                break;
            case Calendar.THURSDAY:
                Position = 3;
                break;
            case Calendar.FRIDAY:
                Position = 4;
                break;
            case Calendar.SATURDAY:
                Position = 5;
                break;
            default:
                break;
        }
        return Position;
    }


    /**
     * 根据毫秒时间戳来格式化字符串
     * 今天显示今天、昨天显示昨天、前天显示前天.
     * 早于前天的显示具体年-月-日，如2017-06-12；
     *
     * @param timeStamp 毫秒值
     * @return 今天 昨天 前天 或者 yyyy-MM-dd HH:mm:ss类型字符串
     */
    public static String format(long timeStamp) {
        long curTimeMillis = System.currentTimeMillis();
        Date curDate = new Date(curTimeMillis);
        int todayHoursSeconds = curDate.getHours() * 60 * 60;
        int todayMinutesSeconds = curDate.getMinutes() * 60;
        int todaySeconds = curDate.getSeconds();
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm");
        L.e("lgh_time","time0 = "+ timeStamp);
        L.e("lgh_time","time = "+ sdf1.format(new Date(timeStamp)));
        if (timeStamp >= todayStartMillis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "今天  " + sdf.format(new Date(timeStamp));
        }
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMilis = todayStartMillis - oneDayMillis;
        if (timeStamp >= yesterdayStartMilis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "昨天  " + sdf.format(new Date(timeStamp));
        }
        long yesterdayBeforeStartMilis = yesterdayStartMilis - oneDayMillis;
        if (timeStamp >= yesterdayBeforeStartMilis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "前天  " + sdf.format(new Date(timeStamp));
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(new Date(timeStamp));
    }

    public static Date stringToDate(String strTime, String formatType) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static long stringToLong(String strTime) throws ParseException {
        Date date = stringToDate(strTime, "yyyy-MM-dd HH:mm:ss");
        // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date);
            // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * 获取今天日期时间戳
     *
     * @return
     */
    public static String getTimestamp () {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().getTime()+"";
    }


}
