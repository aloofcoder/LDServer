package net.le.baseframe.util;

import net.le.baseframe.exception.AppServiceException;

public class CheckParamUtils {

    public static void isNull(Object obj, String message) {
        if (obj == null) {
            throw new AppServiceException(message);
        }
    }

    public static void countErr(int count, String message) {
        if (count == -1) {
            throw new AppServiceException(message);
        }
    }
}
