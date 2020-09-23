package com.yidu.deposit.controller;

import com.yidu.deposit.paging.CashInventoryPaging;
import com.yidu.deposit.service.CashInventoryService;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.format.LayuiFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/9/11 14:42
 */
@RestController
@RequestMapping("/deposit")
public class CashInventoryController {
    @Autowired
    private CashInventoryService cashInventoryService;
    @Autowired
    private LayuiFormat layuiFormat;


    @RequestMapping("/list")
    public LayuiFormat findAll(CashInventoryPaging cashInventoryPaging){
        int limit = cashInventoryPaging.getLimit();
        int page = (cashInventoryPaging.getPage()-1)*limit;
        cashInventoryPaging.setPage(page);
        cashInventoryPaging.setLimit(limit);
        List<CashInventory> cashInventories = cashInventoryService.findAll(cashInventoryPaging);
        if(CollectionUtils.isEmpty(cashInventories)){
            layuiFormat.setCode(1);  //状态码0为查询到数据
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("未查询到指定数据哦!");
        }else{
            layuiFormat.setCode(0);  //状态码0为查询到数据
            layuiFormat.setCount(cashInventoryService.findCashInventoryCount(cashInventoryPaging));
            layuiFormat.setMsg("成功找到数据");
            layuiFormat.setData(cashInventories);
        }
        return layuiFormat;
    }
}
