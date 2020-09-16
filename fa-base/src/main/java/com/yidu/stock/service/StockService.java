package com.yidu.stock.service;

import com.yidu.stock.domain.Stock;

import java.util.List;
import java.util.Map;

public interface StockService {
    List<Stock> queryAllStock();

    Long countStockByCondition(Map<String, Object> paramMap);

    List<Stock> findStockByCondition(Map<String,Object> paramMap);

    boolean addStock(Stock stock);

    boolean updateStock(Stock stock);

    boolean updateStockStatus(String stockIds, String usable);
}
