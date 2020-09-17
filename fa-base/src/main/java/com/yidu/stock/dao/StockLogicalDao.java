package com.yidu.stock.dao;


import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.capital.domain.CashInventory;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.stock.domain.StockTrade;
import com.yidu.stock.paging.StockTradePaging;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类的描述:股票详细业务的逻辑处理,数据操作层接口
 *
 * @author 江北
 * @since 2020/9/11 13:08
 */
public interface StockLogicalDao {
    /**
     * 搜索查询所有股票交易数据并分页
     * @param stockTradePaging 搜索分页参数
     * @return  股票交易集合的layui格式数据
     */
    List<StockTrade> findStockTrade(StockTradePaging stockTradePaging);

    /**
     * 搜索查询所有股票交易数据条数
     * @param stockTradePaging 搜索分页参数
     * @return 数据条数
     */
    Long findStockTradeCount(StockTradePaging stockTradePaging);
    /**
     * 添加股票交易数据
     * @param stockTrade 股票交易数据对象
     */
    void addStockTrade(StockTrade stockTrade);

    /**
     * 修改股票交易状态
     * @param stockTradeId 股票交易数据id
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    int updateTradeStatus(@Param("stockTradeId") String stockTradeId, @Param("tradeStatus") String tradeStatus);

    /**
     * 向资金调拨表中插入数据
     * @param capitalTransfer 资金调拨对象
     * @return 是否插入成功 1：成功，0：失败
     */
    int addCapitalTransfer(CapitalTransfer capitalTransfer);


    /**
     *  按stockTradeId 联表t_capital_transfer,t_account,t_stock_trade 查询 ，返回资金调度对象
     * @param stockTradeId 股票交易id
     * @return 资金调度表对象
     */
    CapitalTransfer findCapitalTransferByStockTradeId(String stockTradeId);


    /**
     * 根据股票交易Id获取股票交易对象
     * @param stockTradeId 股票交易Id
     * @return 股票交易对象
     */
    StockTrade findStockTradeById(String stockTradeId);

    /**
     * 根据基金id 和 债券id 查询证券库存数据 (用于判断是否有同一支基金的同一支债券)
     * @param fundId 基金id
     * @param stockId 股票id
     * @return 券库存对象
     */
    SecuritiesInventory findStockInventory(@Param("fundId") String fundId, @Param("stockId") String stockId);

    /**
     * 查询到账户信息，及单位成本，赋值到证券库存对象中
     * @param fundId 基金id
     * @return 证券库存对象
     */
    SecuritiesInventory findSIByFundId(@Param("fundId") String fundId, @Param("stockTradeId") String stockTradeId);

    /**
     * 添加证券库存数据
     * @param securitiesInventory 债券库存对象
     * @return 是否插入成功， 1：成功，0：失败
     */
    int addSecuritiesInventory(SecuritiesInventory securitiesInventory);

    /**
     * 修改证券（债券）库存数据
     * @param securitiesInventory 债券库存对象
     * @return 是否修改成功， 1：成功，0：失败
     */
    int updateSecuritiesInventory(SecuritiesInventory securitiesInventory);

    /**
     * 按债券交易中的fundId和bondId,联表查询出现金对应的现金库存数据对象
     * @param fundId 基金id
     * @param stockId 债券id
     * @return 现金库存对象
     */
    CashInventory findCashInventory(@Param("fundId") String fundId, @Param("securitiesId") String stockId);

    /**
     * 将对应的现金账户更新
     * @param cashInventory 现金库存对象
     * @return 是否更新成功 1：成功，0：失败
     */
    int updateCashInventory(CashInventory cashInventory);


    /**
     * 添加今天新的现金库存数据
     * @param cashInventory 现金库存对象
     * @return 是否添加成功 1：成功，0：失败
     */
    int addCashInventory(CashInventory cashInventory);
}
