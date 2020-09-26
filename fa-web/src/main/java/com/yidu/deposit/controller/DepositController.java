package com.yidu.deposit.controller;

import com.yidu.deposit.domain.DepositTrade;
import com.yidu.deposit.paging.DepositTradePaging;
import com.yidu.deposit.service.DepositTradeBiz;
import com.yidu.format.LayuiFormat;
import com.yidu.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：...
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/09
 */
@Controller
@RequestMapping("/depositController")
public class DepositController {
    @Autowired
    private DepositTradeBiz depositTradeBiz;
    @Autowired
    private LayuiFormat layuiFormat;

    /**
     *  1.上传存款交易数据，导入数据库中
     * @param file 存款交易数据文件
     * @return 返回包含结果的map
     * @throws Exception
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile file) throws Exception{
        //获取文件名
        String name = file.getOriginalFilename();
        //调用工具类，遍历文件中的数据并封装成deposit对象，将对象封装到集合中
        List<DepositTrade> depositTradeList = ExcelUtils.readExcelToEntity(DepositTrade.class,file.getInputStream(),name);
        //声明一个map，用来接收方法的返回结果
        Map<String,Object>  returnMap = new HashMap<>();
        //声明一个ret，用来接收addDeposit方法的返回结果
        int ret = 0;
        //循环遍历添加存款交易数据到数据库
        DepositTrade depositTrade = null;
        for (int i=0;i<depositTradeList.size()-1;i++){
            depositTrade = depositTradeList.get(i);
            //添加存款交易到数据库，并返回结果
            System.out.println("银行交易数据对象" + depositTrade);
            ret = depositTradeBiz.addDeposit(depositTrade);
        }
        //判断添加结果，
        if (ret == 1){
            returnMap.put("success","上传成功");
        }else {
            returnMap.put("success","上传失败");
        }
        return returnMap;
    }

    /**
     * 2.查询存款交易数据，可多条件查询
     * @param depositTradePaging
     * @return
     */
    @ResponseBody
    @RequestMapping("/findDepositByCondition")
    public LayuiFormat findDepositByCondition(DepositTradePaging depositTradePaging,LayuiFormat layuiFormat){
        System.out.println(depositTradePaging);
        //调用countDepositTradeByCondition
        long count = depositTradeBiz.countDepositTradeByCondition(depositTradePaging);
        //调用findDepositByCondition方法查询所有银行交易数据
        List<DepositTrade> depositTradeList = depositTradeBiz.findDepositByCondition(depositTradePaging);
        if (CollectionUtils.isEmpty(depositTradeList)){
            //将查询结果封装成JSON格式数据
            layuiFormat.setCode(0);
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("失败");
            layuiFormat.setData(null);
        }
        //将查询结果封装成JSON格式数据
        layuiFormat.setCode(0);
        layuiFormat.setCount(count);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(depositTradeList);
        return layuiFormat;
    }

    /**
     * 3.结算银行交易数据
     * @param depositIds 银行交易数据ID字符串
     * @param tradeStatus 银行交易数据状态
     * @return 成功返回true，否则返回false
     */
    @ResponseBody
    @RequestMapping("/depositTradeSettlement")
    public boolean depositTradeSettlement(String depositIds,String tradeStatus){
        //调用结算方法
        boolean b = depositTradeBiz.depositTradeSettlement(depositIds, tradeStatus);
        return b;
    }

}
