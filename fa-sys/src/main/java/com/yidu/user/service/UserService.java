package com.yidu.user.service;

import com.yidu.format.LayuiFormat;
import com.yidu.user.domain.User;
import com.yidu.user.paging.UserPaging;

/**
 * 类的描述: 用户业务逻辑接口类
 *
 * @author wh
 * @since 2020/8/28 0:40
 */
public interface UserService {
    /**
     * 查询所有用户
     * @return  list<user> 的集合
     */
     LayuiFormat findAll(UserPaging userPaging);

    /**
     * 按用户id查询用户对象
     * @param userId 用户id
     * @return 用户对象
     */
    User findById(Long userId);

    /**
     * 根据用户id修改其状态（usable）
     * @param usable 状态
     * @param userId 用户id
     * @return 修改：成功：1，失败0
     */
    int editUsable(String usable, String userId);
}
