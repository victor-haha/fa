package com.yidu.stock.dao;


import com.yidu.capital.domain.CapitalTransfer;
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
    StockTrade findStockTradeByStock(String stockTradeId);
}
