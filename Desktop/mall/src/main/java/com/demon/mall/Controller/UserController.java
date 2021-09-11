package com.demon.mall.Controller;

import com.demon.mall.Common.ApiResponse;
import com.demon.mall.Common.Encryption;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Service.UserService;
import com.demon.mall.model.pojo.User;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @ResponseBody
    @PostMapping ("/register")
    public ApiResponse register(@RequestParam("username") String username,@RequestParam("password") String password) throws NoSuchAlgorithmException {
        //如果用户名为空
        if(StringUtils.isNullOrEmpty(username)){
            return ApiResponse.error(mallExceptionEunm.NEED_USERNAE);
        }
        //如果密码为空
        if(StringUtils.isNullOrEmpty(password)){
            return ApiResponse.error(mallExceptionEunm.NEED_PASSWORD);
        }
        if(password.length() <8){
            return ApiResponse.error(mallExceptionEunm.PASSWORD_SHORT);
        }
        userService.register(username, password);
        return ApiResponse.success();

    }
    @ResponseBody
    @PostMapping("/login")
    public ApiResponse login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession){

        if(StringUtils.isNullOrEmpty(username)){
            return ApiResponse.error(mallExceptionEunm.NEED_USERNAE);
        }
        //如果密码为空
        if(StringUtils.isNullOrEmpty(password)){
            return ApiResponse.error(mallExceptionEunm.NEED_PASSWORD);
        }
        User user = userService.login(username, password);
        user.setPassword(null);
        httpSession.setAttribute(Encryption.User_login,user);
       return ApiResponse.success(user);
    }
}
