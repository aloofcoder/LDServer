package net.le.baseframe.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UserNumberUtil {

    /**
     * 获取long类型用户编号
     * @return
     */
    public static synchronized long getNumber () {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateStr = sdf.format(cal.getTime());
        return Long.parseLong(dateStr);
    }

    /**
     * 获取有前缀的用户编号
     * @param preStr
     * @return
     */
    public static synchronized String getNumber (String preStr) {
        return preStr + "" + UserNumberUtil.getNumber();
    }

    public static void main(String[] args) {
        System.out.println(UserNumberUtil.getNumber());
        System.out.println(UserNumberUtil.getNumber("wx"));
    }

}
