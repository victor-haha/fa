package com.yidu.user.service.impl;

import com.yidu.user.dao.UserDao;
import com.yidu.user.domain.User;
import com.yidu.user.service.UserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/8/26 17:37
 */
@Service
public class UserBizImpl implements UserBiz {
    @Autowired
    private UserDao userDao;
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }


    public List<User> findUserByCondition(Map<String, Object> paramMap) {
        return userDao.findUserByCondition(paramMap);
    }

    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    public boolean deleteUser(String userId) {
        return userDao.deleteUser(userId);
    }

    public boolean updateUser(User user) {
        System.out.println("业务层user.getGender() = " + user.getGender());
        return userDao.updateUser(user);
    }

    public boolean updateUserStatus(String userIds,String usable) {
        //分割用户Id
        String[] ids = userIds.split(",");
        //定义修改状态的参数Map集合
        Map<String,Object> paramMap = new HashMap<String, Object>();
        //定义修改结果
        boolean result = false;
        paramMap.put("usable",usable);
        for (String userId : ids) {
            paramMap.put("userId",userId);
            result = userDao.updateUserStatus(paramMap);
            if (!result) throw new RuntimeException("");
        }
        return result;

    }

    public Long countUserByCondition(Map<String, Object> paramMap) {
        return userDao.countUserByCondition(paramMap);
    }
}
