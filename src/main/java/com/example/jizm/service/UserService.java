package com.example.jizm.service;

import com.example.jizm.config.BaseResult;
import com.example.jizm.dao.UserMapper;
import com.example.jizm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenService tokenService;
    public User findByUsername(String userName){
        return userMapper.selectByUserName(userName);
    }
    public User findByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }
    public User findUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


    public BaseResult<String> userLogin(int type, String account, String password) {
        if(type==0){
            //手机登录
            User userForBase= findByPhone(account);
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
            User userForBase= findByUsername(account);
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
}
