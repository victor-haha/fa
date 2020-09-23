package com.yidu.fund.service;

import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.deposit.paging.CashInventoryPaging;
import com.yidu.deposit.paging.FundInventoryPaging;
import com.yidu.format.LayuiFormat;
import com.yidu.fund.paging.CalculateRevenuePaging;

import java.util.List;

/**
 * 类的描述：基金收益计提业务层
 * @Author 李昊林
 * @Date 2020/9/15 16:31
 */
public interface CalculateRevenueService {
    /**
     * 根据条件查询基金库存
     * @param fundInventoryPaging 基金库存分页字段
     * @return 基金库存对象集合
     */
    List<FundInventory> findFundInventoryByCondition(FundInventoryPaging fundInventoryPaging);

    /**
     * 根据条件统计基金库存数量
     * @param fundInventoryPaging 基金库存分页字段
     * @return 基金库存数量
     */
    Long findCountFundInventoryByCondition(FundInventoryPaging fundInventoryPaging);

    /**
     * 根据基金库存id处理两费计提
     * @param fundInventoryIds 基金库存id
     * @return 计提成功返回true,否则返回false
     */
    boolean doFeeCalculate(String fundInventoryIds);
    /**
     * 根据条件查询现金库存
     * @param cashInventoryPaging 现金库存分页字段
     * @return 现金库存对象集合
     */
    List<CashInventory> findCashInventoryByCondition(CashInventoryPaging cashInventoryPaging);

    /**
     * 根据条件统计现金库存数量
     * @param cashInventoryPaging 现金库存分页字段
     * @return 现金库存数量
     */
    Long findCountCashInventoryByCondition(CashInventoryPaging cashInventoryPaging);
    /**
     * 根据现金库存id处理现金计息
     * @param cashInventoryIds 现金库存id
     * @return 计息成功返回true,否则返回false
     */
    boolean doCashInterest(String cashInventoryIds);
}
