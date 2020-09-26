package com.yidu.deposit.service.impl;

import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.dao.DepositTradeDao;
import com.yidu.deposit.domain.DepositTrade;
import com.yidu.deposit.paging.DepositTradePaging;
import com.yidu.deposit.service.DepositTradeBiz;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.utils.DateUtils;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;


/**
 * 类的描述：...
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/10
 */
@Service
public class DepositTradeBizImpl implements DepositTradeBiz {
    //自动注入
    @Autowired
    private DepositTradeDao depositTradeDao;

    @Override
    public int addDeposit(DepositTrade depositTrade) {
        return depositTradeDao.addDeposit(depositTrade);
    }

    @Override
    public long countDepositTradeByCondition(DepositTradePaging depositTradePaging) {
        long ret = 0;
        ret = depositTradeDao.countDepositTradeByCondition(depositTradePaging);
        return ret;
    }

    @Override
    public List<DepositTrade> findDepositByCondition(DepositTradePaging depositTradePaging) {
        //获取页数
        Integer page = depositTradePaging.getPage();
        //获取页面大小
        Integer limit = depositTradePaging.getLimit();
        //把页数转换成起始行号,并赋值个depositTradePaging对象的page值
        depositTradePaging.setPage((page-1)*limit);
        List<DepositTrade> ret = null;
        ret = depositTradeDao.findDepositByCondition(depositTradePaging);
        return ret;
    }

