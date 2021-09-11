package com.demon.mall.Service;

import com.demon.mall.model.pojo.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface UserService {
  void register(String username,String password) throws NoSuchAlgorithmException;
  User login(String username,String password);
  void updateimformation(User user);
  boolean CheckAdminRole(User user);
}
