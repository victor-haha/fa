package com.yidu.stock.controller;


import com.alibaba.excel.EasyExcel;
import com.yidu.format.LayuiFormat;
import com.yidu.stock.Listener.DataStockQuotationListener;
import com.yidu.stock.domain.StockQuotation;
import com.yidu.stock.paging.StockQuotationPaging;
import com.yidu.stock.service.StockQuotationService;
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
@RequestMapping("/stockQuotation")
public class StockQuotationController {
    @Autowired
    private StockQuotationService stockQuotationService;


    /**
     * 搜索查询所有股票行情数据并分页
     * @param stockQuotationPaging 搜索分页参数
     * @return  股票交易集合的layui格式数据
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiFormat findStockQuotation(StockQuotationPaging stockQuotationPaging){
        return stockQuotationService.findStockQuotation(stockQuotationPaging);
    }

    /**
     * Excel数据导入，保存至数据库
     * @param file
     * @return 1：导入成功，0导入出错
     */
    @RequestMapping("/poi")
    public String addStockQuotation(@RequestParam MultipartFile file) {
        System.out.println("正在进行导入...");
        try {
            InputStream InputStream = file.getInputStream();
            EasyExcel.read(InputStream, StockQuotation.class,new DataStockQuotationListener(stockQuotationService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        //1：导入成功，0导入出错
        return "1";
    }

}
