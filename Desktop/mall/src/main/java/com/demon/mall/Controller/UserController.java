package com.demon.mall.Controller;

import com.demon.mall.Common.ApiResponse;
import com.demon.mall.Common.Encryption;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Service.UserService;
import com.demon.mall.model.pojo.User;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {
    @Autowired
    UserService userService;
//注册用户
    @ResponseBody
    @PostMapping("/register")
    public ApiResponse register(@RequestParam("username") String username, @RequestParam("password") String password) throws NoSuchAlgorithmException {
        //如果用户名为空
        if (StringUtils.isNullOrEmpty(username)) {
            return ApiResponse.error(mallExceptionEunm.NEED_USERNAE);
        }
        //如果密码为空
        if (StringUtils.isNullOrEmpty(password)) {
            return ApiResponse.error(mallExceptionEunm.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiResponse.error(mallExceptionEunm.PASSWORD_SHORT);
        }
        userService.register(username, password);
        return ApiResponse.success();

    }
//用户登陆
    @ResponseBody
    @PostMapping("/login")
    public ApiResponse login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession) {

        if (StringUtils.isNullOrEmpty(username)) {
            return ApiResponse.error(mallExceptionEunm.NEED_USERNAE);
        }
        //如果密码为空
        if (StringUtils.isNullOrEmpty(password)) {
            return ApiResponse.error(mallExceptionEunm.NEED_PASSWORD);
        }
        User user = userService.login(username, password);
        user.setPassword(null);
        httpSession.setAttribute(Encryption.User_login, user);
        return ApiResponse.success(user);
    }
//更新用户签名
    @ResponseBody
    @PostMapping("User/update")
    public ApiResponse update(@RequestParam("signature") String signature, HttpSession httpSession) {
        User user_login = (User) httpSession.getAttribute(Encryption.User_login);
        if (user_login == null) {
            return ApiResponse.error(mallExceptionEunm.NEED_LOGIN);
        }
        User user=new User() ;
        user.setId(user_login.getId());
        user.setPersonalizedSignature(signature);
        userService.updateimformation(user);
        return ApiResponse.success();
    }
    //退出登陆
    @ResponseBody
    @PostMapping("User/logout")
    public ApiResponse logout(HttpSession httpSession){
        httpSession.removeAttribute(Encryption.User_login);
        return ApiResponse.success();
    }
    @PostMapping("User/Admin")
    @ResponseBody
    public ApiResponse Adminlogin(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession httpSession){

        //如果用户名为空
        if (StringUtils.isNullOrEmpty(username)) {
            return ApiResponse.error(mallExceptionEunm.NEED_USERNAE);
        }
        //如果密码为空
        if (StringUtils.isNullOrEmpty(password)) {
            return ApiResponse.error(mallExceptionEunm.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiResponse.error(mallExceptionEunm.PASSWORD_SHORT);
        }
        User user=userService.login(username, password);
        boolean b = userService.CheckAdminRole(user);
        if (b){
            user.setPassword(null);
            httpSession.setAttribute(Encryption.User_login,user );
            return ApiResponse.success();
        }
        return ApiResponse.error(mallExceptionEunm.NEED_ADMIN);
    }
}