    @Override
    public boolean depositTradeSettlement(String depositIds, String tradeStatus) {
        //1.修改银行交易数据表中的状态
        boolean transactionStatus = updateTransactionStatus(depositIds,tradeStatus);
        //将银行交易数据字符串用','分割
        String[] Ids = depositIds.split(",");
        //声明一个银行交易数据集合接收查询结果
        List<DepositTrade> depositByCondition = new ArrayList<>();
        //循环查询银行交易数据
        for (String depositId : Ids){
            //调用depositTradeDao层findDepositByCondition方法查询满足条件的银行交易数据
            depositByCondition.add(depositTradeDao.findDepositById(depositId));
            //没有查询到银行交易数据，发生异常
            if (depositTradeDao.findDepositById(depositId)==null) throw new RuntimeException(depositId+"对应的数据没有");
        }
        //2.往资金调拨单表中添加记录  买流出   卖流入
        boolean bankTreasurer = addBankTreasurer(depositByCondition);
        //3.往证券库存表中添加|修改记录 买增加  卖减少
        boolean stockInventory =addOrUpdateStockInventory(depositByCondition);
        //4.往现金库存表添加|修改数据 买余额减少  卖余额增加
        boolean cashInventory = addOrUpdateCashInventory(depositByCondition);
        if (transactionStatus && bankTreasurer && stockInventory && cashInventory){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 3.1.修改银行交易数据状态
     * @param depositIds 银行交易数据ID字符串
     * @param tradeStatus 银行交易数据状态
     * @return 成功返回true，否则返回false
     */
    private boolean updateTransactionStatus(String depositIds, String tradeStatus) {
        //1.将银行交易数据字符串用','分割
        String[] Ids = depositIds.split(",");
        //定义修改状态的参数Map集合
        Map<String,Object> paramMap = new HashMap<>();
        //定义修改结果
        boolean result = false;
        paramMap.put("tradeStatus",tradeStatus);
        for (String depositId : Ids) {
            paramMap.put("depositId",depositId);
            result = depositTradeDao.updateTransactionStatus(paramMap);
            if (!result) throw new RuntimeException(paramMap.get(depositId) + "对应的数据修改失败");
        }
        return result;
    }

    /**
     * 3.2往资金调拨表中添加数据
     * @param depositTradeList 银行交易数据对象集合
     * @return 成功返回true，否则返回false
     */
    private boolean addBankTreasurer(List<DepositTrade> depositTradeList) {
        //定义修改结果
        boolean result = false;
        //声明一个资金调拨表对象
        CapitalTransfer capitalTransfer = new CapitalTransfer();
        //循环往资金调拨表中插入数据
        for (DepositTrade depositTrade : depositTradeList){
            //将资金调拨表的数据赋值
            //资金调拨表Id
            capitalTransfer.setCapitalTransferId(IDUtil.getUuid());
            //资金调拨表编号
            capitalTransfer.setCapitalTransferNo(NoUtils.getNo("ZJDB"));
            //参考基金表ID
            capitalTransfer.setFundId(depositTrade.getFundId());
            //参考基金表基金代码
            capitalTransfer.setFundNo(depositTrade.getFundNo());
            //参考基金表基金名
            capitalTransfer.setFundName(depositTrade.getFundName());
            //参考账户表，基金对应的现金账户,流出账户
            capitalTransfer.setAccountId(depositTrade.getOutAccountId());
            //参考账户表，基金对应的现金账户代码，流出账户代码
            capitalTransfer.setAccountNo(depositTrade.getOutAccountNo());
            //参考账户表，基金对应的现金账户名，流出账户名
            capitalTransfer.setAccountName(depositTrade.getOutAccountName());
            //调拨金额
            capitalTransfer.setTransferAmount(depositTrade.getMoney());
            //调拨日期
            capitalTransfer.setTransferDate(new Date());
            //调拨标识
            if (depositTrade.getTradeFlag() == 1){
                capitalTransfer.setTransferFlag(BigInteger.valueOf(depositTrade.getTradeFlag()));
            }else if (depositTrade.getTradeFlag() == 2){
                capitalTransfer.setTransferFlag(BigInteger.valueOf(depositTrade.getTradeFlag()));
            }else {
                throw new RuntimeException("没有调拨标识");
            }
            //调拨类型
            capitalTransfer.setTransferType("4");
            //调用addBankTreasurer方法添加资金调拨
            result = depositTradeDao.addBankTreasurer(capitalTransfer);
            //没有查询到银行交易数据，发生异常
            if (!result) throw new RuntimeException(capitalTransfer.getCapitalTransferId() + "添加失败");
        }
        return result;
    }

    /**
     * 3.3修改或添加证券库存表
     * @param depositTradeList 银行交易数据对象集合
     * @return 成功返回true，否则返回false
     */
    private boolean addOrUpdateStockInventory(List<DepositTrade> depositTradeList) {
        //定义添加或修改结果
        boolean result = false;
        //定义一个参数map
        Map<String,Object> paramMap = new HashMap<>();
        //定义一个参数map
        Map<String,Object> paramMap2 = new HashMap<>();
        SecuritiesInventory securitiesInventory = new SecuritiesInventory();
        for (DepositTrade depositTrade : depositTradeList){
            paramMap.put("accountId",depositTrade.getOutAccountId());
            paramMap.put("fundId",depositTrade.getFundId());
            paramMap.put("securitiesName",depositTrade.getBusinessType());
            paramMap.put("securitiesType",3);
            paramMap.put("statisticalDate", new Date(DateUtils.ZeroDate(new Date())));
            //查询证券库存表中今天的数据
            SecuritiesInventory sec = depositTradeDao.findSecuritiesInventoryByCondition(paramMap);
            if (sec==null){
                //没有证券库存数据，就添加一条
                //证券库存ID
                securitiesInventory.setSecuritiesInventoryId(IDUtil.getUuid());
                //证券库存编号
                securitiesInventory.setSechuritiesInventoryNo(NoUtils.getNo("ZQKC"));
                //
                securitiesInventory.setSecuritiesId(depositTrade.getDepositId());
                //证券名
                securitiesInventory.setSecuritiesName(String.valueOf(depositTrade.getBusinessType()));
                //参考基金表，基金ID
                securitiesInventory.setFundId(depositTrade.getFundId());
                //参考基金表,基金编号
                securitiesInventory.setFundNo(depositTrade.getFundNo());
                //参考基金表,基金名
                securitiesInventory.setFundName(depositTrade.getFundName());
                //参考账户表，基金对应的现金托管账户ID
                securitiesInventory.setAccountId(depositTrade.getOutAccountId());
                //参考账户表，基金对应的现金托管账户编号
                securitiesInventory.setAccountNo(depositTrade.getOutAccountNo());
                //参考账户表，基金对应的现金托管账户名
                securitiesInventory.setAccountName(depositTrade.getOutAccountName());
                //证劵类型,1股票   2债券   3 银行存款
                securitiesInventory.setSecuritiesType(3);
                //金额
                securitiesInventory.setTurnover(depositTrade.getMoney());
                //统计日期,交易结算后进行统计的日期
                securitiesInventory.setStatisticalDate(new Date());
                securitiesInventory.setDescription(depositTrade.getDescription());
                securitiesInventory.setTradeStatus(0);
                //当天没有结算数据，就添加一条结算数据
                result = depositTradeDao.addSecuritiesInventory(securitiesInventory);
                if (!result) throw new  RuntimeException("添加失败");
            }else {
                //有就修改证券库存表中对应的数据
                if (depositTrade.getTradeType()==1){
                    paramMap2.put("turnover",depositTrade.getMoney().negate());
                }else {
                    paramMap2.put("turnover",depositTrade.getMoney());
                }
                paramMap2.put("statisticalDate",new Date());
                paramMap2.put("securitiesInventoryId",sec.getSecuritiesInventoryId());
                result = depositTradeDao.updateSecuritiesInventory(paramMap2);
            }
        }
        return result;
    }

    /**
     * 3.4添加或修改现金库存表
     * @param depositTradeList 银行交易数据对象集合
     * @return 成功返回true，否则返回false
     */
    private boolean addOrUpdateCashInventory(List<DepositTrade> depositTradeList) {
        //定义添加或修改结果
        boolean result = false;
        //定义
        CashInventory cashInventory = new CashInventory();
        //定义一个参数map
        Map<String,Object> paramMap = new HashMap<>();
        Map<String,Object> paramMap2 = new HashMap<>();
        CashInventory cashInventory1 = null;
        CashInventory cashInventory2 = new CashInventory();
        //循环遍历银行交易数据
        for (DepositTrade depositTrade : depositTradeList){
            paramMap.put("fundId",depositTrade.getFundId());
            paramMap.put("StatisticalDate",new Date(DateUtils.ZeroDate(new Date())));
            //查询现金库存表中今天是否有符合条件的对象
            cashInventory1 = depositTradeDao.findCashInventoryByCondition(paramMap);
            if (cashInventory1 == null){
                //没有当天的现金库存数据，就添加一条
                //查询前一天的现金库存数据
                cashInventory2 = depositTradeDao.findCashInventoryByFundId(depositTrade.getFundId(),depositTrade.getOutAccountId());
                //现金库存ID
                cashInventory.setCashInventoryId(IDUtil.getUuid());
                //现金库存编号
                cashInventory.setCashInventoryNo(NoUtils.getNo("XJKC"));
                //基金ID
                cashInventory.setFundId(depositTrade.getFundId());
                //基金编号
                cashInventory.setFundNo(depositTrade.getFundNo());
                //基金名
                cashInventory.setFundName(depositTrade.getFundName());
                //现金账户ID
                cashInventory.setAccountId(depositTrade.getOutAccountId());
                //现金账户号
                cashInventory.setAccountNo(depositTrade.getOutAccountNo());
                //账户名
                cashInventory.setAccountName(depositTrade.getOutAccountName());
                if (depositTrade.getTradeType() == 1){
                    //现金余额,定存减少
                    cashInventory.setCashBalance(cashInventory2.getCashBalance().subtract(depositTrade.getMoney()));
                }else {
                    //现金余额，定存回收
                    cashInventory.setCashBalance(cashInventory2.getCashBalance().add(depositTrade.getMoney()) );
                }
                //统计日期
                cashInventory.setStatisticalDate(new Date());
                //描述
                cashInventory.setDescription(depositTrade.getDescription());
                result = depositTradeDao.addCashInventory(cashInventory);
            }else {
                //有，修改当天数据
                if (depositTrade.getTradeType() == 1){
                    //现金余额,定存减少
                    paramMap2.put("cashBalance",cashInventory2.getCashBalance().subtract(depositTrade.getMoney()));
                }else {
                    //现金余额，定存回收
                    paramMap2.put("cashBalance",cashInventory2.getCashBalance().add(depositTrade.getMoney()));
                }
                paramMap2.put("cashInventoryId",cashInventory2.getCashInventoryId());
                paramMap2.put("StatisticalDate",new Date());
                result = depositTradeDao.updateCashInventory(paramMap2);
            }
        }
        return result;
    }
}
