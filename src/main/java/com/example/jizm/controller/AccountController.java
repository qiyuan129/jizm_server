package com.example.jizm.controller;

import com.example.jizm.dao.AccountMapper;
import com.example.jizm.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags={"账户相关接口"},protocols = "http")
public class AccountController {
    @Resource
    AccountMapper accountMapper;

    @GetMapping
    @ApiOperation(value="获取账户列表",notes="获取当前登录用户的账户列表",protocols = "http")
    @ApiImplicitParam(name="token",value="用户登录时获取的token",required = true,dataType="String",
            paramType = "header")
    public List<Account> getAccountList(@RequestHeader String token){
        //@TODO
        List<Account> accountList=new ArrayList<>();
        return accountList;
    }
}