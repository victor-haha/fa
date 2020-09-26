package com.yidu.deposit.dao;

import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.DepositTrade;
import com.yidu.deposit.paging.DepositTradePaging;
import com.yidu.index.domain.SecuritiesInventory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：...
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/10
 */
public interface DepositTradeDao {
    /**
     * 1.添加存款交易数据
     * @param depositTrade 存款交易数据
     * @return 成功返回1，否则返回0
     */
    int addDeposit(DepositTrade depositTrade);

    /**
     * 2.1.查询满足条件的银行交易数据的数量
     * @param depositTradePaging 包含条件的实体类
     * @return 成功返回数量，否则返回0
     */
    long countDepositTradeByCondition(DepositTradePaging depositTradePaging);

    /**
     * 2.2.查询满足条件的银行交易数据
     * @param depositTradePaging 包含条件的实体类
     * @return 成功返回数据的集合，否则返回null
     */
    List<DepositTrade> findDepositByCondition(DepositTradePaging depositTradePaging);

    /**
     * 3.1.修改银行交易数据状态
     * @param paramMap 包含状态的map集合
     * @return 成功返回true，否则返回false
     */
    boolean updateTransactionStatus(Map<String,Object> paramMap);

    /**
     * 3.2.1.添加资金调拨数据
     * @param capitalTransfer 要添加的资金调拨数据
     * @return 成功返回true，否则返回false
     */
    boolean addBankTreasurer(CapitalTransfer capitalTransfer);

    /**
     * 3.2.2.根据ID查询银行交易数据
     * @param depositId 银行交易数据ID
     * @return 银行交易数据对象集合
     */
    DepositTrade findDepositById(String depositId);

    /**
     * 3.3.1.根据条件查询满足的证券库存数据
     * @param paramMap 包含条件的map
     * @return 成功返回对象，否则返回null
     */
    SecuritiesInventory findSecuritiesInventoryByCondition(Map<String,Object> paramMap);

    /**
     * 3.3.2.添加证券库存数据
     * @param securitiesInventory 添加对象
     * @return 成功返回true，否则返回false
     */
    boolean addSecuritiesInventory(SecuritiesInventory securitiesInventory);

    /**
     * 3.3.3.修改对应的证券库存数据
     * @param paramMap 包含修改条件的map
     * @return 成功返回true，否则返回false
     */
    boolean updateSecuritiesInventory(Map<String,Object> paramMap);

    /**
     * 3.4.1.查询今天的现金库存数据
     * @param paramMap 包含条件的map
     * @return 成功返回对象，否则返回null
     */
    CashInventory findCashInventoryByCondition(Map<String,Object> paramMap);

    /**
     * 3.4.2.查询最近一条现金库存数据
     * @param fundId 基金ID
     * @return 成功返回现金库存数据，否则返回null
     */
    CashInventory findCashInventoryByFundId(@Param("fundId") String fundId,@Param("accountId") String accountId);

    /**
     * 3.4.3.添加现金库存数据
     * @param cashInventory 现金库存数据
     * @return  成功返回true，否则返回false
     */
    boolean addCashInventory(CashInventory cashInventory);

    /**
     * 3.4.4.修改今天的现金库存数据
     * @param paramMap2 包含条件的map
     * @return 成功返回对象，否则返回null
     */
    boolean updateCashInventory(Map<String,Object> paramMap2);
}
