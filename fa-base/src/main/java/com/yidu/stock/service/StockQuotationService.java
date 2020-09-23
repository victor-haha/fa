package com.yidu.stock.service;

import com.yidu.format.LayuiFormat;
import com.yidu.stock.domain.StockQuotation;
import com.yidu.stock.paging.StockQuotationPaging;

/**
 * 类的描述:股票行情业务的逻辑处理
 *
 * @author 江北
 * @since 2020/9/22
 */
public interface StockQuotationService {

    /**
     * 搜索查询所有股票行情数据并分页
     * @param stockQuotationPaging 搜索分页参数
     * @return  股票行情集合的layui格式数据
     */
    LayuiFormat findStockQuotation(StockQuotationPaging stockQuotationPaging);



    /**
     * 添加股票行情数据
     * @param stockQuotation 股票行情数据对象
     */
    void addStockQuotation(StockQuotation stockQuotation);
}
