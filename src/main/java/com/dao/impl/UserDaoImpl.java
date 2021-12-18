package com.dao.impl;

import com.dao.BaseDao;
import com.dao.IUserDao;
import com.pojo.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl extends BaseDao implements IUserDao {
    User user = null;
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    @Override
    public User getUser(String userName) {
        try{
            //1、获取连接
            con = super.getCon();
            //2、创建sql语句
            String sql = "select * from tab_user where username=?"; //预处理sql传参，安全，高效，简洁
            //3、发送sql语句，并且设定参数
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            //4、执行sql语句，得到返回的结果
            res = pst.executeQuery();
            //5、对结果进行处理封装
            if (res.next()) {   //判断是否有结果，有就把光标向下移动一行
                //System.out.println(res.getString("name"));
                user = new User();
                user.setUid(res.getInt("uid"));
                user.setUserName(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setName(res.getString("name"));
                user.setBirthday(res.getString("birthday"));
                user.setSex(res.getInt("sex"));
                user.setTelephone(res.getString("telephone"));
                user.setEmail(res.getString("email"));
                user.setStatus(res.getInt("status"));
                user.setCode(res.getString("code"));
            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst, res);
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(new UserDaoImpl().getUser("zs"));
        User user1 = new User();
        user1.setUserName("lisi1");
        user1.setPassword("123");
        user1.setName("李四");
        user1.setBirthday("19911220");
        user1.setSex(1);
        user1.setTelephone("1264599");
        user1.setEmail("123@163.com");
        user1.setStatus(1);
        user1.setCode("lisi1");
        System.out.println(new UserDaoImpl().insertUser(user1));
    }

    @Override
    public int insertUser(User user) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        int count = 0;
        try{
            con = super.getCon();
            String sql = "INSERT INTO tab_user(username, PASSWORD, NAME, birthday, sex, telephone, email, STATUS, CODE) VALUES (?, ?, ?, ?, ?, ?, ?,0, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.setString(4, user.getBirthday());
            pst.setInt(5, user.getSex());
            pst.setString(6, user.getTelephone());
            pst.setString(7, user.getEmail());
            pst.setString(8, user.getCode());

            //执行sql
            count= pst.executeUpdate(); //jdbc执行增删改查返回影响的行数
            return count;

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            super.close(con ,pst, res);
        }
        return count;
    }

    @Override
    public User findUserByCode(String code) {
        User user = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        try{
            //1、获取连接
            con = super.getCon();
            //2、创建sql语句
            String sql = "select * from tab_user where code=?"; //预处理sql传参，安全，高效，简洁
            //3、发送sql语句，并且设定参数
            pst = con.prepareStatement(sql);
            pst.setString(1, code);
            //4、执行sql语句，得到返回的结果
            res = pst.executeQuery();
            //5、对结果进行处理封装
            if (res.next()) {   //判断是否有结果，有就把光标向下移动一行
                //System.out.println(res.getString("name"));
                user = new User();
                user.setUid(res.getInt("uid"));
                user.setUserName(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setName(res.getString("name"));
                user.setBirthday(res.getString("birthday"));
                user.setSex(res.getInt("sex"));
                user.setTelephone(res.getString("telephone"));
                user.setEmail(res.getString("email"));
                user.setStatus(res.getInt("status"));
                user.setCode(res.getString("code"));
            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst, res);
        }

        return null;
    }

    @Override
    public int updateUserById(Integer uid) {
        int count = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        try{
            con = super.getCon();
            String sql = "update tab_user set status=1 where uid=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, uid);

            //执行sql
            count= pst.executeUpdate(); //jdbc执行增删改查返回影响的行数
            return count;

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            super.close(con ,pst, res);
        }
        return count;
    }
}
