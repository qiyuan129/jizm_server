package com.example.jizm.dao;
import com.example.jizm.model.Account;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.example.jizm.model.Category;

import com.example.jizm.model.Bill;

public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);



    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    int updateByLocalIdAndUserId(@Param("updated")Bill updated,@Param("localId")Integer localId,@Param("userId")Integer userId);

    List<Bill> selectAllByCategoryIdAndUserId(@Param("categoryId")Integer categoryId, @Param("userId")Integer userId);

    List<Bill> selectAllByCategoryIdAndUserIdOrderByMoneyDesc(@Param("category")Category category, @Param("userId")Integer userId);

    List<Bill> selectAllByUserIdAndTypeAndAccount(@Param("userId")Integer userId,@Param("type")Integer type,@Param("accountId")Integer accountId);



    Bill selectByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);

    List<Bill> selectAllByUserId(@Param("userId")Integer userId);

    List<Bill> selectAllByUserIdAndType(@Param("userId")Integer userId,@Param("type")Integer type);

    List<Bill> selectByModifiedGreaterThanAndUserId(@Param("minModified")Date minModified,@Param("userId")Integer userId);

    Integer selectMaxLocalIdByUserId(@Param("userId")Integer userId);


}