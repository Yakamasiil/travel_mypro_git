package com.pojo;

import java.io.Serializable;
import java.util.Date;

public class Router implements Serializable {

    private Integer rid;
    private String rname;
    private Double price;
    private String routeIntroduce;
    private Integer rflag;
    private Date rdate;
    private Integer isThemeTour;
    private Integer count;
    private Integer cid;    //分类编号
    private Integer sid;    //销售人员编号
    private String rimage;
    private Integer sourceId;

    public Router() {
    }

    public Router(Integer rid, String rname, Double price, String routeIntroduce, Integer rflag, Date rdate, Integer isThemeTour, Integer count, Integer cid, Integer sid, Integer sourceId) {
        this.rid = rid;
        this.rname = rname;
        this.price = price;
        this.routeIntroduce = routeIntroduce;
        this.rflag = rflag;
        this.rdate = rdate;
        this.isThemeTour = isThemeTour;
        this.count = count;
        this.cid = cid;
        this.sid = sid;
        this.sourceId = sourceId;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public Integer getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(Integer isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }
}
