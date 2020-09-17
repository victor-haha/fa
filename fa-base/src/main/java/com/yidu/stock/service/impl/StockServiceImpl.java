package com.yidu.stock.service.impl;

import com.yidu.stock.service.StockService;
import com.yidu.stock.dao.StockDao;

import com.yidu.stock.domain.Stock;
import com.yidu.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StockService")
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao stockDao;
    @Override
    public List<Stock> queryAllStock() {
        return stockDao.findAllStock();
    }

    @Override
    public Long countStockByCondition(Map<String, Object> paramMap) {
        return stockDao.countStockByCondition(paramMap);
    }

    @Override
    public List<Stock> findStockByCondition(Map<String, Object> paramMap) {
        return stockDao.findStockByCondition(paramMap);
    }

    @Override
    public boolean addStock(Stock stock) {
        //添加id
        stock.setStockId(IDUtil.getUuid());
        stock.setUsable("Y");
        System.out.println(stock);

        return stockDao.addStock(stock);
    }

    @Override
    public boolean updateStock(Stock stock) {
        System.out.println(stock);
        return stockDao.updateStock(stock);
    }

    @Override
    public boolean updateStockStatus(String stockCodes, String usable) {
        //分割用户Id
        String[] ids = stockCodes.split(",");
        System.out.println(ids);
        //定义修改状态的参数Map集合
        Map<String,Object> paramMap = new HashMap<String, Object>();
        //定义修改结果
        boolean result = false;
        paramMap.put("usable",usable);
        for (String stockCode : ids) {
            paramMap.put("stockCode",stockCode);
            System.out.println(paramMap);
            result = stockDao.updateStockStatus(paramMap);
//            if (!result) throw new RuntimeException("");
        }
        return result;
    }
}
