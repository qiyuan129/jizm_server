package com.example.jizm.service;

import com.example.jizm.dao.UserMapper;
import com.example.jizm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User findByUsername(String userName){
        return userMapper.selectByUserName(userName);
    }
    public User findByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }
    public User findUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
