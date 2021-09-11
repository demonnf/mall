package com.demon.mall.Exception;

public class mallException extends RuntimeException{
    private final Integer code;
    private final String msg;

    public mallException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public mallException(mallExceptionEunm mallExceptionEunm){
      this(mallExceptionEunm.getCode(),mallExceptionEunm.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
