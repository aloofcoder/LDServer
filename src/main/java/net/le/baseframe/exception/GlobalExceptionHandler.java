package net.le.baseframe.exception;

import net.le.baseframe.web.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 全局捕获系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultBean handleRunTimeException(RuntimeException e) {
        System.out.println("系统异常");
        e.printStackTrace();
        return new ResultBean(e);
    }

    @ExceptionHandler(AppServiceException.class)
    @ResponseBody
    public ResultBean handleAppServiceException(RuntimeException e) {
        e.printStackTrace();
        return new ResultBean(e);
    }
    @ExceptionHandler(AppControllerException.class)
    @ResponseBody
    public ResultBean handleAppControllerException(RuntimeException e) {
        return new ResultBean(e.getMessage());
    }
}
