package com.yidu.fund.controller;

import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.deposit.paging.CashInventoryPaging;
import com.yidu.deposit.paging.FundInventoryPaging;
import com.yidu.format.LayuiFormat;
import com.yidu.fund.service.CalculateRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类的描述：收益计提控制器
 * @Author 李昊林
 * @Date 2020/9/17 11:01
 */
@Controller
@RequestMapping("/calcRevenue")
public class CalculateRevenueController {
    @Autowired
    private CalculateRevenueService calculateRevenueService;

    /**
     * 查询基金库存信息
     * @param fundInventoryPaging 基金库存分页字段
     * @return layui格式json数据
     */
    @RequestMapping("/fundInventoryList")
    @ResponseBody
    public LayuiFormat findAllFundInventory(FundInventoryPaging fundInventoryPaging,LayuiFormat layuiFormat){
        List<FundInventory> fundInventoryList = calculateRevenueService.findFundInventoryByCondition(fundInventoryPaging);
        Long count = calculateRevenueService.findCountFundInventoryByCondition(fundInventoryPaging);
        if (CollectionUtils.isEmpty(fundInventoryList)) {
            layuiFormat.setCode(0);
            layuiFormat.setMsg("FAIL");
            layuiFormat.setCount(0L);
        }
        layuiFormat.setMsg("OK");
        layuiFormat.setCount(count);
        layuiFormat.setCode(0);
        layuiFormat.setData(fundInventoryList);
        return layuiFormat;
    }
    @ResponseBody
    @RequestMapping("/feeCalculate")
    public String processingFeeCalculate(String fundInventoryIds){
        boolean result = calculateRevenueService.doFeeCalculate(fundInventoryIds);
        if (result) {
            return "1";
        }
        return "0";
    }

    /**
     * 查询现金库存信息
     * @param cashInventoryPaging 现金库存分页字段
     * @return layui格式json数据
     */
    @RequestMapping("/cashInventoryList")
    @ResponseBody
    public LayuiFormat findAllCashInventory(CashInventoryPaging cashInventoryPaging,@Qualifier LayuiFormat layuiFormat){
        List<CashInventory> cashInventoryList = calculateRevenueService.findCashInventoryByCondition(cashInventoryPaging);
        Long count = calculateRevenueService.findCountCashInventoryByCondition(cashInventoryPaging);
        if (CollectionUtils.isEmpty(cashInventoryList)) {
            layuiFormat.setCode(0);
            layuiFormat.setMsg("FAIL");
            layuiFormat.setCount(0L);
        }
        layuiFormat.setMsg("OK");
        layuiFormat.setCount(count);
        layuiFormat.setCode(0);
        layuiFormat.setData(cashInventoryList);
        return layuiFormat;
    }
    @ResponseBody
    @RequestMapping("/cashInterest")
    public String processingCashInterest(String cashInventoryIds){
        boolean result = calculateRevenueService.doCashInterest(cashInventoryIds);
        if (result) {
            return "1";
        }
        return "0";
    }
}
