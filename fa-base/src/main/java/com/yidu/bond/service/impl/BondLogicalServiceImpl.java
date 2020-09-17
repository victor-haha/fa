package com.yidu.bond.service.impl;

import com.yidu.bond.dao.BondLogicalDao;
import com.yidu.bond.domain.BondTrade;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.bond.service.BondLogicalService;
import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.format.LayuiFormat;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;
import com.yidu.utils.DateUtils;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
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
            flag = addSIRecording(bondTradeId);
            //4.现金库存表添加|修改记录
            flag = addCIRecording(bondTradeId);

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
     * 4.现金库存表添加|修改记录
     * @param bondTradeId 债券交易id
     * @return 是否添加成功 1：添加失败，0：添加成功
     */
    private int addCIRecording(String bondTradeId) {
        int flag = 0;
        //4.1 通过bondTradeId查询交易记录
        BondTrade bondTrade = bondLogicalDao.findBondTradeById(bondTradeId);
        //4.2 按债券交易中的fundId和bondId,联表查询出现金对应的现金库存数据对象
        CashInventory cashInventory = bondLogicalDao.findCashInventory(bondTrade.getFundId(),bondTrade.getBondId());
        if(bondTrade.getTradeFlag()==1){  //流入，现金库存+
            cashInventory.setCashBalance(cashInventory.getCashBalance().add(bondTrade.getTurnover()));
        }else if(bondTrade.getTradeFlag() == 2){  //流出 现金库存-
            cashInventory.setCashBalance(cashInventory.getCashBalance().subtract(bondTrade.getTurnover()));
        }
        //4.3 判断此数据是否是今天的数据
            //是今天的数据，修改此对象现金库存数据
        if(cashInventory.getStatisticalDate().getTime()>= DateUtils.ZeroDate(new Date())){
            //修改更改账户统计日期
            cashInventory.setStatisticalDate(new Date());
            //4.3 将对应的现金账户更新
            flag = bondLogicalDao.updateCashInventory(cashInventory);
        }else{//不是今天的数据，添加今天新的现金库存数据
            //设置现金库存id
            cashInventory.setCashInventoryId(IDUtil.getUuid());
            //设置现金库存编号
            cashInventory.setCashInventoryNo(NoUtils.getNo("XJKC"));
            //修改更改账户统计日期
            cashInventory.setStatisticalDate(new Date());
            //4.3 添加今天新的现金库存数据
            flag = bondLogicalDao.addCashInventory(cashInventory);
        }


        return flag;
    }

    /**
     * 证券库存中添加|修改记录
     * @param bondTradeId 债券交易id
     * @return 是否添加成功 1：添加失败，0：添加成功
     */
    private int addSIRecording(String bondTradeId) {
        int flag = 0;
        //3.1 通过bondTradeId查询交易记录
        BondTrade bondTrade = bondLogicalDao.findBondTradeById(bondTradeId);
        //3.2 查询证券库存中是否有同一支基金的同一支债券（有：修改，没有：添加）
        SecuritiesInventory securitiesInventory =  bondLogicalDao.findBondInventory(bondTrade.getFundId(),bondTrade.getBondId());
        //如果securitiesInventory不为空，且交易数据的时间小于今天零点（今天没有数据），则添加
        if(null == securitiesInventory || securitiesInventory.getStatisticalDate().getTime()<DateUtils.ZeroDate(new Date())){
            //3.3/查询到账户信息，及单位成本，赋值到证券库存对象中
            securitiesInventory = bondLogicalDao.findSIByFundId(bondTrade.getFundId(),bondTrade.getBondTradeId());
            //设置相关初始信息，及相关数据到证券库存对象
            securitiesInventory.setSecuritiesInventoryId(IDUtil.getUuid());
            securitiesInventory.setSechuritiesInventoryNo(NoUtils.getNo("ZQJS"));
            securitiesInventory.setSecuritiesType(2);//1:股票，2:债券，3:银行存款
            securitiesInventory.setStatisticalDate(new Date());
            securitiesInventory.setDescription("债券交易数据清算");
            securitiesInventory.setSecuritiesId(bondTrade.getBondId());
            securitiesInventory.setSecuritiesNo(bondTrade.getBondCode());
            securitiesInventory.setSecuritiesName(bondTrade.getBondName());
            securitiesInventory.setFundId(bondTrade.getFundId());
            securitiesInventory.setFundNo(bondTrade.getFundNo());
            securitiesInventory.setFundName(bondTrade.getFundName());
            securitiesInventory.setShare(bondTrade.getShare());
            securitiesInventory.setTurnover(securitiesInventory.getPrice().multiply(new BigDecimal(bondTrade.getShare())));  //注意计算的精度问题

            //3.4添加证券库存数据
            flag = bondLogicalDao.addSecuritiesInventory(securitiesInventory);
        }else{//交易数据的时间大于今天零点（今天有数据），则修改
            //3.3存在则 修改数据
            //修改 更新数据时的日期
            securitiesInventory.setStatisticalDate(new Date());
            //判断是证券(债券)是  买入 or 卖出
            if(bondTrade.getTradeType() == 1){  //买入
                //持有份额+
                securitiesInventory.setShare(securitiesInventory.getShare().add(bondTrade.getShare()));
                //总金额+
                securitiesInventory.setTurnover(securitiesInventory.getTurnover().add(bondTrade.getTurnover()));//注意计算的精度问题
            }else{    //流出
                //持有份额-
                securitiesInventory.setShare(securitiesInventory.getShare().subtract(bondTrade.getShare()));
                //总金额+
                securitiesInventory.setTurnover(securitiesInventory.getTurnover().subtract(bondTrade.getTurnover()));//注意计算的精度问题
            }
            // 3.4 修改证券（债券）库存数据
            flag = bondLogicalDao.updateSecuritiesInventory(securitiesInventory);
        }
        return flag;
    }

    /**
     * 往资金调拨单表中添加记录
     * @param bondTradeId 债券交易id
     * @return 1:添加成功，0：添加失败
     */
    private int addCTRecording(String bondTradeId) {
        //2.1 根据bondTradeId查询出债券数据所需字段  并封装到‘资金调拨’对象
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

    /**
     * 债券计息处理
     * @param securitiesInventoryId 债券库存id
     * @return 1：计息成功，0：计息失败
     */
    @Override
    public int interestAccrual(String securitiesInventoryId) {

       // 2往证券应收应付发生表中添加数据

       // 3证券应收应付库存表添加或修改数据
       return 0;
    }
    /**
     * 查询需要计息的债券库存
     * @param securitiesInventoryPaging 搜索词条
     * @return layui格式的集合数据
     */
    @Override
    public LayuiFormat findInterestAccrual(SecuritiesInventoryPaging securitiesInventoryPaging) {
        //计算分页
        int limit = securitiesInventoryPaging.getLimit();
        int page = (securitiesInventoryPaging.getPage()-1)*limit;
        securitiesInventoryPaging.setPage(page);
        securitiesInventoryPaging.setLimit(limit);
        if(null != securitiesInventoryPaging.getEndStatisticalDate() && null != securitiesInventoryPaging.getStartStatisticalDate() ){
            if(securitiesInventoryPaging.getStartStatisticalDate().getTime()>securitiesInventoryPaging.getEndStatisticalDate().getTime()){
                //起始值大于终止值，则交换
                Date param = securitiesInventoryPaging.getStartStatisticalDate();
                securitiesInventoryPaging.setStartStatisticalDate(securitiesInventoryPaging.getEndStatisticalDate());
                securitiesInventoryPaging.setEndStatisticalDate(param);
            }
        }else{
            securitiesInventoryPaging.setEndStatisticalDate(new Date());
            try {
                securitiesInventoryPaging.setStartStatisticalDate(DateUtils.stringToDate("1900-01-01","yyyy-MM-dd"));
            } catch (Exception e) {
                layuiFormat.setCode(1);  //状态码0为查询到数据
                layuiFormat.setCount(0L);
                layuiFormat.setMsg("未查询到需要计息的数据哦!");
                layuiFormat.setData(null);
                return layuiFormat;
            }
        }
        List<SecuritiesInventory> securitiesInventorys = bondLogicalDao.findInterestAccrual(securitiesInventoryPaging);
        if (CollectionUtils.isEmpty(securitiesInventorys)){   //集合为空
            layuiFormat.setCode(1);  //状态码0为查询到数据
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("未查询到需要计息数据哦!");
            layuiFormat.setData(null);
        }else{
            layuiFormat.setCode(0);  //状态码0为查询到数据
            layuiFormat.setCount(bondLogicalDao.findInterestAccrualCount(securitiesInventoryPaging));
            layuiFormat.setMsg("成功找到数据");
            layuiFormat.setData(securitiesInventorys);
        }
        return layuiFormat;
    }

}
