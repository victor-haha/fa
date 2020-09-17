package com.yidu.user.dao;


import com.yidu.user.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/8/26 16:15
 */
public interface UserDao {
    /**
     * 查询所有用户
     * @return 用户对象集合
     */
    List<User> findAllUser();

    /**
     * 根据条件统统用户数量
     * @param paramMap 查询条件map集合
     * @return 返回用户数量
     */
    Long countUserByCondition(Map<String, Object> paramMap);

    /**
     * 根据条件查询
     * @param paramMap 查询条件map集合
     * @return 用户对象集合
     */
    List<User> findUserByCondition(Map<String, Object> paramMap);

    /**
     * 添加用户
     * @param user 用户对象
     * @return 成功添加返回true,否则返回false
     */
    boolean addUser(User user);

    /**
     * 删除用户
     * @param userId 用户Id
     * @return 成功删除返回true,否则返回false
     */
    boolean deleteUser(String userId);

    /**
     * 修改用户
     * @param user 用户对象
     * @return 成功修改返回true,否则返回false
     */
    boolean updateUser(User user);
    /**
     * 修改用户可用状态
     * @param paramMap 带有用户Id和用户可用状态的map参数集合
     * @return 成功修改返回true,否则返回false
     */
    boolean updateUserStatus(Map<String, Object> paramMap);
}
