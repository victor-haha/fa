package com.yidu.stock.controller;

import com.yidu.format.LayuiFormat;
import com.yidu.stock.service.StockService;
import com.yidu.stock.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 江北
 * @Date 2020/9/3 16:16
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;


    @ResponseBody
    @RequestMapping("/findAllStock")
    public LayuiFormat findAllStock(int page,int limit,@Qualifier LayuiFormat layuiFormat){
        System.out.println("page = " + page + ", limit = " + limit);
        System.out.println("控制器page = " + page);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("page",(page-1)*limit);
        paramMap.put("limit",limit);
        Long count = stockService.countStockByCondition(paramMap);
        System.out.println("count:"+count);
        layuiFormat.setCount(count);
        List<Stock> stockList = stockService.findStockByCondition(paramMap);
        System.out.println(stockList);
        //格式化成Layui所需数据
        layuiFormat.setCode(0);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(stockList);
        return layuiFormat;
    }


    @ResponseBody
    @RequestMapping("/findStockByCondition")
    public LayuiFormat findStockByCondition(String stockCode,String stockShortName,String plate,String industry,String exchange,String usable,int page,int limit,@Qualifier LayuiFormat layuiFormat){
        //将前端请求数据封装成map集合
        Map<String,Object> paramMap = new HashMap<String, Object>();
        System.out.println(usable);
        paramMap.put("stockCode",stockCode);
        paramMap.put("stockShortName",stockShortName);
        paramMap.put("plate",plate);
        paramMap.put("industry",industry);
        paramMap.put("exchange",exchange);
        paramMap.put("usable",usable);
        Long count = stockService.countStockByCondition(paramMap);
        paramMap.put("page",(page-1)*limit);
        paramMap.put("limit",limit);
        List<Stock> stockList = stockService.findStockByCondition(paramMap);
        layuiFormat.setCount(count);
        //格式化成Layui所需数据
        layuiFormat.setCode(0);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(stockList);
        return layuiFormat;
    }

    @ResponseBody
    @RequestMapping("/addStock")
    public int saveUser(Stock stock){
        if(stockService.addStock(stock)){
            return 1;
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/updateStock")
    public Map<String,Object> updateUser(Stock stock){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (stockService.updateStock(stock)) {
            returnMap.put("result", 1);
        } else {
            returnMap.put("result", 0);
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStockStatus/{stockIds}/{usable}",method = RequestMethod.DELETE)
    public Map<String,Object> updateStockStatus(@PathVariable("stockIds") String stockIds, @PathVariable("usable") String usable){
        System.out.println(stockIds);
        System.out.println(usable);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (stockService.updateStockStatus(stockIds,usable)) {
            returnMap.put("result", 1);
        } else {
            returnMap.put("result", 0);
        }
        return returnMap;
    }


}
