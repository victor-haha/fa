package com.yidu.stock.service.impl;

import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.format.LayuiFormat;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.stock.dao.StockLogicalDao;
import com.yidu.stock.domain.StockTrade;
import com.yidu.stock.paging.StockTradePaging;
import com.yidu.stock.service.StockLogicalService;
import com.yidu.utils.DateUtils;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
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
        stockTrade.setStockId(IDUtil.getUuid());
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
        flag = addSIRecording(stockTradeId);
        //现金库存表中添加（或修改）记录    每天一条记录    买    余额减少    卖    余额增加
        flag = addCIRecording(stockTradeId);
        return flag;
    }

    private int addCIRecording(String stockTradeId) {
        int flag = 0;
        //4.1 通过stockTradeId查询交易记录
        StockTrade stockTrade = stockLogicalDao.findStockTradeById(stockTradeId);
        //4.2 按债券交易中的fundId和bondId,联表查询出现金对应的现金库存数据对象
        CashInventory cashInventory = stockLogicalDao.findCashInventory(stockTrade.getFundId(),stockTrade.getStockId());
        if(stockTrade.getTradeFlag()==1){  //流入，现金库存+
            cashInventory.setCashBalance(cashInventory.getCashBalance().add(stockTrade.getTurnover()));
        }else if(stockTrade.getTradeFlag() == 2){  //流出 现金库存-
            cashInventory.setCashBalance(cashInventory.getCashBalance().subtract(stockTrade.getTurnover()));
        }
        //4.3 判断此数据是否是今天的数据
        //是今天的数据，修改此对象现金库存数据
        if(cashInventory.getStatisticalDate().getTime()>= DateUtils.ZeroDate(new Date())){
            //修改更改账户统计日期
            cashInventory.setStatisticalDate(new Date());
            //4.3 将对应的现金账户更新
            flag = stockLogicalDao.updateCashInventory(cashInventory);
        }else{//不是今天的数据，添加今天新的现金库存数据
            //设置现金库存id
            cashInventory.setCashInventoryId(IDUtil.getUuid());
            //设置现金库存编号
            cashInventory.setCashInventoryNo(NoUtils.getNo("XJKC"));
            //修改更改账户统计日期
            cashInventory.setStatisticalDate(new Date());
            //4.3 添加今天新的现金库存数据
            flag = stockLogicalDao.addCashInventory(cashInventory);
        }
        return flag;
    }

    private int addSIRecording(String stockTradeId) {
        int flag=0;
        //3.1 通过stockTradeId查询交易记录
        StockTrade stockTrade = stockLogicalDao.findStockTradeById(stockTradeId);
        System.out.println(stockTrade.getFundId()+","+stockTrade.getStockId());
        //3.2 查询证券库存中是否有同一支基金的同一支债券（有：修改，没有：添加）
        SecuritiesInventory securitiesInventory =  stockLogicalDao.findStockInventory(stockTrade.getFundId(),stockTrade.getStockId());
        /*System.out.println("securitiesInventory"+securitiesInventory);
        System.out.println("StatisticalDate"+securitiesInventory.getStatisticalDate().getTime());
        System.out.println("ZeroDate"+DateUtils.ZeroDate(new Date()));*/
        //如果securitiesInventory不为空，且交易数据的时间小于今天零点（今天没有数据），则添加
        if(null == securitiesInventory || securitiesInventory.getStatisticalDate().getTime()< DateUtils.ZeroDate(new Date())){
            //3.3/查询到账户信息，及单位成本，赋值到证券库存对象中
            securitiesInventory = stockLogicalDao.findSIByFundId(stockTrade.getFundId(),stockTrade.getStockTradeId());
            //设置相关初始信息，及相关数据到证券库存对象
            securitiesInventory.setSecuritiesInventoryId(IDUtil.getUuid());
            securitiesInventory.setSechuritiesInventoryNo(NoUtils.getNo("GPJS"));
            securitiesInventory.setSecuritiesType(1);//1:股票，2:债券，3:银行存款
            securitiesInventory.setStatisticalDate(new Date());
            securitiesInventory.setDescription("股票交易数据清算");
            securitiesInventory.setSecuritiesId(stockTrade.getStockId());
            securitiesInventory.setSecuritiesNo(stockTrade.getStockCode());
            securitiesInventory.setSecuritiesName(stockTrade.getStockName());
            securitiesInventory.setFundId(stockTrade.getFundId());
            securitiesInventory.setFundNo(stockTrade.getFundNo());
            securitiesInventory.setFundName(stockTrade.getFundName());
            securitiesInventory.setShare(stockTrade.getShare());
            securitiesInventory.setTurnover(securitiesInventory.getPrice().multiply(new BigDecimal(stockTrade.getShare())));  //注意计算的精度问题
            System.out.println(securitiesInventory);
            //3.4添加证券库存数据
            flag = stockLogicalDao.addSecuritiesInventory(securitiesInventory);
        }else{//交易数据的时间大于今天零点（今天有数据），则修改
            //3.3存在则 修改数据
            //修改 更新数据时的日期
            securitiesInventory.setStatisticalDate(new Date());
            //判断是证券(债券)是  买入 or 卖出
            if(stockTrade.getTradeType() == 1){  //买入
                //持有份额+
                securitiesInventory.setShare(securitiesInventory.getShare()+(stockTrade.getShare()));
                //总金额+
                securitiesInventory.setTurnover(securitiesInventory.getTurnover().add(stockTrade.getTurnover()));//注意计算的精度问题
            }else{    //流出
                //持有份额-
                securitiesInventory.setShare(securitiesInventory.getShare()-(stockTrade.getShare()));
                //总金额+
                securitiesInventory.setTurnover(securitiesInventory.getTurnover().subtract(stockTrade.getTurnover()));//注意计算的精度问题
            }
            // 3.4 修改证券（债券）库存数据
            flag = stockLogicalDao.updateSecuritiesInventory(securitiesInventory);
        }
        return flag;
    }


    /**
     * 往资金调拨单表中添加记录
     * @param stockTradeId 股票交易id
     * @return
     */
    private int addCTRecording(String stockTradeId) {
        //2.1 根据stockTradeId查询出股票数据所需字段  并封装到‘资金调度’对象
        CapitalTransfer capitalTransfer = stockLogicalDao.findCapitalTransferByStockTradeId(stockTradeId);
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
