package com.example.jizm.service;

import com.alibaba.fastjson.JSONObject;
import com.example.jizm.util.BaseResult;
import com.example.jizm.dao.UserMapper;
import com.example.jizm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Random;

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
                //获取给定密码加盐后md5的结果，并于数据库中加密过的密码比较
                String md5GivenPassword=DigestUtils.md5DigestAsHex((password+userForBase.getRandom()).getBytes());
                if (!userForBase.getPassword().equals(md5GivenPassword)){
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
                //获取给定密码加盐后md5的结果，并于数据库中加密过的密码比较
                String md5GivenPassword=DigestUtils.md5DigestAsHex((password+userForBase.getRandom()).getBytes());
                if (!userForBase.getPassword().equals(md5GivenPassword)){
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

    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(int userId,String userName,String email,String phone){
        User user=userMapper.selectByPrimaryKey(userId);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPhone(phone);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserName(int userId,String userName){
        User user=userMapper.selectByPrimaryKey(userId);
        user.setUserName(userName);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(int userId,String email){
        User user=userMapper.selectByPrimaryKey(userId);
        user.setEmail(email);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePhoneNumber(int userId,String phoneNumber){
        User user=userMapper.selectByPrimaryKey(userId);
        user.setPhone(phoneNumber);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerUser(String userName,String phoneNumber,String password){
        User user=new User();
        Random random=new Random();

        int randomNumber=random.nextInt(Integer.MAX_VALUE);
        String md5password=DigestUtils.md5DigestAsHex((password+randomNumber).getBytes());

        user.setUserName(userName);
        user.setPhone(phoneNumber);
        //user.setEmail(email);
        user.setRandom(randomNumber);
        user.setPassword(md5password);

        userMapper.insertSelective(user);

    }

    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> updatePasswordByOldPassword(Integer userId,String oldPassword,String newPassword){
        User user=userMapper.selectByPrimaryKey(userId);
        int randomNumber=user.getRandom();

        String md5GivenOldPassword=DigestUtils.md5DigestAsHex((oldPassword+randomNumber).getBytes());
        //如果传来的旧密码正确，就允许此次密码修改
        if(user.getPassword().equals(md5GivenOldPassword)){
            String md5NewPassword=DigestUtils.md5DigestAsHex((newPassword+randomNumber).getBytes());
            user.setPassword(md5NewPassword);

            userMapper.updateByPrimaryKeySelective(user);
            return BaseResult.successWithData("修改密码成功！");
        }
        else{
            return BaseResult.failWithCodeAndMsg(401,"旧密码错误");
        }
    }
}
