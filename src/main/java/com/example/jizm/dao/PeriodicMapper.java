package com.example.jizm.dao;

import com.example.jizm.model.Periodic;

public interface PeriodicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Periodic record);

    int insertSelective(Periodic record);

    Periodic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Periodic record);

    int updateByPrimaryKey(Periodic record);
}