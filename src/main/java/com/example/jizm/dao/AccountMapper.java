package com.example.jizm.dao;

import com.example.jizm.model.Account;import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int insertList(@Param("list") List<Account> list);

    Account selectByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);

    List<Account> selectAllByUserId(@Param("userId")Integer userId);


}