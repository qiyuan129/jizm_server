package com.example.jizm.dao;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.jizm.model.Periodic;

public interface PeriodicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Periodic record);

    int insertSelective(Periodic record);

    Periodic selectByPrimaryKey(Integer id);

    Periodic selectByLocalIdAndUserId(@Param("localId")Integer localId,@Param("userId")Integer userId);

    List<Periodic> selectByModifiedGreaterThanAndUserId(@Param("minModified")Date minModified,@Param("userId")Integer userId);



    int updateByPrimaryKeySelective(Periodic record);

    int updateByPrimaryKey(Periodic record);

    int updateByLocalIdAndUserId(@Param("updated")Periodic updated,@Param("localId")Integer localId,@Param("userId")Integer userId);


}