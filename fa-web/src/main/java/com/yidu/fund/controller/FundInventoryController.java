package com.yidu.fund.controller;

import com.yidu.deposit.domain.FundInventory;
import com.yidu.format.LayuiFormat;
import com.yidu.fund.paging.FundInventoryPaging;
import com.yidu.fund.service.FundInventoryService;
import org.ehcache.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/15 21:23
 */
@Controller
public class FundInventoryController {
    @Autowired
    private FundInventoryService fundInventoryService;
    @RequestMapping("/list")
    @ResponseBody
    public LayuiFormat findFundInventoryByCondition(FundInventoryPaging fundInventoryPaging,LayuiFormat layuiFormat){
        System.out.println("layuiFormat = " + layuiFormat);
        List<FundInventory> fundInventoryList = fundInventoryService.findFundInventoryByCondition(fundInventoryPaging);
        Long count = fundInventoryService.findCountByCondition(fundInventoryPaging);
        if (CollectionUtils.isEmpty(fundInventoryList)) {
            layuiFormat.setMsg("fail");
            layuiFormat.setCode(0);
            layuiFormat.setCount(0L);
        }
        layuiFormat.setMsg("OK");
        layuiFormat.setData(fundInventoryList);
        layuiFormat.setCount(count);
        layuiFormat.setCode(0);
        return layuiFormat;
    }
}
