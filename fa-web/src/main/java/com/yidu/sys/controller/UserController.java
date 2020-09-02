package com.yidu.sys.controller;

import com.yidu.format.LayuiFormat;
import com.yidu.user.domain.User;
import com.yidu.user.paging.UserPaging;
import com.yidu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/8/28 0:43
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return List<User> json数据
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public LayuiFormat findAll(UserPaging userPaging){
        LayuiFormat layuiFormat = userService.findAll(userPaging);
        return layuiFormat;
    }

    /**
     * 根据用户id修改其状态（usable）
     * @param usable 状态
     * @param userId 用户id
     * @return 修改：成功：1，失败0
     */
    @RequestMapping(value = "usable/{usable}",method = RequestMethod.GET)
    public int editUsable(@PathVariable("usable") String usable,String userId){
       int result =  userService.editUsable(usable,userId);
       return result;
    }
    @RequestMapping(value = "edit/{userId}",method = RequestMethod.POST)
    public int editUser(User user){
        return 0;
    }

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long userId){
       User user = userService.findById(userId);
       return user;
    }

}
