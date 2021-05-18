package com.minimap.service;

import com.minimap.pojo.Locollection;

import java.util.List;

public interface LocollectionService {
    /*根据userid查询收藏地点*/
    List<Locollection> SelectLocollectionByUser(Locollection locollection);

    /*像数据表中插入数据*/
    void InsertLocation(Locollection locollection);
}
