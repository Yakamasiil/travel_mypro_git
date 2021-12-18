package com.dao;

import com.pojo.RouteImg;

import java.util.List;

public interface IRouterImgDao {
    /**
     * 根据路线id查询当前路线下的图片列表
     * @return
     */
    List<RouteImg> findRouteImgByRid(int rid);
}
