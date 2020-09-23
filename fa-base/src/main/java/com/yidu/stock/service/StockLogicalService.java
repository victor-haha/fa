package com.yidu.stock.service;

import com.yidu.format.LayuiFormat;
import com.yidu.stock.domain.StockTrade;
import com.yidu.stock.paging.StockTradePaging;

/**
 * 类的描述:股票交易业务的逻辑处理
 *
 * @author 江北
 * @since 2020/9/11 13:06
 */
public interface StockLogicalService {
    /**
     * 搜索查询所有股票交易数据并分页
     * @param stockTradePaging 搜索分页参数
     * @return  债券交易集合的layui格式数据
     */
    LayuiFormat findStockTrade(StockTradePaging stockTradePaging);


    /**
     *  结算操作
     * @param stockTradeId 股票交易数据id
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    int settlementsT(String stockTradeId, String tradeStatus);


    /**
     * 添加股票交易数据
     * @param stockTrade 股票交易数据对象
     */
    void addStockTrade(StockTrade stockTrade);
}
