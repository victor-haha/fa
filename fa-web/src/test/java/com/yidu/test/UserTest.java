package com.yidu.test;

import com.yidu.user.domain.User;
import com.yidu.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/8/28 0:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserTest {
    @Autowired
    private UserService userService;
    @Test
    public void findAlltest(){
        /*LayuiFormat layuiFormat = userService.findAll();
        System.out.println(layuiFormat);*/
    }

    @Test
    public void findByIdtest(){
        User user = userService.findById(1L);
            System.out.println(user);
    }
}
