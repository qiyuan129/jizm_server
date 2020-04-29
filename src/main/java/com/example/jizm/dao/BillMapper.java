package com.example.jizm.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.example.jizm.model.Category;

import com.example.jizm.model.Bill;

public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<Bill> selectAllByCategoryIdAndUserId(@Param("categoryId")Integer categoryId, @Param("userId")Integer userId);

    Bill selectByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);

    List<Bill> selectAllByUserId(@Param("userId")Integer userId);

    List<Bill> selectAllByUserIdAndType(@Param("userId")Integer userId,@Param("type")Integer type);


}