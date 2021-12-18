package com.services.impl;

import com.dao.IRouterDao;
import com.dao.impl.RouterDaoImpl;
import com.pojo.Router;
import com.services.IRouterServices;

import java.util.List;

public class RouterServicesImpl implements IRouterServices {
    IRouterDao routerDao = new RouterDaoImpl();

    @Override
    public List<Router> findAllRouterPage(Integer cid, int pageSize, int pageNo) {
        return routerDao.findAllRouterPage(cid, pageSize, pageNo);
    }

    @Override
    public Router findRouterById(Integer rid) {
        return routerDao.findRouterById(rid);
    }

    @Override
    public Integer totalCount(Integer cid) {
        return routerDao.totalCount(cid);
    }
}
