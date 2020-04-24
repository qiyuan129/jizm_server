package com.example.jizm.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.jizm.model.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int insertList(@Param("list")List<Account> list);


}