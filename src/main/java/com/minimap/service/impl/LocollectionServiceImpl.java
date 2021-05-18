package com.minimap.service.impl;

import com.minimap.dao.LocollectionMapper;
import com.minimap.pojo.Locollection;
import com.minimap.service.LocollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocollectionServiceImpl implements LocollectionService {

    @Autowired
    private LocollectionMapper locollectionMapper;

    /*根据userid查询list集合*/
    @Override
    public List<Locollection> SelectLocollectionByUser(Locollection locollection) {
        return locollectionMapper.selectByUserId(locollection.getUserid());
    }

    /**
     * 插入user下的collection
     * @param locollection
     */
    @Override
    public void InsertLocation(Locollection locollection) {
        locollectionMapper.insert(locollection);
    }
}
