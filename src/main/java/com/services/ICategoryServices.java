package com.services;

import com.pojo.Category;

import java.util.List;

public interface ICategoryServices {
    /**
     * 返回所有的分类信息
     * @return
     */
    List<Category> findAllCategory();
}
