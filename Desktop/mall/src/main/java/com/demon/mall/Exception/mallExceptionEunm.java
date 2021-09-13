package com.demon.mall.Exception;

import org.springframework.stereotype.Component;


public enum mallExceptionEunm {
    NEED_USERNAE(10001, "用户名不能为空"),
    NEED_USER(10002, "用户已经存在"),
    NEED_PASSWORD(10004, "密码不能为空"),
    PASSWORD_SHORT(10005, "密码不能为小于8位"),
    SYS_EXCEPTION(20000, "系统异常"),
    PWD_FAIL(10006, "密码错误"),
    UPDATE_FAIL(10007, "更新错误请重新再试"),
    NEED_LOGIN(10008, "需要登陆"),
    NEED_ADMIN(10009,"需要管理员权限"),
    CATAGORY_ISHAVE(10010,"Category已经存在"),
    INSERTCATE_FAIL(10011,"插入Category失败"),
    PARA_NULL(10012,"参数为空"),
    CATEGORY_ISNOTFOUND(10013,"需要的商品不存在"),
    DELETE_FAIL(10014,"删除失败"),
    INSERT_FAIL(10003, "插入用户失败，请重新再试");
    Integer code;
    String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    mallExceptionEunm(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
