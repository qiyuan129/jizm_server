package com.example.jizm.controller;

import com.example.jizm.annotation.UserLoginToken;
import com.example.jizm.util.BaseResult;
import com.example.jizm.dao.UserMapper;
import com.example.jizm.service.TokenService;
import com.example.jizm.service.UserService;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;

@Api(tags = {"用户相关接口"},protocols = "http")
@RestController
@Validated
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
    public BaseResult<String> userLogin(@Range(min=0,max=1) int type, String account, String password){
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
    })
    public BaseResult<String> userRegister(String phoneNumber,String password,String userName){
        userService.registerUser(userName,phoneNumber,password);
        return BaseResult.successWithData("注册成功！");
    }

//    public Object getUserID(HttpServletRequest request){
//        if(request.getAttribute("userID")== null)
//            return  -1;
//        return request.getAttribute("userID");
//    }



    @PostMapping("/user/information")
    @ApiOperation(value="修改用户资料（一次性修改用户名、电话、邮箱）",notes="根据参数修改对应用户的用户资料",protocols = "http")
    @UserLoginToken
    public BaseResult<String> updateUserInfo(@RequestAttribute int userId, String phone, String email, String userName){
        userService.updateUserInfo(userId,userName,email,phone);
        return BaseResult.successWithData("修改用户信息成功");
    }

    @PostMapping("/user/userName")
    @ApiOperation(value="修改用户名",protocols = "http")
    @UserLoginToken
    public BaseResult<String> updateUserName(@RequestAttribute int userId,
                                             String userName){
        userService.updateUserName(userId,userName);
        return BaseResult.successWithData("修改用户名成功");
    }

    @PostMapping("/user/phoneNumber")
    @ApiOperation(value="修改用户电话",protocols = "http")
    @UserLoginToken
    public BaseResult<String> updatePhoneNumber(@RequestAttribute int userId,
                                             String phoneNumber){
        userService.updatePhoneNumber(userId,phoneNumber);
        return BaseResult.successWithData("修改用户电话成功");
    }

    @PostMapping("/user/email")
    @ApiOperation(value="修改用户邮箱",protocols = "http")
    @UserLoginToken
    public BaseResult<String> updateUserEmail(@RequestAttribute int userId,
                                             String email){
        userService.updateEmail(userId,email);
        return BaseResult.successWithData("修改用户邮箱成功");
    }

    @PostMapping("/user/password/byOldPassword")
    @UserLoginToken
    public BaseResult<String> updatePassword(@RequestAttribute int userId,String oldPassword,String newPassword){
        BaseResult<String> result=userService.updatePasswordByOldPassword(userId,oldPassword,newPassword);
        return result;
    }
}
