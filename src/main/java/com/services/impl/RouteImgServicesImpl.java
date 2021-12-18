package com.services.impl;

import com.dao.IRouterImgDao;
import com.dao.impl.RouterImgDaoImpl;
import com.pojo.RouteImg;
import com.services.IRouteImgServices;

import java.util.List;

public class RouteImgServicesImpl implements IRouteImgServices {
    IRouterImgDao imgDao = new RouterImgDaoImpl();
    @Override
    public List<RouteImg> findRouteImgByRid(int rid) {
        return imgDao.findRouteImgByRid(rid);
    }
}
