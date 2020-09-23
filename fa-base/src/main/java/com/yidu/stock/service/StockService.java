package com.yidu.stock.service;

import com.yidu.stock.domain.Stock;

import java.util.List;
import java.util.Map;

/**
 * 类的描述:股票业务的逻辑处理
 *
 * @author 江北
 * @since 2020/9/22
 */
public interface StockService {
    List<Stock> queryAllStock();

    Long countStockByCondition(Map<String, Object> paramMap);

    List<Stock> findStockByCondition(Map<String,Object> paramMap);

    boolean addStock(Stock stock);

    boolean updateStock(Stock stock);

    boolean updateStockStatus(String stockIds, String usable);
}
