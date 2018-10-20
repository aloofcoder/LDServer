package net.le.baseframe.exception;

public class AppControllerException extends RuntimeException {
    public AppControllerException() {
        super();
    }

    public AppControllerException(String message) {
        super(message);
    }

    public AppControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppControllerException(Throwable cause) {
        super(cause);
    }

    public AppControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
