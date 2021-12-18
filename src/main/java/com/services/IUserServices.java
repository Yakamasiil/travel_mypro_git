package com.services;

import com.pojo.User;

public interface IUserServices {
    /**
     * 登录的方法
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password);

    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 检查用户名是否存在
     * @param uname
     * @return
     */
    boolean checkUserName(String uname);

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
