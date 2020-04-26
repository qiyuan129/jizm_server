package com.example.jizm.controller;

import com.example.jizm.config.BaseResult;
import com.example.jizm.dao.UserMapper;
import com.example.jizm.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @PostMapping("/validationCode")
    @ApiOperation(value="获取验证码",notes="向指定手机号发送验证码",protocols = "http")
    @ApiImplicitParam(name="phoneNumber",value="用于接收验证码的手机号",required = true,dataType = "String",paramType = "body")
    public BaseResult<String> getValidationCode(String phoneNumber){
        //@TODO
        return BaseResult.failWithCodeAndMsg(200,"发送成功");
    }

    @PostMapping("/user/login")
    @ApiOperation(value="用户登录",notes="根据输入的账户及密码尝试进行登录",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "登录方式：0为手机登录，1为邮箱登录", required = true, dataType = "int",
                    paramType = "body"),
            @ApiImplicitParam(name = "account", value = "用户账户，代表手机还是邮箱由type参数决定", required = true,
                    dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String",
                    paramType = "body")
    })
    public BaseResult<String> userLogin(int type,String account,String password){
        //@TODO
        String token = null;
        return BaseResult.successWithData(token);
    }

    @PostMapping("/user/registry")
    @ApiOperation(value="用户注册",notes="根据参数尝试注册新用户",protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "用户手机号", required = true, dataType = "String",
                    paramType = "body"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true,
                    dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataType = "String",
                    paramType = "body"),
            @ApiImplicitParam(name="validationCode",value="发起注册请求前获取的验证码",required = true,dataType = "String",
                    paramType = "body")
    })
    public BaseResult<String> userRegister(String phoneNumber,String password,String email,String validationCode){
        return BaseResult.success();
    }

}
