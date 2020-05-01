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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    dataType = "Int", paramType = "body"),
            @ApiImplicitParam(name = "account", value = "用户账户，代表手机还是用户名由type参数决定", required = true,
                    dataTypeClass = String.class,paramType = "body"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataTypeClass = String.class,
                    paramType = "body")
    })
    public BaseResult<String> userLogin(int type,String account,String password){
        //@TODO
        //JSONObject jsonObject=new JSONObject();
        if(type==0){
            //手机登录
            User userForBase= userService.findByPhone(account);
            if(userForBase==null){
                return BaseResult.failWithCodeAndMsg(200,"账号不存在");
            }
            else{
                if (!userForBase.getPassword().equals(password)){
                    return BaseResult.failWithCodeAndMsg(200,"密码错误");
                }
                else {
                    String token = tokenService.getToken(userForBase);
                    return BaseResult.successWithData(token);
                }
            }
        }
        else{
            //用户名登录
            User userForBase= userService.findByUsername(account);
            if(userForBase==null){
                return BaseResult.failWithCodeAndMsg(200,"账号不存在");
            }
            else{
                if (!userForBase.getPassword().equals(password)){
                    return BaseResult.failWithCodeAndMsg(200,"密码错误");
                }
                else {
                    String token = tokenService.getToken(userForBase);
                    return BaseResult.successWithData(token);

                }
            }
        }
    }

    @GetMapping("/message")
    @UserLoginToken
    public String getMessage(){
        return "Success";
    }

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

    public int getUserID(HttpServletRequest request){ //token为空返回-1
        String userId;
        String token = request.getHeader("token");
        if(token==null)
            return -1;
        userId = JWT.decode(token).getAudience().get(0);
        return  Integer.parseInt(userId);
    }

}
