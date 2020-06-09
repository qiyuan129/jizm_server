package com.example.jizm.dao;
import java.util.Date;

import com.example.jizm.model.Account;import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);



    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int updateByUserIdAndLocalId(@Param("updated")Account updated,@Param("userId")Integer userId,@Param("localId")Integer localId);

    Account selectByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);

    List<Account> selectAllByUserId(@Param("userId")Integer userId);

    int insertList(@Param("list")List<Account> list);

    List<Account> selectByModifiedGreaterThanAndUserId(@Param("minModified")Date minModified,@Param("userId")Integer userId);


}