package com.services.impl;

import com.dao.impl.CategoryDaoImpl;
import com.pojo.Category;
import com.services.ICategoryServices;
import com.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServicesImpl implements ICategoryServices {
    @Override
    public List<Category> findAllCategory() {
        Jedis jedis = JedisUtil.getJedis();
        List<Category> list = null;
        //首先从缓存中加载分类数据
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);


        //1、如果发现缓存中不存在分类数据，就从数据库查询分类数据，并把数据存储进redis中
        if (categories==null || categories.size()==0) {
            list = new CategoryDaoImpl().findAllCategory();
            for(int i=0;i<list.size();i++){
                jedis.zadd("category", list.get(i).getCid(), list.get(i).getCname());
            }
        }else{  //2、如果发现缓存中存在分类数据，直接从缓存中读取加载分类数据
            list = new ArrayList<>();
            for (Tuple tuple : categories){
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                list.add(category);
            }
        }

        return list;
//        return new CategoryDaoImpl().findAllCategory();
    }
}
