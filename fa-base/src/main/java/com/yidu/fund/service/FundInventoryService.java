package com.yidu.fund.service;

import com.yidu.deposit.domain.FundInventory;
import com.yidu.format.LayuiFormat;
import com.yidu.fund.paging.CalculateRevenuePaging;
import com.yidu.fund.paging.FundInventoryPaging;

import java.util.List;

/**
 * 类的描述：基金库存业务层
 * @Author 李昊林
 * @Date 2020/9/15 18:26
 */
public interface FundInventoryService {
    /**
     *根据条件查询基金库存
     * @param fundInventoryPaging 基金库存分页对象
     * @return 基金库存对象集合
     */
    List<FundInventory> findFundInventoryByCondition(FundInventoryPaging fundInventoryPaging);

    /**
     * 根据条件统计基金库存数据数量
     * @param fundInventoryPaging 基金库存分页对象
     * @return 基金库存数量
     */
    Long findCountByCondition(FundInventoryPaging fundInventoryPaging);
}
