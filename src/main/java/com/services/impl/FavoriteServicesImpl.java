package com.services.impl;

import com.dao.IFavoriteDao;
import com.dao.IRouterDao;
import com.dao.impl.FavoriteDaoImpl;
import com.dao.impl.RouterDaoImpl;
import com.pojo.Favorite;
import com.services.IFavoriteServices;

import java.util.ArrayList;
import java.util.List;

public class FavoriteServicesImpl implements IFavoriteServices {

    IFavoriteDao favoriteDao = new FavoriteDaoImpl();
    IRouterDao routerDao = new RouterDaoImpl();
    @Override
    public Favorite findFavoriteByRidAndUid(int uid, int rid) {
        return favoriteDao.findFavoriteByRidAndUid(uid, rid);
    }

    @Override
    public int fCount(int rid) {
        return favoriteDao.fCount(rid);
    }

    @Override
    public int addFavorite(Favorite favorite) {
        return favoriteDao.addFavorite(favorite);
    }

    @Override
    public List<Favorite> findAllFavoriteByUid(int uid) {
        List<Favorite> list = favoriteDao.findAllFavoriteByUid(uid);
        List<Favorite> listn = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Favorite favorite = list.get(i);
            favorite.setRouter(routerDao.findRouterById(favorite.getRouter().getRid()));
            listn.add(favorite);
        }
        return listn;
    }
}
