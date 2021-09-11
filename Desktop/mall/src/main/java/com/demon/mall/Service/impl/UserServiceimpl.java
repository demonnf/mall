package com.demon.mall.Service.impl;

import com.demon.mall.Common.ApiResponse;
import com.demon.mall.Exception.mallException;
import com.demon.mall.Exception.mallExceptionEunm;
import com.demon.mall.Service.UserService;
import com.demon.mall.Util.MD5Util;
import com.demon.mall.model.dao.UserMapper;
import com.demon.mall.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void register(String username, String password) throws mallException, NoSuchAlgorithmException {
        User user = userMapper.SelectByName(username);
        //用户已经存在
        if(user!=null){
            throw new mallException(mallExceptionEunm.NEED_USER);
        }
        //存入用户
        User userresult= new User();
        userresult.setUsername(username);
        String md5 = MD5Util.getMD5(password);
        userresult.setPassword(md5);
        int i = userMapper.insertSelective(userresult);
       if(i==0){
           throw new mallException(mallExceptionEunm.INSERT_FAIL);
       }

    }

    @Override
    public User login(String username, String password) throws mallException{
        String md5string=null;
        try {
            md5string=MD5Util.getMD5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.SelectLogin(username, md5string);
        if(user==null){
            throw new mallException(mallExceptionEunm.PWD_FAIL);
        }
        return user;

    }

    @Override
    public void updateimformation(User user) {
        //更新签名,根据主键
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i==0){
            throw  new mallException(mallExceptionEunm.UPDATE_FAIL);
        }

    }

    @Override
    public boolean CheckAdminRole(User user) {
        return  user.getRole().equals(2);
    }
}
