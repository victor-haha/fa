package com.yidu.account.controller;

import com.yidu.account.domain.Account;
import com.yidu.account.paging.AccountPaging;
import com.yidu.account.service.AccountBiz;
import com.yidu.format.LayuiFormat;
import com.yidu.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：...
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/03
 */
@Controller
@RequestMapping("/accountController")
public class AccountController {
    @Autowired
    private AccountBiz accountBiz;
    @Autowired
    private LayuiFormat layuiFormat;

    /**
     * 查询所有现金账户，并分页
     * @param page 页数
     * @param limit 页面数据行数
     * @return 成功返回一个JSON格式的数据
     */
    @ResponseBody
    @RequestMapping("/findAllAccount")
    public LayuiFormat findAllAccount(int page,int limit){
        //处理请求数据
        Map<String,Object> map = new HashMap();
        map.put("page",(page-1)*limit);
        map.put("limit",limit);
        System.out.println(map.get("page") + "||||||||" + map.get("limit"));
        //调用Biz层countAccountByCondition方法查询所有现金账户的数量
        Long count = accountBiz.countAccountByCondition(map);
        //调用Biz层findAccountByConditionWithPaging方法查询所有现金账户
        List<Account> accountByConditionWithPaging = accountBiz.findAccountByConditionWithPaging(map);
        //将数据处理成前端需要的JSON格式
        layuiFormat.setCode(0);
        layuiFormat.setCount(count);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(accountByConditionWithPaging);
        return layuiFormat;
    }

    /**
     * 2.多条件组合查询
     * @param accountPaging 包含条件的实体类
     * @return 成功返回一个JSON格式的数据
     */
    @RequestMapping("/findAccountByCondition")
    @ResponseBody
    public LayuiFormat findAccountByCondition(AccountPaging accountPaging){
        //处理请求数据
        Map<String,Object> map = new HashMap<>();
        map.put("page",(accountPaging.getPage()-1)*accountPaging.getLimit());
        map.put("limit",accountPaging.getLimit());
        map.put("accountName",accountPaging.getAccountName());
        map.put("depositType",accountPaging.getDepositType());
        map.put("bankName",accountPaging.getBankName());
        //调用Biz层countAccountByCondition方法查询满足条件的现金账户的数量
        Long count = accountBiz.countAccountByCondition(map);
        //调用Biz层findAccountByConditionWithPaging方法查询满足条件现金账户
        List<Account> accountByConditionWithPaging = accountBiz.findAccountByConditionWithPaging(map);
        //将数据处理成前端需要的JSON格式
        layuiFormat.setCode(0);
        layuiFormat.setCount(count);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(accountByConditionWithPaging);
        return layuiFormat;
    }

    /**
     * 3.添加现金账户
     * @param account 要添加的现金账户
     * @return 成功返回1.否则返回0
     */
    @RequestMapping("/addAccount")
    @ResponseBody
    public int addAccount(Account account){
        account.setAccountId(IDUtil.getUuid());
        return accountBiz.addAccount(account);
    }

    /**
     * 4.修改现金账户
     * @param account 要修改的现金账户
     * @return 成功返回1，否则返回0
     */
    @ResponseBody
    @RequestMapping("/updateAccount")
    public int updateAccount(Account account){
        return accountBiz.updateAccount(account);
    }

    @RequestMapping(value = "/updateAccountStatus/{accountIds}/{usable}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> updateAccountStatus(@PathVariable("accountIds") String accountIds,@PathVariable("usable") String usable){
        System.out.println(accountIds + " ......"+usable);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (accountBiz.updateAccountStatus(accountIds,usable)) {
            returnMap.put("result", 1);
        } else {
            returnMap.put("result", 0);
        }
        return returnMap;
    }
}
