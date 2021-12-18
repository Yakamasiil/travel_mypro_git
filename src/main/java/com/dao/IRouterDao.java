package com.dao;

import com.pojo.Router;

import java.util.List;

public interface IRouterDao {
    /**
     * 分页
     * @param pageSize  每页显示多少条
     * @param pageNo    显示第几页
     * @return
     */
    List<Router> findAllRouterPage(Integer cid, int pageSize, int pageNo);

    /**
     * 根据路线id查询路线信息
     * @param rid
     * @return
     */
    Router findRouterById(Integer rid);

    /**
     * 查询路线总条数
     * @return
     */
    Integer totalCount(Integer cid);
}
