package com.yidu.deposit.service;

import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;

import java.util.List;

/**
 * 类的描述：存款计息业务逻辑接口
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/21
 */
public interface DepositInterestAccrualBiz {
    /**
     * 1.1.根据条件查询证券库存表中的银行交易数据数量
     * @param securitiesInventoryPaging 包含条件的POJO类
     * @return 功返回一个数量，否则返回0
     */
    Long countDepositInterestAccrualByCondition(SecuritiesInventoryPaging securitiesInventoryPaging);
    /**
     * 1.2.根据条件查询证券库存表中的银行交易数据
     * @param securitiesInventoryPaging 包含条件的POJO类
     * @return 成功返回一个包含数据的集合，否则返回null
     */
    List<SecuritiesInventory> findSecuritiesInventoryByCondition(SecuritiesInventoryPaging securitiesInventoryPaging);

    /**
     * 2.存款计息
     * @param securitiesInventoryIds 证券库存中银行存款数据Id
     * @param tradeStatus 计息状态
     * @return 成功返回true，否则返回false
     */
    boolean depositInterestAccrualByCondition(String securitiesInventoryIds,String tradeStatus);
}
