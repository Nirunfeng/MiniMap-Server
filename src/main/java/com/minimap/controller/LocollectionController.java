package com.minimap.controller;

import com.minimap.pojo.Locollection;
import com.minimap.service.LocollectionService;
import com.minimap.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收藏地点等地图操作Controller
 */
@RestController
@RequestMapping("/map")
public class LocollectionController {

    @Autowired
    private LocollectionService locollectionService;

    /**
     * 收藏地点方法
     */
    @RequestMapping("/CollectionLocation")
    @ResponseBody
    public ResponseResult CollectionLocation(@RequestBody Locollection locollection){
        /*1.判断用户是否登录，未登录无法收藏地点*/
        if(locollection.getUserid()==null){
            //未登录，收藏无效
            return new ResponseResult(500,"收藏无效，请先登录",null);
        }

        /*2.进行收藏，最终目的是向数据库插入一条数据*/
        //2.1先根据userid对数据库表进行遍历，遍历出该用户收藏的地点结果集
        List<Locollection> locollectionList=locollectionService.SelectLocollectionByUser(locollection);
        for(int i=0;i<locollectionList.size();i++){
            System.out.println(locollectionList.get(i).getName());
            //出现地名相同
            if(locollection.getName().equals(locollectionList.get(i).getName())){
                //返回错误消息
                return new ResponseResult(500,"地点已收藏，请勿重复点击",null);
            }
        }
        //2.2没有相同结果，进行数据写入
        locollectionService.InsertLocation(locollection);
        return  new ResponseResult(200,"收藏成功",null);
    }

    /**
     * 展示收藏夹方法，将其显示在新页面中
     */
    //TODO List<Locollection> locollectionList=locollectionService.SelectLocollectionByUser(locollection)
    @RequestMapping("/Collection")
    @ResponseBody
    public ResponseResult Collection(@RequestBody Locollection locollection){
        //进行检索地址
        List<Locollection> locollectionList=locollectionService.SelectLocollectionByUser(locollection);
        //进行结果集判断
        if(locollectionList.size()!=0){
            //返回结果集
            return ResponseResult.ok(locollectionList);
        }

        return  new ResponseResult(500,"没有收藏地址",null);
    }

}
