package com.yidu.bond.service.impl;

import com.yidu.bond.dao.BondLogicalDao;
import com.yidu.bond.domain.BondTrade;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.bond.service.BondLogicalService;
import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.format.LayuiFormat;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 类的描述:债券详细业务的逻辑处理,业务逻辑实现层
 *
 * @author wh
 * @since 2020/9/8 13:07
 */
@Service
public class BondLogicalServiceImpl implements BondLogicalService {
    @Autowired
    private BondLogicalDao bondLogicalDao;
    @Autowired
    private LayuiFormat layuiFormat;
    @Override
    public LayuiFormat findBondTrade(BondTradePaging bondTradePaging) {
        int limit = bondTradePaging.getLimit();
        int page = (bondTradePaging.getPage()-1)*limit;
        bondTradePaging.setPage(page);
        bondTradePaging.setLimit(limit);
        List<BondTrade> bondTrades = bondLogicalDao.findBondTrade(bondTradePaging);
        if(CollectionUtils.isEmpty(bondTrades)){
            layuiFormat.setCode(1);  //状态码0为查询到数据
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("未查询到指定数据哦!");
        }else{
            layuiFormat.setCode(0);  //状态码0为查询到数据
            layuiFormat.setCount(bondLogicalDao.findBondTradeCount(bondTradePaging));
            layuiFormat.setMsg("成功找到数据");
            layuiFormat.setData(bondTrades);
        }
        return layuiFormat;
    }

    @Override
    public int settlementsT(String bondTradeId, String tradeStatus) {
        int flag = 0;
        try {
            //1.修改债券交易数据的状态
            flag =  bondLogicalDao.updateTradeStatus(bondTradeId,tradeStatus);
            //2.往资金调拨单表中添加记录
            flag = addCTRecording(bondTradeId);
            //3.证券库存中添加|修改记录
            //3.1 通过bondTradeId查询交易记录
//            BondTrade bondTrade = bondLogicalDao.findBondTradeById(bondTradeId);
            //3.2 查询证券库存中是否有同一支基金的同一支债券（如果有，查询日期最晚的一条数据）
//            SecuritiesInventory securitiesInventory =  bondLogicalDao.findBondInventory(bondTrade.getFundId(),bondTrade.getBondId());







            System.out.println("证券库存中添加|修改记录");
            //4.现金库存表添加|修改记录
            System.out.println("现金库存表添加|修改记录");
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
            //手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally {
            return flag;
        }
    }

    /**
     * 往资金调拨单表中添加记录
     * @param bondTradeId 债券交易id
     * @return
     */
    private int addCTRecording(String bondTradeId) {
        //2.1 根据bondTradeId查询出债券数据所需字段  并封装到‘资金调度’对象
        CapitalTransfer capitalTransfer = bondLogicalDao.findCapitalTransferByBondTradeId(bondTradeId);
        //2.2 设置capitalTransferId（uuid）
        capitalTransfer.setCapitalTransferId(IDUtil.getUuid());
        //2.3 设置调拨编号
        capitalTransfer.setCapitalTransferNo(NoUtils.getNo("ZJDB"));
        //2.4设置调拨日期
        capitalTransfer.setTransferDate(new Date());
        //2.5设置调拨类型（4.清算调拨）
        capitalTransfer.setTransferType("4");
        //2.6将查出的调拨单存入数据库表中
        int flag = bondLogicalDao.addCapitalTransfer(capitalTransfer);
        return flag;
    }

    /**
     * 添加债券交易数据
     * @param bondTrade 债券交易数据对象
     */
    @Override
    public void addBondTrade(BondTrade bondTrade) {
        //uuid生成BondId
        bondTrade.setBondTradeId(IDUtil.getUuid());
        bondTrade.setBondTradeNo(NoUtils.getNo("XQJY"));
        bondLogicalDao.addBondTrade(bondTrade);
    }
}
