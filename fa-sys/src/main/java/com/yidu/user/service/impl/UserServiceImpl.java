package com.yidu.service.impl;

import com.yidu.dao.UserDao;
import com.yidu.domain.User;
import com.yidu.format.LayuiFormat;
import com.yidu.paging.UserPaging;
import com.yidu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/8/28 0:41
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LayuiFormat layuiFormat;

    @Override
    public LayuiFormat findAll(UserPaging userPaging) {
        int size = userPaging.getLimit();       //每一页的数据条数
        int start = (userPaging.getPage()-1)*size; //开始位置
        userPaging.setUserId("%"+userPaging.getUserId()+"%"); //加上%进行模糊查询
        userPaging.setUserName("%"+userPaging.getUserName()+"%");//加上%进行模糊查询
        userPaging.setPage(start);
        userPaging.setLimit(size);
        List<User> users = userDao.findAll(userPaging);
        if (CollectionUtils.isEmpty(users)){
            layuiFormat.setCode(1);
            layuiFormat.setMsg("未找到合适的数据哦！");
            layuiFormat.setCount(0L);
        }else{
            layuiFormat.setCode(0);
            layuiFormat.setCount(userDao.findCount(userPaging));
            layuiFormat.setMsg("成功获取到数据");
            layuiFormat.setData(users);

        }
        return layuiFormat;
    }

    @Override
    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public int editUsable(String usable, String userId) {
        return userDao.editUsable(usable,userId);
    }
}
