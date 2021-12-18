package com.dao.impl;

import com.dao.BaseDao;
import com.dao.IFavoriteDao;
import com.pojo.Favorite;
import com.pojo.Router;
import com.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDaoImpl extends BaseDao implements IFavoriteDao {
    @Override
    public Favorite findFavoriteByRidAndUid(int uid, int rid) {
        User user = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        Favorite favorite = null;
        try{
            //1、获取连接
            con = super.getCon();
            //2、创建sql语句
            String sql = "select * from tab_favorite where rid=? and uid=?"; //预处理sql传参，安全，高效，简洁
            //3、发送sql语句，并且设定参数
            pst = con.prepareStatement(sql);
            pst.setInt(1, rid);
            pst.setInt( 2, uid);
            //4、执行sql语句，得到返回的结果
            res = pst.executeQuery();
            //5、对结果进行处理封装
            if (res.next()) {   //判断是否有结果，有就把光标向下移动一行
                //System.out.println(res.getString("name"));
                favorite = new Favorite();

                user = new User();
                user.setUid(res.getInt("uid"));
                favorite.setUser(user);
                favorite.setDate(res.getString("date"));
                Router router = new Router();
                router.setRid(res.getInt("rid"));
                favorite.setRouter(router);
            }
            return favorite;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst, res);
        }

        return null;
    }

    @Override
    public int fCount(int rid) {
        int count = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        Favorite favorite = null;
        try{
            //1、获取连接
            con = super.getCon();
            //2、创建sql语句
            String sql = "select count(1) from tab_favorite where rid=?"; //预处理sql传参，安全，高效，简洁
            //3、发送sql语句，并且设定参数
            pst = con.prepareStatement(sql);
            pst.setInt(1, rid);
            //4、执行sql语句，得到返回的结果
            res = pst.executeQuery();
            //5、对结果进行处理封装
            if (res.next()) {   //判断是否有结果，有就把光标向下移动一行
                count = res.getInt(1);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst, res);
        }

        return 0;
    }

    @Override
    public int addFavorite(Favorite favorite) {

        int count = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try{
            //1、获取连接
            con = super.getCon();
            //2、创建sql语句
            String sql = "insert into tab_favorite(rid,date,uid) values(?,now(),?)"; //预处理sql传参，安全，高效，简洁
            //3、发送sql语句，并且设定参数
            pst = con.prepareStatement(sql);
            pst.setInt(1, favorite.getRouter().getRid());
            pst.setInt(2, favorite.getUser().getUid());
            //4、执行sql语句，得到返回的结果
            count = pst.executeUpdate();
            System.out.println(count);
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst,null);
        }

        return 0;
    }

    @Override
    public List<Favorite> findAllFavoriteByUid(int uid) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        List<Favorite> list = null;
        try{
            //1、获取连接
            con = super.getCon();
            //2、创建sql语句
            String sql = "select * from tab_favorite where uid=?"; //预处理sql传参，安全，高效，简洁
            //3、发送sql语句，并且设定参数
            pst = con.prepareStatement(sql);
            pst.setInt(1, uid);
            //4、执行sql语句，得到返回的结果
            res = pst.executeQuery();
            list = new ArrayList<>();
            //5、对结果进行处理封装
            while (res.next()) {   //判断是否有结果，有就把光标向下移动一行
                //System.out.println(res.getString("name"));
                Favorite favorite = new Favorite();

                favorite.setDate(res.getString("date"));
                Router router = new Router();
                router.setRid(res.getInt("rid"));
                favorite.setRouter(router);
                list.add(favorite);

            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst, res);
        }

        return null;
    }
}
