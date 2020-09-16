package com.yidu.fund.controller;

import com.yidu.format.LayuiFormat;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.domain.FundTrade;
import com.yidu.fund.paging.FundTradePaging;
import com.yidu.fund.service.FundTradeService;
import com.yidu.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/7 20:59
 */
@Controller
public class FundTradeController {
    @Autowired
    private FundTradeService fundTradeService;
    @RequestMapping("/findAllFundTrade")
    @ResponseBody
    public LayuiFormat findFundTradeByCondition(FundTradePaging fundTradePaging,LayuiFormat layuiFormat){
        System.out.println("控制器" + layuiFormat);
        List<FundTrade> fundTradeList = fundTradeService.findFundTradeByCondition(fundTradePaging);
        if (CollectionUtils.isEmpty(fundTradeList)) {
            layuiFormat.setCode(0);
            layuiFormat.setMsg("FAIL");
            layuiFormat.setData(null);
            layuiFormat.setCount(0L);
        }
        layuiFormat.setCode(0);
        layuiFormat.setMsg("OK");
        layuiFormat.setData(fundTradeList);
        Long count = fundTradeService.findCountFundTradeByCondition(fundTradePaging);
        layuiFormat.setCount(count);
        return layuiFormat;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        List<FundTrade> fundTradeList= ExcelUtils.readExcelToEntity(FundTrade.class,file.getInputStream(),name);
        Map<String,Object> resultMap = new HashMap<>();
        boolean flag = fundTradeService.addFundTrade(fundTradeList);
        if (flag)
            resultMap.put("success","导入成功!");

        return resultMap;
    }
    @RequestMapping("/fundSettlement")
    @ResponseBody
    public String fundSettlement(String fundTradeIds,String tradeStatus){
        boolean flag = fundTradeService.fundSettlement(fundTradeIds,tradeStatus);
        if (flag)
            return "1";
        return "0";
    }

}
