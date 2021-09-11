package com.demon.mall.Exception;

import com.demon.mall.Common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
 private final Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);
 @ExceptionHandler(Exception.class)
 @ResponseBody
 public Object handleException(Exception e){
     log.info("system Exception:"+e);
     return ApiResponse.error(mallExceptionEunm.SYS_EXCEPTION);
 }
 @ExceptionHandler(mallException.class)
    @ResponseBody
    public Object handlemallException(mallException e){
     log.info("mallException:",e);
     return ApiResponse.error(e.getMsg(),e.getCode());
    }
}
