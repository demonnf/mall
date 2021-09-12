package com.demon.mall.Exception;

import com.demon.mall.Common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.info("system Exception:" + e);
        return ApiResponse.error(mallExceptionEunm.SYS_EXCEPTION);
    }

    @ExceptionHandler(mallException.class)
    @ResponseBody
    public Object handlemallException(mallException e) {
        log.info("mallException:", e);
        return ApiResponse.error(e.getMsg(), e.getCode());
    }
@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        return handleBindingResult(e.getBindingResult());
    }
    private ApiResponse handleBindingResult(BindingResult bindingResult) {
        List<String> list = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objError : allErrors
            ) {
                String defaultMessage = objError.getDefaultMessage();
                list.add(defaultMessage);
            }
            if(list==null){
                return ApiResponse.error(mallExceptionEunm.PARA_NULL);
            }
        }
        return ApiResponse.error(list.toString(), mallExceptionEunm.PARA_NULL.getCode());
    }
}
