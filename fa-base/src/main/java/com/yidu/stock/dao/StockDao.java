package com.yidu.stock.dao;


import com.yidu.stock.domain.Stock;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：股票数据层
 * @author 江北
 * @version 1.0
 * since 2020/09/09
 */
public interface StockDao {

    List<Stock> findAllStockWithPaging(Map<String,Integer> paramMap);

    long countStockByCondition(Map<String,Object> paramMap);

    List<Stock> findAllStock();
    List<Stock> findStockByCondition(Map<String,Object> paramMap);

    boolean addStock(Stock stock);

    boolean updateStock(Stock stock);

    boolean updateStockStatus(Map<String, Object> paramMap);
//    void addStock(Stock );
}
