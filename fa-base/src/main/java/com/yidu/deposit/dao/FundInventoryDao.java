package com.yidu.deposit.dao;

import com.yidu.deposit.domain.FundInventory;
import com.yidu.deposit.paging.FundInventoryPaging;

import java.util.List;

/**
 * 类的描述：基金库存数据访问层
 * @Author 李昊林
 * @Date 2020/9/15 18:30
 */
public interface FundInventoryDao {
    /**
     *根据条件查询基金库存
     * @param fundInventoryPaging 基金库存分页对象
     * @return 返回基金库存对象集合
     */
    List<FundInventory> findFundInventoryByCondition(FundInventoryPaging fundInventoryPaging);

    /**
     * 根据条件统计基金库存数据数量
     * @param fundInventoryPaging 基金库存分页对象
     * @return 基金库存数量
     */
    Long findCountByCondition(FundInventoryPaging fundInventoryPaging);

    /**
     * 根据基金库存id查询
     * @param fundInventoryId 基金库存id
     * @return 基金库存对象
     */
    FundInventory findFundInventoryById(String fundInventoryId);
}
