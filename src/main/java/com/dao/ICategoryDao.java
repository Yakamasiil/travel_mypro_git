package com.dao;


import com.pojo.Category;

import java.util.List;

public interface ICategoryDao {
    /**
     * 查询所有的分类信息
     * @return
     */
    List<Category> findAllCategory();
}
