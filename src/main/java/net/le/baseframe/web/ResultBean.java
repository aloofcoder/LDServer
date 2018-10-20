package net.le.baseframe.web;

import net.le.baseframe.util.StatusEnum;

import java.io.Serializable;

public class ResultBean implements Serializable {

    private static final long serialVersionUID = 3375269590958028609L;
    private int status;

    private String message;

    private Object data;

    public ResultBean() {
        this.status = 0;
        this.message = "SUCCESS";
    }

    public ResultBean(Object data) {
        this();
        this.data = data;
    }

    public ResultBean(Throwable t){
        this.status = 1;
        this.message = t.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
