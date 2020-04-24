package com.example.jizm.dao;

import com.example.jizm.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserMapperTest {
    @Resource
    UserMapper userMapper;

    @Test
    void insert() {
        User user=new User();
        user.setUserName("cqy");
        user.setPhone("18059905120");
        user.setPassword("123456");
        userMapper.insert(user);
    }

    @Test
    void selectByPrimaryKey() {
        User user=userMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
    }
}