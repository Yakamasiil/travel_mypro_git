package com.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ConcurrentModificationException;
import java.util.Properties;

public class BaseDao {
    static DataSource ds = null;
    //创建连接池对象
    static {
        //加载配置文件中的数据
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("/druid.properties");
        Properties pp = new Properties();
        try{
            pp.load(is);
            //创建连接池，使用配置文件中的参数
            ds = DruidDataSourceFactory.createDataSource(pp);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Connection getCon(){
        Connection con = null;
        try{
            //1、加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://127.0.0.1:3306/travel?useUnicode=true&characterEncoding=utf-8";
//            //2、获取连接
//            con = DriverManager.getConnection(url, "root", "123456");
//            System.out.println("success connect MYSQL");
            con = ds.getConnection();
            return con;
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;

    }

    public static void close(Connection con, Statement st, ResultSet res){
        try {
            if(res!=null){
                res.close();
            }
            if(st!=null){
                st.close();
            }
            if(con!=null){
                con.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
