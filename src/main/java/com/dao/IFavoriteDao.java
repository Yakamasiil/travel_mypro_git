package com.dao;

import com.pojo.Favorite;

import java.util.List;

public interface IFavoriteDao {

    /**
     * 判断用户是否收藏了某条旅游线路
     * @param uid
     * @param rid
     * @return
     */
    Favorite findFavoriteByRidAndUid(int uid, int rid);

    /**
     * 查询某条旅游线路收藏的次数
     * @param rid
     * @return
     */
    int fCount(int rid);

    /**
     * 添加收藏旅游线路
     * @param favorite
     * @return
     */
    int addFavorite(Favorite favorite);

    /**
     * 查询个人收藏线路
     * @param uid
     * @return
     */
    List<Favorite> findAllFavoriteByUid(int uid);
}
