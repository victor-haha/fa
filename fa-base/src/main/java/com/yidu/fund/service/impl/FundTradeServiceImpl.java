package com.yidu.fund.service.impl;

import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.format.LayuiFormat;
import com.yidu.fund.dao.FundDao;
import com.yidu.fund.dao.FundTradeDao;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.domain.FundTrade;
import com.yidu.fund.paging.FundTradePaging;
import com.yidu.fund.service.FundTradeService;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/7 15:38
 */
@Service
public class FundTradeServiceImpl implements FundTradeService {
    @Autowired
    private FundTradeDao fundTradeDao;
//    @Autowired
//    private  LayuiFormat layuiFormat;
    @Override
    public List<FundTrade>  findFundTradeByCondition(FundTradePaging fundTradePaging) {
        //计算分页
        fundTradePaging.setPage((fundTradePaging.getPage()-1)*fundTradePaging.getLimit());
        return fundTradeDao.findFundTradeByCondition(fundTradePaging);

    }

    @Override
    public Long findCountFundTradeByCondition(FundTradePaging fundTradePaging) {
        return fundTradeDao.findCountFundTradeByCondition(fundTradePaging);
    }

    @Override
    public boolean addFundTrade(List<FundTrade> fundTradeList) {
        boolean result = false;
        for (FundTrade trade : fundTradeList) {
            trade.setFundTradeId(IDUtil.getUuid());
//            trade.setFundTradeNo(NoUtils.getNo("JJJY"));
            result = fundTradeDao.addFundTrade(trade);
            if (!result)
                throw new RuntimeException("添加失败,请检查数据!");
        }
        return result;
    }

    @Override
    public boolean fundSettlement(String ids,String tradeStatus) {
        //分割基金交易Id字符串
        String[] fundTradeIds = ids.split(",");
        //先查询要结算的基金交易对象集合
        List<FundTrade> fundTradeList = new ArrayList<>();
        for (String fundTradeId : fundTradeIds) {
            fundTradeList.add(fundTradeDao.findFundTradeById(fundTradeId));
        }
        //调用修改交易状态方法
        boolean UTSResult = updateTradeStatus(fundTradeIds,tradeStatus);
        //调用添加调拨记录方法
        boolean ACTResult = addCapitalTransfer(fundTradeList);
        //调用结算至基金库存方法
        boolean STTIResult = settlementToFundInventory(fundTradeList);
        //调用结算至现金库存方法
        boolean STCIResult = settlementToCashInventory(fundTradeList);

        return UTSResult && ACTResult && STTIResult && STCIResult;
    }

    /**
     * 修改基金交易状态
     * @param fundTradeIds 基金交易Id字符数组
     * @param tradeStatus 交易状态
     * @return 修改成功返回true
     */
    private boolean updateTradeStatus(String[] fundTradeIds,String tradeStatus) {
        //声明布尔类型结果变量
        boolean result = false;
        //创建参数map集合对象
        Map<String,Object> paramMap = new HashMap<>();
        //封装前端交易状态到参数map集合
        paramMap.put("tradeStatus",tradeStatus);
        //遍历前端基金交易Id字符数组
        for (String fundTradeId : fundTradeIds) {
            //封装前端交易id到参数map集合
            paramMap.put("fundTradeId",fundTradeId);
            //修改基金交易状态
            result = fundTradeDao.updateTradeStatus(paramMap);
            if (!result)
                throw new RuntimeException("修改基金交易状态失败,请检查相关数据");
        }
        return result;
    }

    /**
     * 添加调拨记录
     * @param fundTradeList 基金交易对象集合
     * @return 成功添加返回true,否则返回false
     */
    private boolean addCapitalTransfer(List<FundTrade> fundTradeList) {
        //定义资金调拨对象
        CapitalTransfer capitalTransfer = new CapitalTransfer();
        //声明添加资金调拨结果变量
        boolean result = false;
        for (FundTrade fundTrade : fundTradeList) {
            //设置资金调拨id
            capitalTransfer.setCapitalTransferId(IDUtil.getUuid());
            //设置资金调拨编号
            capitalTransfer.setCapitalTransferNo(NoUtils.getNo("ZJDB"));
            capitalTransfer.setFundId(fundTrade.getFundId());
            capitalTransfer.setFundNo(fundTrade.getFundNo());
            capitalTransfer.setFundName(fundTrade.getFundName());
            capitalTransfer.setAccountId(fundTrade.getAccountId());
            capitalTransfer.setAccountNo(fundTrade.getAccountNo());
            capitalTransfer.setAccountName(fundTrade.getAccountName());
            capitalTransfer.setTransferAmount(fundTrade.getTurnover());
            //设置资金调拨日期
            capitalTransfer.setTransferDate(new Date());
            //设置资金调拨标识
            capitalTransfer.setTransferFlag(fundTrade.getTradeFlag());
            //设置资金调拨类型
            capitalTransfer.setTransferType("4");
            //添加资金调拨到数据库
            result = fundTradeDao.addCapitalTransfer(capitalTransfer);
            if (!result)
                throw new RuntimeException("资金调拨添加失败，请检查数据");
        }
        return result;
    }

