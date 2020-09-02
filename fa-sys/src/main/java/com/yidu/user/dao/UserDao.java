package com.yidu.dao;



import com.yidu.domain.User;
import com.yidu.paging.UserPaging;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类的描述:用户数据操作层接口
 *
 * @author wh
 * @since 2020/8/28 0:20
 */
public interface UserDao {
    /**
     * 查询所有用户
     * @return 用户集合
     */
    List<User> findAll(UserPaging userPaging);

    /**
     * 按用户id查询用户
     * @param userId 用户id
     * @return 用户对象
     */
    User findById(Long userId);

    /**
     * 查询所有用户数
     * @return
     */
    Long findCount(UserPaging userPaging);

    /**
     * 根据用户id修改其状态（usable）
     * @param usable 状态
     * @param userId 用户id
     * @return 修改：成功：1，失败0
     */
    int editUsable(@Param("usable") String usable, @Param("userId") String userId);
}
