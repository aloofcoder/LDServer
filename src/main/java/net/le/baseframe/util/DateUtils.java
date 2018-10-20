package net.le.baseframe.util;

import lombok.extern.slf4j.Slf4j;
import net.le.baseframe.core.entity.Loan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static Date getNowMonthPre () {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取给定时间到现在的月份
     * @return
     */
    public static int spendMonth (long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        System.out.println(cal.getTime());
        int lastYear = cal.get(Calendar.YEAR);
        int lastMonth = cal.get(Calendar.MONTH);

        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH);

        System.out.println(nowMonth);
        System.out.println(lastMonth);
        // 超过的年份
        int spendYear = 0;
        int spendMonth = 0;
        if (nowYear > lastYear) {
            if (nowMonth >= lastMonth) {
                spendYear = nowYear - lastYear;
                spendMonth = nowMonth - lastMonth;
            } else {
                spendYear = nowYear - lastYear - 1;
                spendMonth = 12 + nowMonth - lastMonth;
            }
        } else if (nowYear == lastYear) {
            if (nowMonth > lastMonth) {
                spendMonth = nowMonth - lastMonth;
            }
        }
        int totalMonth = spendYear * 12 + spendMonth;
        return totalMonth;
    }

    public static long getNowTimeMillis () {
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis() / 1000;
    }


    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        log.debug(sdf.format(DateUtils.getNowMonthPre()));
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 0, 10, 15, 22, 33);
        System.out.println(DateUtils.spendMonth(cal.getTimeInMillis()));
    }
}
