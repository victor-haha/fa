package com.yidu.bond.controller;

import com.alibaba.excel.EasyExcel;
import com.yidu.bond.Listener.DataListener;
import com.yidu.bond.domain.BondTrade;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.bond.service.BondLogicalService;
import com.yidu.format.LayuiFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 类的描述:债券详细业务的逻辑处理,控制器层
 *
 * @author wh
 * @since 2020/9/8 13:03
 */
@RestController
@RequestMapping("/bondTrade")
public class BondLogicalController {
    @Autowired
    private BondLogicalService bondLogicalService;


    /**
     * 搜索查询所有债券交易数据并分页
     * @param bondTradePaging 搜索分页参数
     * @return  债券交易集合的layui格式数据
     */
    @RequestMapping("/list")
    public LayuiFormat findBondTrade(BondTradePaging bondTradePaging){
        return bondLogicalService.findBondTrade(bondTradePaging);
    }

    /**
     *
     * @param bondTradeIds 债券交易数据ids
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    @RequestMapping("/settlements")
    public String settlements(String bondTradeIds , String tradeStatus){
        //将传来的多个id转换为String数组
        String[] ids = bondTradeIds.split(",");
        //循环ids数组，进行业务处理
        int flag = 0;
        for(String bondTradeId : ids){
            flag =  bondLogicalService.settlementsT(bondTradeId,tradeStatus);
        }
        return flag==1 ? "1": "0";
    }

    /**
     * Excel数据导入，保存至数据库
     * @param file
     * @return 1：导入成功，0导入出错
     */
    @RequestMapping("/poi")
    public String addBondTrade(@RequestParam MultipartFile file) {
        try {

            InputStream InputStream = file.getInputStream();

            EasyExcel.read(InputStream, BondTrade.class,new DataListener(bondLogicalService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        //1：导入成功，0导入出错
        return "1";
    }
}
