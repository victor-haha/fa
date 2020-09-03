package com.yidu.sys.controller;

import com.yidu.format.LayuiFormat;
import com.yidu.user.domain.User;
import com.yidu.user.service.UserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/8/26 16:16
 */
@Controller
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @ResponseBody
    @RequestMapping("/findAllUser")
    public LayuiFormat findAllUser( int page,int limit,@Qualifier LayuiFormat layuiFormat){
        System.out.println("控制器page = " + page);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        Long count = userBiz.countUserByCondition(paramMap);
        layuiFormat.setCount(count);
        paramMap.put("page",(page-1)*limit);
        paramMap.put("limit",limit);
        List<User> userList = userBiz.findUserByCondition(paramMap);
        //格式化成Layui所需数据
        layuiFormat.setCode(0);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(userList);
        return layuiFormat;
    }

    @ResponseBody
    @RequestMapping("/findUserByCondition")
    public LayuiFormat findUserByCondition(String userName,String gender,String usable,int page,int limit,@Qualifier LayuiFormat layuiFormat){
        //将前端请求数据封装成map集合
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName",userName);
        paramMap.put("gender",gender);
        paramMap.put("usable",usable);
        Long count = userBiz.countUserByCondition(paramMap);
        paramMap.put("page",(page-1)*limit);
        paramMap.put("limit",limit);
        List<User> userList = userBiz.findUserByCondition(paramMap);
        layuiFormat.setCount(count);
        //格式化成Layui所需数据
        layuiFormat.setCode(0);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(userList);
        return layuiFormat;
    }

    @ResponseBody
    @RequestMapping("/verifyTelephone")
    public Map<String,Object> verifyTelephone( String telephone,@Qualifier LayuiFormat layuiFormat){
        //将前端请求数据封装成map集合
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("telephone",telephone);
        List<User> userList = userBiz.findUserByCondition(paramMap);
        paramMap.clear();
        if(userList.size() > 0) {
            paramMap.put("msg", "号码重复了");
            paramMap.put("flag", true);
        }
        return paramMap;
    }

    @ResponseBody
    @RequestMapping("/saveUser")
    public int saveUser(User user){
        if(userBiz.addUser(user)){
            return 1;
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String,Object> updateUser(User user){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (userBiz.updateUser(user)) {
            returnMap.put("result", 1);
        } else {
            returnMap.put("result", 0);
        }
        return returnMap;
    }
//    @ResponseBody
//    @RequestMapping("/updateUserStatus")
//    public Map<String,Object> updateUserStatus(String userId,String useable){
//        System.out.println("无Restful风格控制器方法userId = " + userId);
//        Map<String, Object> returnMap = new HashMap<String, Object>();
//        if (userBiz.updateUserStatus(userId,useable)) {
//            returnMap.put("result", 1);
//        } else {
//            returnMap.put("result", 0);
//        }
//        return returnMap;
//    }

    @ResponseBody
    @RequestMapping(value = "/updateUserStatus/{userIds}/{usable}",method = RequestMethod.DELETE)
    public Map<String,Object> updateUserStatus(@PathVariable("userIds") String userIds,@PathVariable("usable") String usable){
        System.out.println(userIds);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (userBiz.updateUserStatus(userIds,usable)) {
            returnMap.put("result", 1);
        } else {
            returnMap.put("result", 0);
        }
        return returnMap;
    }

    @RequestMapping("/deleteUser")
    public boolean deleteUser(String userId){
        return userBiz.deleteUser(userId);
    }

}
