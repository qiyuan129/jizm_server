package com.example.jizm.service;

import com.example.jizm.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    UserMapper userMapper;
}
