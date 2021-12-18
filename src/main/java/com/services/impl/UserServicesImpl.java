package com.services.impl;

import com.dao.IUserDao;
import com.dao.impl.UserDaoImpl;
import com.pojo.User;
import com.services.IUserServices;
import com.util.MailUtils;
import com.util.Md5Util;
import com.util.UuidUtil;

public class UserServicesImpl implements IUserServices {
    IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String userName, String password) {
        //根据用户名获得用户信息
        User user = userDao.getUser(userName);
        if (user != null) {
            //对传入的密码加密进行比较
            try{
                password = Md5Util.encodeByMd5(password);
            }catch (Exception e){
                e.printStackTrace();
            }
            //进行密码对比，对上了返回对象，没对上返回空
            if (user.getPassword().equals(password) && user.getStatus()==1) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        //对用户的密码进行加密然后存储
        try{
            String md5Pwd = Md5Util.encodeByMd5(user.getPassword());
            //        String md5Pwd  = Md5Util.enco  user.getPassword()
        user.setPassword(md5Pwd);
        }catch (Exception e){
            e.printStackTrace();
        }

        //设置激活码
        String code = UuidUtil.getUuid();
        user.setCode(code);
        //给用户发送激活邮件
        String href =
                "<a href='http://localhost:8080/travel_mypro/user?action=activeAccount&code="+code+"'>请点击激活账户</a>";
        MailUtils.sendMail(user.getEmail(), href, "账户激活邮件");

        int count = userDao.insertUser(user);
        if (count >0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUserName(String uname) {
        User user = userDao.getUser(uname);
        if(user!=null){
            return  true;
        }
        return false;
    }

    @Override
    public User findUserByCode(String code) {
        return userDao.findUserByCode(code);
    }

    @Override
    public int updateUserById(Integer uid) {
        return userDao.updateUserById(uid);
    }
}
