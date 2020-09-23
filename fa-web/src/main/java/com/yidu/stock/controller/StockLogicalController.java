package com.yidu.stock.controller;


import com.alibaba.excel.EasyExcel;

import com.yidu.format.LayuiFormat;
import com.yidu.stock.Listener.DataListener;
import com.yidu.stock.domain.StockTrade;
import com.yidu.stock.paging.StockTradePaging;
import com.yidu.stock.service.StockLogicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 类的描述:股票详细业务的逻辑处理,控制器层
 *
 * @author 江北
 * @since 2020/9/11 13:03
 */
@RestController
@RequestMapping("/stockTrade")
public class StockLogicalController {
    @Autowired
    private StockLogicalService stockLogicalService;


    /**
     * 搜索查询所有股票交易数据并分页
     * @param stockTradePaging 搜索分页参数
     * @return  债券交易集合的layui格式数据
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiFormat findStockTrade(StockTradePaging stockTradePaging){
        return stockLogicalService.findStockTrade(stockTradePaging);
    }

    /**
     *
     * @param stockTradeIds 股票交易数据ids
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    @ResponseBody
    @RequestMapping("/settlements")
    public String settlements(String stockTradeIds , String tradeStatus){
        //将传来的多个id转换为String数组
        String[] ids = stockTradeIds.split(",");

        //循环ids数组，进行业务处理
        int flag = 0;
        for(String stockTradeId : ids){
            flag =  stockLogicalService.settlementsT(stockTradeId,tradeStatus);
        }
        return flag==1 ? "1": "0";
    }

    /**
     * Excel数据导入，保存至数据库
     * @param file
     * @return 1：导入成功，0导入出错
     */
    @RequestMapping("/poi")
    public String addStockTrade(@RequestParam MultipartFile file) {
        try {
            System.out.println(file);
            InputStream InputStream = file.getInputStream();
            System.out.println(InputStream);
            EasyExcel.read(InputStream, StockTrade.class,new DataListener(stockLogicalService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        //1：导入成功，0导入出错
        return "1";
    }

}
