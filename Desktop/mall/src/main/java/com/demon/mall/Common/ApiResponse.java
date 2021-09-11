package com.demon.mall.Common;

import com.demon.mall.Exception.mallExceptionEunm;

import java.util.ArrayList;

public class ApiResponse<T> {
    private  String msg;
    private  Integer status;
    private T data;
    private static final   int Success_code=10000;
    private static final  String Success_msg="success";

    public ApiResponse(String msg, Integer status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    public ApiResponse() {
       this(Success_msg, Success_code);
    }
    //失败方法
    public static<T> ApiResponse<T> error(String msg, Integer status){
        return new ApiResponse(msg,status);
    }
   //成功方法
    public static <T>ApiResponse <T>success(){
        return  new ApiResponse();
    }
    //返回带data的方法
    public static <T> ApiResponse<T> success(T result){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(result);
        return  apiResponse;

    }
    public static <T> ApiResponse<T> error(mallExceptionEunm mallExceptionEunm){
        return new ApiResponse<>(mallExceptionEunm.getMsg(),mallExceptionEunm.getCode());
    }
    @Override
    public String toString() {
        return "ApiResponse{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
