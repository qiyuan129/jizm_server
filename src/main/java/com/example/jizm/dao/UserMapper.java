package com.example.jizm.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.jizm.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(@Param("userName")String userName);

    User selectByPhone(@Param("phone")String phone);


}