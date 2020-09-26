package com.yidu.deposit.service;

import com.yidu.deposit.domain.DepositTrade;
import com.yidu.deposit.paging.DepositTradePaging;

import java.util.List;

/**
 * 类的描述：...
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/10
 */
public interface DepositTradeBiz {
    /**
     * 1.添加银行存款交易数据
     * @param depositTrade 银行存款交易数据
     * @return 成功返回1，否则返回0
     */
    int addDeposit(DepositTrade depositTrade);

    /**
     * 2.1.查询满足条件的银行交易数据数量
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
     * 3.结算
     * @param depositIds
     * @param tradeStatus
     * @return
     */
    boolean depositTradeSettlement(String depositIds,String tradeStatus);
    /**
     * 3.1.修改银行交易数据状态
     * @param depositIds 银行交易数据ID字符串
     * @param tradeStatus 银行交易数据状态
     * @return 成功返回true，否则返回false
     *//*
    boolean updateTransactionStatus(String depositIds,String tradeStatus);

    *//**
     * 3.2往资金调拨表中添加数据
     * @param depositTradeList 银行交易数据对象集合
     * @return 成功返回true，否则返回false
     *//*
    boolean addBankTreasurer(List<DepositTrade> depositTradeList);

    *//**
     * 3.3修改或添加证券库存表
     * @param depositTradeList 银行交易数据对象集合
     * @return 成功返回true，否则返回false
     *//*
    boolean addOrUpdateStockInventory(List<DepositTrade> depositTradeList);*/
}
