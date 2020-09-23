package com.yidu.stock.dao;

import com.yidu.stock.domain.StockQuotation;
import com.yidu.stock.paging.StockQuotationPaging;

import java.util.List;

/**
 * 类的描述:股票行情业务的逻辑处理,数据操作层接口
 *
 * @author 江北
 * @since 2020/9/22
 */
public interface StockQuotationDao {
    /**
     * 搜索查询所有股票行情数据并分页
     * @param stockQuotationPaging 搜索分页参数
     * @return  股票交易集合的layui格式数据
     */
    List<StockQuotation> findStockQuotation(StockQuotationPaging stockQuotationPaging);

    /**
     * 搜索查询所有股票行情数据条数
     * @param stockQuotationPaging 搜索分页参数
     * @return 数据条数
     */
    Long findStockQuotationCount(StockQuotationPaging stockQuotationPaging);
    /**
     * 添加股票行情数据
     * @param stockQuotation 股票行情数据对象
     */
    void addStockQuotation(StockQuotation stockQuotation);
}
