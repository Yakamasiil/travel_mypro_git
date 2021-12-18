package com.dao.impl;

import com.dao.BaseDao;
import com.dao.ICategoryDao;
import com.pojo.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends BaseDao implements ICategoryDao {
    @Override
    public List<Category> findAllCategory() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        List<Category> listc = null;
        try{
            con = super.getCon();
            String sql = "select * from tab_category order by cid";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
            listc = new ArrayList<>();
            while(res.next()){
                Category category = new Category();
                category.setCid(res.getInt("cid"));
                category.setCname(res.getString("cname"));
                listc.add(category);
            }
            System.out.println(sql);
            return listc;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            super.close(con, pst, res);
        }
        return listc;
    }
}
