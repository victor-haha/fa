package com.yidu.bond.controller;

import com.yidu.bond.domain.Bond;
import com.yidu.bond.paging.BondPaging;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.bond.service.BondService;
import com.yidu.format.LayuiFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/9/3 13:16
 */
@RestController
@RequestMapping("/bond")
public class BondController {
    @Autowired
    private BondService bondService;

    /**
     * 搜索查询债券+分页
     * @param bondPaging 分页搜索查询
     * @return 债券数据及相关数据
     */
    @RequestMapping("/list")
    public LayuiFormat findAll(BondPaging bondPaging){
        return bondService.findAll(bondPaging);
    }

    /**
     * 添加债券
     * @param bond 债券对象
     * @return 1:添加成功，0：添加失败
     */
    @RequestMapping("/add")
    public String addBond(Bond bond){
        int flag = bondService.addBond(bond);
        //返回修改成功与否1：成功，0：失败
        return flag == 1 ? "1" : "0";
    }

    /**
     * 修改债券状态
     * @param usable 1：可用，0：禁用
     * @param bondIds 修改的债券ids
     * @return 是否修改成功1：成功，0失败
     */
    @RequestMapping("/updateUsable")
    public String updateUsable(@RequestParam("usable") String usable,@RequestParam("bondIds") String bondIds){
        int flag = bondService.updateUsable(usable,bondIds);
        //返回修改成功与否1：成功，0：失败
        return flag == 1 ? "1" : "0";
    }

    /**
     * 修改债券
     * @param bond 修改对象
     * @return 1:修改成功，0：修改失败
     */
    @RequestMapping("/update")
    public String updateBond(Bond bond){
        int flag = bondService.updateBond(bond);
        return flag == 1 ? "1" : "0";
    }


    /**
     * 搜索查询所有债券交易数据并分页
     * @param bondTradePaging 搜索分页参数
     * @return  债券交易集合的layui格式数据
     */
    @RequestMapping("/trade/list")
    public LayuiFormat findBondTrade(BondTradePaging bondTradePaging){
        return bondService.findBondTrade(bondTradePaging);
    }

    /**
     *
     * @param bondTradeIds 债券交易数据ids
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    @RequestMapping("/trade/settlements")
    public String tradeStatus(String bondTradeIds , String tradeStatus){
        //将传来的多个id转换为String数组
        String[] ids = bondTradeIds.split(",");
        //循环ids数组，进行业务处理
        int flag = 0;
        for(String bondTradeId : ids){
           flag =  bondService.updateTradeStatus(bondTradeId,tradeStatus);
        }
        return flag==1 ? "1": "0";
    }
}
