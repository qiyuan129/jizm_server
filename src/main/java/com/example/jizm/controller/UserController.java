package com.example.jizm.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.jizm.annotation.UserLoginToken;
import com.example.jizm.config.BaseResult;
import com.example.jizm.dao.UserMapper;
import com.example.jizm.model.User;
import com.example.jizm.service.TokenService;
import com.example.jizm.service.UserService;
import io.swagger.annotations.*;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = {"用户相关接口"},protocols = "http")
@RestController
public class UserController {
    @Resource
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/validationCode")
    @ApiOperation(value="获取验证码",notes="向指定手机号发送验证码",protocols = "http")
    @ApiImplicitParam(name="phoneNumber",value="用于接收验证码的手机号",required = true,dataTypeClass = String.class,
            paramType = "body")
    public BaseResult<String> getValidationCode(String phoneNumber){
        //@TODO
        return BaseResult.failWithCodeAndMsg(200,"发送成功");
    }

    @PostMapping("/user/login")
    @ApiOperation(value="用户登录",notes="根据输入的账户及密码尝试进行登录",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "登录方式：0为手机登录，1为用户名登录", required = true,example = "1",
                    dataType = "Int", paramType = "form"),
            @ApiImplicitParam(name = "account", value = "用户账户，代表手机还是用户名由type参数决定", required = true,
                    dataTypeClass = String.class,paramType = "form"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataTypeClass = String.class,
                    paramType = "form")
    })
    public BaseResult<String> userLogin(int type,String account,String password){
        //@TODO
        //JSONObject jsonObject=new JSONObject();
        return userService.userLogin(type,account,password);

    }

//    @GetMapping("/message")
//    @UserLoginToken
//    public String getMessage(HttpServletRequest request){
//        return "Success"+getUserID(request);
//    }

    @PostMapping("/user/registry")
    @ApiOperation(value="用户注册",notes="根据参数尝试注册新用户",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "用户手机号", required = true, dataTypeClass = String.class,
                    paramType = "body"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true,
                    dataTypeClass = String.class,paramType = "body"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataTypeClass = String.class,
                    paramType = "body"),
            @ApiImplicitParam(name="validationCode",value="发起注册请求前获取的验证码",required = true,
                    dataTypeClass = String.class, paramType = "body")
    })
    public BaseResult<String> userRegister(String phoneNumber,String password,String email,String validationCode){
        //@TODO
        return BaseResult.success();
    }

    public Object getUserID(HttpServletRequest request){
        if(request.getAttribute("userID")== null)
            return  -1;
        return request.getAttribute("userID");
    }

}
