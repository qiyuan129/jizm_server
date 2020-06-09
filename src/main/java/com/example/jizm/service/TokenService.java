package com.example.jizm.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jizm.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUserId().toString())// 将 user id 保存到 token 里
                .withExpiresAt(new Date(System.currentTimeMillis()+864000000))//设置过期时间为10天
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
