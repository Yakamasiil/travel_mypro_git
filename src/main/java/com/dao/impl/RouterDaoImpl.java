package com.dao.impl;

import com.dao.BaseDao;
import com.dao.IRouterDao;
import com.pojo.Router;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouterDaoImpl extends BaseDao implements IRouterDao {
    @Override
    public List<Router> findAllRouterPage(Integer cid, int pageSize, int pageNo) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        List<Router> listr = null;

        try{
            String sql = "select * from tab_route where cid=? limit ?,?";
            con = super.getCon();
            pst = con.prepareStatement(sql);

            pst.setInt(1, cid);
            pst.setInt(2, (pageNo-1)*pageSize);
            pst.setInt(3, pageSize);

            listr = new ArrayList<>();
            res = pst.executeQuery();
            while (res.next()){
                Router router = new Router();
                router.setRid(res.getInt("rid"));
                router.setRname(res.getString("rname"));
                router.setPrice(res.getDouble("price"));
                router.setRouteIntroduce(res.getString("routeIntroduce"));
                router.setRflag(res.getInt("rflag"));
                router.setRdate(res.getDate("rdate"));
                router.setIsThemeTour(res.getInt("isThemeTour"));
                router.setCount(res.getInt("count"));
                router.setCid(res.getInt("cid"));
                router.setRimage(res.getString("rimage"));
                listr.add(router);
            }
            return listr;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con ,pst, res);
        }

        return listr;
    }

    @Override
    public Router findRouterById(Integer rid) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        Router router = null;

        try{
            String sql = "select * from tab_route where rid=?";
            con = super.getCon();
            pst = con.prepareStatement(sql);
            pst.setInt(1, rid);
            res = pst.executeQuery();
            if (res.next()){
                router = new Router();
                router.setRid(res.getInt("rid"));
                router.setRname(res.getString("rname"));
                router.setPrice(res.getDouble("price"));
                router.setRouteIntroduce(res.getString("routeIntroduce"));
                router.setRflag(res.getInt("rflag"));
                router.setRdate(res.getDate("rdate"));
                router.setIsThemeTour(res.getInt("isThemeTour"));
                router.setCount(res.getInt("count"));
                router.setCid(res.getInt("cid"));
                router.setRimage(res.getString("rimage"));
                return router;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con ,pst, res);
        }

        return router;
    }

    @Override
    public Integer totalCount(Integer cid) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        int count = 0;
        try{
            String sql = "select count(1) from tab_route where cid=?";
            con = super.getCon();
            pst = con.prepareStatement(sql);
            pst.setInt(1, cid);
            res = pst.executeQuery();
            if (res.next()){
                count = res.getInt(1);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con ,pst, res);
        }

        return count;
    }
}
