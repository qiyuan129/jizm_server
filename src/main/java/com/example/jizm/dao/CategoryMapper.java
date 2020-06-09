package com.example.jizm.dao;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.jizm.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);



    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    int updateByLocalIdAndUserId(@Param("updated")Category updated,@Param("localId")Integer localId,@Param("userId")Integer userId);


    Category selectByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);

    //List<Category> selectAllByUserId(@Param("userId")Integer userId);

    List<Category> selectByUserIdAndType(@Param("userId")Integer userId,@Param("type")Integer type);

    List<Category> selectByModifiedGreaterThanAndUserId(@Param("minModified")Date minModified,@Param("userId")Integer userId);



}