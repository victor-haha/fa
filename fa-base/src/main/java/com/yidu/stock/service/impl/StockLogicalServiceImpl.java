package com.yidu.stock.service.impl;



import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.format.LayuiFormat;
import com.yidu.stock.dao.StockLogicalDao;
import com.yidu.stock.domain.StockTrade;
import com.yidu.stock.paging.StockTradePaging;
import com.yidu.stock.service.StockLogicalService;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service("StockLogicalService")
public class StockLogicalServiceImpl implements StockLogicalService {
    @Autowired
    private StockLogicalDao stockLogicalDao;
    @Autowired
    private LayuiFormat layuiFormat;


    @Override
    public LayuiFormat findStockTrade(StockTradePaging stockTradePaging) {
        int limit = stockTradePaging.getLimit();
        int page = (stockTradePaging.getPage()-1)*limit;
        stockTradePaging.setPage(page);
        stockTradePaging.setLimit(limit);
        List<StockTrade> stockTrades = stockLogicalDao.findStockTrade(stockTradePaging);
        System.out.println(stockTrades);
        if(CollectionUtils.isEmpty(stockTrades)){
            layuiFormat.setCode(1);  //状态码0为查询到数据
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("未查询到指定数据哦!");
        }else{
            layuiFormat.setCode(0);  //状态码0为查询到数据
            layuiFormat.setCount(stockLogicalDao.findStockTradeCount(stockTradePaging));
            layuiFormat.setMsg("成功找到数据");
            layuiFormat.setData(stockTrades);
        }
        return layuiFormat;
    }

    @Override
    public void addStockTrade(StockTrade stockTrade) {
        stockLogicalDao.addStockTrade(stockTrade);
    }



    @Override
    public int settlementsT(String stockTradeId, String tradeStatus) {
        int flag = 0;
        //修改股票交易数据的状态
        flag=stockLogicalDao.updateTradeStatus(stockTradeId,tradeStatus);
        //往资金调拨单中添加记录    买    流出    卖    流入
        flag=addCTRecording(stockTradeId);
        //证券库存表中添加|修改    每天有多条记录（一个股票一条）    买    份额增加    卖    份额减少

        //现金库存表中添加（或修改）记录    每天一条记录    买    余额减少    卖    余额增加

        return 1;
    }



    /**
     * 往资金调拨单表中添加记录
     * @param stockTradeId 股票交易id
     * @return
     */
    private int addCTRecording(String stockTradeId) {
        //2.1 根据stockTradeId查询出股票数据所需字段  并封装到‘资金调度’对象
        CapitalTransfer capitalTransfer = stockLogicalDao.findCapitalTransferByStockTradeId(stockTradeId);
        System.out.println(capitalTransfer);
        //2.2 设置capitalTransferId（uuid）
        capitalTransfer.setCapitalTransferId(IDUtil.getUuid());
        //2.3 设置调拨编号
        capitalTransfer.setCapitalTransferNo(NoUtils.getNo("ZJDB"));
        //2.4设置调拨日期
        capitalTransfer.setTransferDate(new Date());
        //2.5设置调拨类型（4.清算调拨）
        capitalTransfer.setTransferType("4");
        //2.6将查出的调拨单存入数据库表中
        int flag = stockLogicalDao.addCapitalTransfer(capitalTransfer);
        return flag;
    }


    /**
     * 往资金调拨单表中添加记录
     * @param stockTradeId 股票交易id
     * @return
     */
    private int addSecuritiesInventory(String stockTradeId) {
        //2.1 根据stockTradeId查询出股票数据所需字段  并封装到‘资金调度’对象
        CapitalTransfer capitalTransfer = stockLogicalDao.findCapitalTransferByStockTradeId(stockTradeId);
        System.out.println(capitalTransfer);
        //2.2 设置capitalTransferId（uuid）
        capitalTransfer.setCapitalTransferId(IDUtil.getUuid());
        //2.3 设置调拨编号
        capitalTransfer.setCapitalTransferNo(NoUtils.getNo("ZJDB"));
        //2.4设置调拨日期
        capitalTransfer.setTransferDate(new Date());
        //2.5设置调拨类型（4.清算调拨）
        capitalTransfer.setTransferType("4");
        //2.6将查出的调拨单存入数据库表中
        int flag = stockLogicalDao.addCapitalTransfer(capitalTransfer);
        return flag;
    }





}
