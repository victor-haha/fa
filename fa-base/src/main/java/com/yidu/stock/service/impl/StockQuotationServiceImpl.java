package com.yidu.stock.service.impl;

import com.yidu.format.LayuiFormat;

import com.yidu.stock.dao.StockQuotationDao;
import com.yidu.stock.domain.StockQuotation;
import com.yidu.stock.paging.StockQuotationPaging;
import com.yidu.stock.service.StockQuotationService;
import com.yidu.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("StockQuotationService")
public class StockQuotationServiceImpl implements StockQuotationService {
    @Autowired
    private StockQuotationDao stockQuotationDao;
    @Autowired
    private LayuiFormat layuiFormat;
    @Override
    public LayuiFormat findStockQuotation(StockQuotationPaging stockQuotationPaging) {
        int limit = stockQuotationPaging.getLimit();
        int page = (stockQuotationPaging.getPage()-1)*limit;
        stockQuotationPaging.setPage(page);
        stockQuotationPaging.setLimit(limit);
        List<StockQuotation> stockQuotations = stockQuotationDao.findStockQuotation(stockQuotationPaging);
        if(CollectionUtils.isEmpty(stockQuotations)){
            layuiFormat.setCode(1);  //状态码0为查询到数据
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("未查询到指定数据哦!");
        }else{
            layuiFormat.setCode(0);  //状态码0为查询到数据
            layuiFormat.setCount(stockQuotationDao.findStockQuotationCount(stockQuotationPaging));
            layuiFormat.setMsg("成功找到数据");
            layuiFormat.setData(stockQuotations);
        }
        return layuiFormat;
    }

    @Override
    public void addStockQuotation(StockQuotation stockQuotation) {
        stockQuotation.setStockQuotationId(IDUtil.getUuid());
        stockQuotationDao.addStockQuotation(stockQuotation);
    }
}
