package com.dao;

import com.pojo.User;

public interface IUserDao {

    /**
     * 根据用户名查询用户对象
     * @param userName
     * @return
     */
    User getUser(String userName);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据激活码查询用户信息
     * @param code
     * @return
     */
    User findUserByCode(String code);

    /**
     * 根据用户编号修改用户状态信息
     * @param uid
     * @return
     */
    int updateUserById(Integer uid);
}
