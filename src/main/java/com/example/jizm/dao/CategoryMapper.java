package com.example.jizm.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.jizm.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);


}