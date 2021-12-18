package com.dao.impl;

import com.dao.BaseDao;
import com.dao.IRouterImgDao;
import com.pojo.RouteImg;
import com.pojo.Router;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouterImgDaoImpl extends BaseDao implements IRouterImgDao {

    @Override
    public List<RouteImg> findRouteImgByRid(int rid) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        List<RouteImg> list = null;
        try {
            String sql = "select * from tab_route_img where rid=?";
            con = super.getCon();
            pst = con.prepareStatement(sql);
            pst.setInt(1, rid);
            res = pst.executeQuery();
            list = new ArrayList<>();
            while (res.next()){
                RouteImg routeImg = new RouteImg();
                routeImg.setRgid(res.getInt("rgid"));
                routeImg.setRid(res.getInt("rid"));
                routeImg.setBigPic(res.getString("bigPic"));
                routeImg.setSmallPic(res.getString("smallPic"));
                list.add(routeImg);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst, res);
        }

        return list;
    }
}
