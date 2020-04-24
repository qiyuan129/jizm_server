package com.example.jizm.controller;

import com.example.jizm.dao.UserMapper;
import com.example.jizm.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"用户相关接口"},protocols = "http")
@RestController
public class UserController {
    @Resource
    UserMapper userMapper;

    @GetMapping("/user")
    @ApiOperation(value = "获取用户",notes="根据用户id获取用户",protocols = "http")
    @ApiImplicitParam(name="id",value="要获取的用户的id",required = true)
    public User getUser(int id){
        User user=userMapper.selectByPrimaryKey(id);
        return user;
    }

    @ApiOperation(value="添加用户",notes="根据发送的参数添加用户",protocols = "http")
    @PostMapping("/user")
    public User addUser(User user){
        userMapper.insert(user);
        return user;
    }
}
