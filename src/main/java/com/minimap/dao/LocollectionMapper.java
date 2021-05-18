package com.minimap.dao;

import com.minimap.pojo.Locollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LocollectionMapper {
    int deleteByPrimaryKey(Integer locid);

    int insert(Locollection record);

    int insertSelective(Locollection record);

    Locollection selectByPrimaryKey(Integer locid);

    int updateByPrimaryKeySelective(Locollection record);

    int updateByPrimaryKey(Locollection record);

    List<Locollection> selectByUserId(String userid);
}