    /**
     * 结算至基金库存
     * @param fundTradeList 基金交易对象集合
     * @return 成功添加返回true,否则返回false
     */
    private boolean settlementToFundInventory(List<FundTrade> fundTradeList){
        //声明执行结果变量
        boolean result = false;
        //定义查询参数map集合
        Map<String,String> paramMap = new HashMap<>();
        //定义日期格式化对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        //格式化当前时间
        String currentDate = df.format(new Date());
        //准备模糊查询统计日期条件数据 拼接百分号
        paramMap.put("statisticalDate",currentDate + "%");
        for (FundTrade fundTrade : fundTradeList) {
            //准备查询条件数据 基金id
            paramMap.put("fundId",fundTrade.getFundId());
            //查询现金库存中指定基金的当天基金库存数据
            FundInventory fundInventory = fundTradeDao.findFundInventoryByIdAndStatisticalDate(paramMap);
            System.out.println("fundInventory = " + fundInventory);
            //判断基金库存是否存在指定基金当天的库存数据
            if (fundInventory != null) {
                //调用计算基金库存余额和份额的方法
                calcBalanceAndShare(fundTrade,fundInventory);
                //如果有指定基金的当天基金库存数据,则调用数据访问对象方法修改该基金的当天基金库存数据
                result = fundTradeDao.updateFundInventory(fundInventory);
            }else{
                //调用数据访问方法查询该基金最近一次库存信息
                FundInventory lastFundInventory = fundTradeDao.findLastFIByFundId(fundTrade.getFundId());
                if ( null==lastFundInventory) {
                    throw new RuntimeException("找不到最近一次基金库存信息,请检查基金库中是否有对应的基金id");
                }
                System.out.println("lastFundInventory = " + lastFundInventory);
                //调用计算基金库存余额和份额的方法
                calcBalanceAndShare(fundTrade,lastFundInventory);
                //设置基金库存id
                lastFundInventory.setFundInventoryId(IDUtil.getUuid());
                //设置基金库存编号
                lastFundInventory.setFundInventoryNo(NoUtils.getNo("JJKC"));
                //如果没有指定基金的当天基金库存数据,则调用数据访问对象新增一条基金库存数据
                result = fundTradeDao.addFundInventory(lastFundInventory);
            }
        }
        return result;
    }

    /**
     * 计算基金库存余额和份额
     * @param fundTrade 基金交易对象
     * @param fundInventory 基金库存对象
     */
    private void calcBalanceAndShare(FundTrade fundTrade,FundInventory fundInventory){
        //获取交易类型
        String type = fundTrade.getFundTradeType();
        //获取交易金额
        BigDecimal turnover = fundTrade.getTurnover();
        //获取交易数量
        BigInteger share = fundTrade.getShare();
        //判断交易类型
        if ("3".equals(type)) {
            //赎回减少库存份额及余额
            fundInventory.setShare(fundInventory.getShare().subtract(share));
            fundInventory.setBalance(fundInventory.getBalance().subtract(turnover));
        }else{
            //申购或认购增加库存份额及余额
            fundInventory.setShare(fundInventory.getShare().add(share));
            fundInventory.setBalance(fundInventory.getBalance().add(turnover));
        }
        //设置统计日期
        fundInventory.setStatisticalDate(new Date());
    }

    /**
     * 结算至现金库存
     * @param fundTradeList 基金交易对象集合
     * @return 成功添加返回true,否则返回false
     */
    private boolean settlementToCashInventory(List<FundTrade> fundTradeList){
        //声明执行结果变量
        boolean result = false;
        //定义查询参数map集合
        Map<String,String> paramMap = new HashMap<>();
        //定义日期格式化对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //格式化当前时间
        String currentDate = df.format(new Date());
        //准备模糊查询统计日期条件数据 拼接百分号
        paramMap.put("statisticalDate",currentDate + "%");
        for (FundTrade fundTrade : fundTradeList) {
            //准备查询条件数据 基金id
            paramMap.put("fundId",fundTrade.getFundId());
            //查询现金库存中指定基金的当天现金库存数据
            CashInventory cashInventory = fundTradeDao.findCashInventoryByIdAndStatisticalDate(paramMap);
            System.out.println("cashInventory = " + cashInventory);
            //判断现金库存是否存在指定基金当天的库存数据
            if (cashInventory != null) {
                //调用计算现金余额的方法
                calcCashBalance(fundTrade,cashInventory);
                //如果有指定基金的当天现金库存数据,则调用数据访问对象方法修改该基金当天的现金库存数据
                result = fundTradeDao.updateCashInventory(cashInventory);
            }else{
                //调用数据访问方法查询指定基金最近一次现金库存信息
                CashInventory lastCashInventory = fundTradeDao.findLastCIByFundId(fundTrade.getFundId());
                if ( null==lastCashInventory) {
                    throw new RuntimeException("找不到最近一次现金库存信息,请检查现金库中是否有对应的基金id");
                }
                System.out.println("lastCashInventory = " + lastCashInventory);
                //调用计算现金余额的方法
                calcCashBalance(fundTrade,lastCashInventory);
                //设置现金库存id
                lastCashInventory.setCashInventoryId(IDUtil.getUuid());
                //设置现金库存编号
                lastCashInventory.setCashInventoryNo(NoUtils.getNo("XJKC"));
                //如果没有指定基金的当天现金库存数据,则调用数据访问对象新增一条现金库存数据
                result = fundTradeDao.addCashInventory(lastCashInventory);
            }
        }
        return result;
    }
    /**
     * 计算现金库存的现金余额
     * @param fundTrade 基金交易对象
     * @param cashInventory 现金库存对象
     */
    private void calcCashBalance(FundTrade fundTrade,CashInventory cashInventory){
        //获取交易类型
        String type = fundTrade.getFundTradeType();
        //获取交易金额
        BigDecimal turnover = fundTrade.getTurnover();
        //判断交易类型
        if ("3".equals(type)) {
            //赎回减少现金余额
            cashInventory.setCashBalance(cashInventory.getCashBalance().subtract(turnover));
        }else{
            //申购或认购增加现金余额
            cashInventory.setCashBalance(cashInventory.getCashBalance().add(turnover));
        }
        //设置统计日期
        cashInventory.setStatisticalDate(new Date());
    }
}


