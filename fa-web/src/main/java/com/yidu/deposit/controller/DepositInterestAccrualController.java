package com.yidu.deposit.controller;

import com.yidu.deposit.service.DepositInterestAccrualBiz;
import com.yidu.format.LayuiFormat;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类的描述：存款计息控制器
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/21
 */
@Controller
@RequestMapping("/depositInterestAccrualController")
public class DepositInterestAccrualController {
    @Autowired
    private DepositInterestAccrualBiz depositInterestAccrualBiz;
    @Autowired
    private LayuiFormat layuiFormat;

    /**
     * 1.根据条件查询证券库存表中的银行交易数据
     * @param securitiesInventoryPaging 包含条件的POJO类
     * @return 成功返回一个JSON格式的数据
     */
    @ResponseBody
    @RequestMapping("/findSecuritiesInventoryByCondition")
    public LayuiFormat findSecuritiesInventoryByCondition(SecuritiesInventoryPaging securitiesInventoryPaging){
        //调用持久层方法查询满足条件的证券库存数
        long count = depositInterestAccrualBiz.countDepositInterestAccrualByCondition(securitiesInventoryPaging);
        List<SecuritiesInventory> securitiesInventoryList = depositInterestAccrualBiz.findSecuritiesInventoryByCondition(securitiesInventoryPaging);
        if(CollectionUtils.isEmpty(securitiesInventoryList)){
            layuiFormat.setCode(0);
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("失败");
            layuiFormat.setData(null);
        }
        layuiFormat.setCode(0);
        layuiFormat.setCount(count);
        layuiFormat.setMsg("成功");
        layuiFormat.setData(securitiesInventoryList);
        return layuiFormat;
    };

    /**
     * 2.存款计息
     * @param securitiesInventoryIds 证券库存中银行存款数据Id
     * @param tradeStatus 计息状态
     * @return 成功返回true，否则返回false
     */
    @ResponseBody
    @RequestMapping("/depositInterestAccrual")
    public boolean depositInterestAccrual(String securitiesInventoryIds,String tradeStatus){
        //调用存款计息业务逻辑层的存款计息方法
        boolean result = depositInterestAccrualBiz.depositInterestAccrualByCondition(securitiesInventoryIds,tradeStatus);
        return result;
    };

}
