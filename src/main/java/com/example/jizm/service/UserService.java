package com.example.jizm.service;

import com.alibaba.fastjson.JSONObject;
import com.example.jizm.util.BaseResult;
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


    public BaseResult userLogin(int type, String account, String password) {
        if(type==0){
            //手机登录
            User userForBase= findByPhone(account);
            if(userForBase==null){
                return BaseResult.failWithCodeAndMsg(404,"账号不存在");
            }
            else{
                if (!userForBase.getPassword().equals(password)){
                    return BaseResult.failWithCodeAndMsg(401,"密码错误");
                }
                else {
                    String token = tokenService.getToken(userForBase);
                    int userId=userForBase.getUserId();
                    String userName=userForBase.getUserName();
                    String phone=userForBase.getPhone();
                    String email=userForBase.getEmail();

                    JSONObject resultObject=new JSONObject();
                    resultObject.put("token",token);
                    resultObject.put("userId",userId);
                    resultObject.put("userName",userName);
                    resultObject.put("phone",phone);
                    resultObject.put("email",email);

                    return BaseResult.successWithData(resultObject);
                }
            }
        }
        else{
            //用户名登录
            User userForBase= findByUsername(account);
            if(userForBase==null){
                return BaseResult.failWithCodeAndMsg(404,"账号不存在");
            }
            else{
                if (!userForBase.getPassword().equals(password)){
                    return BaseResult.failWithCodeAndMsg(401,"密码错误");
                }
                else {
                    String token = tokenService.getToken(userForBase);
                    int userId=userForBase.getUserId();
                    String userName=userForBase.getUserName();
                    String phone=userForBase.getPhone();
                    String email=userForBase.getEmail();

                    JSONObject resultObject=new JSONObject();
                    resultObject.put("token",token);
                    resultObject.put("userId",userId);
                    resultObject.put("userName",userName);
                    resultObject.put("phone",phone);
                    resultObject.put("email",email);
                    return BaseResult.successWithData(resultObject);

                }
            }
        }
    }
    public void updateUserName(String userName,int userId){
        User user=userMapper.selectByPrimaryKey(userId);
        user.setUserName(userName);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void updateUserEmail(String email,int userId){
        User user=userMapper.selectByPrimaryKey(userId);
        user.setEmail(email);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void updateUserPhone(String phone,int userId){
        User user=userMapper.selectByPrimaryKey(userId);
        user.setPhone(phone);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
