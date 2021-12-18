package com.pojo;

import java.io.Serializable;

public class Category implements Serializable {

    private Integer cid;    //分类编号
    private String cname;   //分类名称

    public Category() {
    }

    public Category(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
