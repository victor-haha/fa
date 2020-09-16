package com.yidu.fund.controller;

import com.yidu.format.LayuiFormat;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.paging.FundPaging;
import com.yidu.fund.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/3 8:32
 */
@Controller
public class FundController {

    @Autowired
    private FundService fundService;
    @ResponseBody
    @RequestMapping("/findAllFund")
    public LayuiFormat findAllFund(FundPaging fundPaging){
        return fundService.findAllFundWithPage(fundPaging);
    }
    @ResponseBody
    @RequestMapping("/findFundByCondition")
    public LayuiFormat findFundByCondition(FundPaging fundPaging){
        return fundService.findFundByCondition(fundPaging);
    }
    @ResponseBody
    @RequestMapping("/updateFundStatus/{fundId}/{usable}")
    public Map<String,String> updateFundStatus(@PathVariable("fundId") String fundId, @PathVariable("usable") String usable){
        boolean result = fundService.updateFundStatus(fundId,usable);
        Map<String,String> resultMap = new HashMap<>();
        if(result){
            resultMap.put("result","1");
        }else{
            resultMap.put("result","0");
        }
        return resultMap;

    }
    @ResponseBody
    @RequestMapping("/findUserAndFundCompany")
    public Map<String,Object> findUserAndFundCompany(){
        return fundService.findUserAndFundCompany();

    }
    @ResponseBody
    @RequestMapping("/saveFund")
    public Map<String,Object> saveFund(Fund fund){
        return fundService.addFund(fund);
    }
    @ResponseBody
    @RequestMapping("/updateFund")
    public int updateFund(Fund fund){
        return fundService.updateFund(fund);
    }

}
