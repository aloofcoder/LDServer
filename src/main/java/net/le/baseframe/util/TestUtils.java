package net.le.baseframe.util;

import net.le.baseframe.web.ResultBean;

public class TestUtils {
    public static void main(String[] args) {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(System.getProperty("sun.jnu.encoding"));
        System.out.println(new ResultBean());
        System.out.println(new ResultBean());
    }
}
