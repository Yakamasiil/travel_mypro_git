package com.pojo;

import java.io.Serializable;

public class Favorite implements Serializable {
    private Router router;  //旅游线路对象
    private String date;    //收藏时间
    private User user;      //所属用户

    public Favorite() {
    }

    public Favorite(Router router, String date, User user) {
        this.router = router;
        this.date = date;
        this.user = user;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
