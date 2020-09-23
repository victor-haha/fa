package com.yidu.fund.service.impl;

import com.yidu.deposit.dao.CashInventoryDao;
import com.yidu.deposit.dao.FundInventoryDao;
import com.yidu.deposit.domain.CashArap;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.CasharapInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.deposit.paging.CashInventoryPaging;
import com.yidu.deposit.paging.FundInventoryPaging;
import com.yidu.deposit.service.CashInventoryService;
import com.yidu.fund.dao.CalculateRevenueDao;
import com.yidu.fund.dao.FundDao;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.paging.CalculateRevenuePaging;
import com.yidu.fund.service.CalculateRevenueService;
import com.yidu.manage.domain.Account;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 类的描述：收益计提业务层
 * @Author 李昊林
 * @Date 2020/9/17 11:16
 */
@Service
public class CalculateRevenueServiceImpl implements CalculateRevenueService {
    @Autowired
    private CalculateRevenueDao calculateRevenueDao;
    @Autowired
    private FundInventoryDao fundInventoryDao;
    @Autowired
    private CashInventoryDao cashInventoryDao;
    @Autowired
    private FundDao fundDao;

    @Override
    public List<FundInventory> findFundInventoryByCondition(FundInventoryPaging fundInventoryPaging) {
        return calculateRevenueDao.findAllFIByConditionForNotAccrual(fundInventoryPaging);
    }

    @Override
    public Long findCountFundInventoryByCondition(FundInventoryPaging fundInventoryPaging) {
        return calculateRevenueDao.findCountByConditionForNotAccrual(fundInventoryPaging);
    }
    @Override
    public boolean doFeeCalculate(String fundInventoryIds){
        //分割基金库存id字符串
        String[] ids = fundInventoryIds.split(",");
        //声明计提结果变量
        boolean accrualResult = false;

        //遍历基金库存id查询基金库存对象封装到集合中
        for (String fundInventoryId : ids) {
            //获取基金库存对象
            FundInventory fundInventory = fundInventoryDao.findFundInventoryById(fundInventoryId);
            //获取基金对象
            Fund fund = fundDao.findFundById(fundInventory.getFundId());
            //调用计提方法
            accrualResult = doAccrual(fundInventory,fund);
        }
        return accrualResult;
    }

    /**
     * 计提业务操作
     * @param fundInventory 基金库存对象
     * @param fund 基金对象
     * @return 计提成功返回true,否则返回false
     */
    private boolean doAccrual(FundInventory fundInventory,Fund fund){
        //获取账户信息
        Account account = calculateRevenueDao.findAccountByFundId(fund.getFundId());
        //获取基金库存余额
        BigDecimal balance = fundInventory.getBalance();
        //获取基金表中的计费有效天数
        int billingDays = fund.getBillingDays();
        //获取基金表中的托管费率
        BigDecimal trusteeFee = fund.getTrusteeFee();
        //获取基金表的中管理费率
        BigDecimal managementFee = fund.getManagementFee();
        //计算托管费用
        BigDecimal calcTrusteeFee = balance.multiply(trusteeFee).divide(new BigDecimal("1000"),BigDecimal.ROUND_UNNECESSARY).divide(new BigDecimal(billingDays),4,BigDecimal.ROUND_HALF_UP);
        //计算管理费用
        BigDecimal calcManagementFee = balance.multiply(managementFee).divide(new BigDecimal("1000"),BigDecimal.ROUND_UNNECESSARY).divide(new BigDecimal(billingDays),4,BigDecimal.ROUND_HALF_UP);
        //设置现金应收应付对象
        CashArap cashArap = new CashArap();
        cashArap.setCashArapId(IDUtil.getUuid());
        cashArap.setCashArapNo(NoUtils.getNo("XJYSYF"));
        cashArap.setFundId(fund.getFundId());
        cashArap.setFundNo(fund.getFundNo());
        cashArap.setFundName(fund.getFundName());
        cashArap.setAccountId(account.getAccountId());
        cashArap.setAccountNo(account.getAccountNo());
        cashArap.setAccountName(account.getAccountName());
        cashArap.setArapDate(new Date());

        //设置托管费
        cashArap.setArapAmount(calcTrusteeFee);
        //设置应收应付标识:2应付
        cashArap.setArapFlag(2);
        //设置应收应付类型为托管费3
        cashArap.setArapType(3);
        //往现金应收应付表中插入托管费
        boolean addTrusteeFeeResult = calculateRevenueDao.addCashArap(cashArap);
        //调用处理现金应收应付库存业务方法操作托管费
        boolean PCIBTFResult = processingCasharapInventoryBusiness(cashArap);

        cashArap.setCashArapId(IDUtil.getUuid());
        cashArap.setCashArapNo(NoUtils.getNo("XJYSYF"));
        //设置管理费
        cashArap.setArapAmount(calcManagementFee);
        //设置应收应付类型为管理费4
        cashArap.setArapType(4);
        //往现金应收应付表中插入管理费
        boolean addManagementFeeResult = calculateRevenueDao.addCashArap(cashArap);
        //调用处理现金应收应付库存业务方法操作管理费
        boolean PCIBMFResult = processingCasharapInventoryBusiness(cashArap);
        //修改基金库存计提状态
        boolean updateAccrualStatusResult = calculateRevenueDao.updateAccrualStatus(fundInventory.getFundInventoryId());

        return addTrusteeFeeResult && PCIBTFResult && addManagementFeeResult && PCIBMFResult && updateAccrualStatusResult;
    }

    /**
     * 处理现金应收应付库存数据业务
     * @param cashArap 现金应收应付对象
     * @return 处理成功返回true,否则返回false
     */
    private boolean processingCasharapInventoryBusiness(CashArap cashArap){
        //定义查询参数map
        Map<String,Object> paramMap = new HashMap<>();
        //准备查询参数
        paramMap.put("fundId",cashArap.getFundId());
        paramMap.put("businessType",cashArap.getArapType());
        paramMap.put("businessDate",cashArap.getArapDate());
        //声明操作结果变量
        boolean result = false;
        //查询现金应收应付库存中是否有指定基金指定业务类型当天的应收应付库存数据
        CasharapInventory casharapInventory = calculateRevenueDao.findCARAPIByFundIdAndBusinessType(paramMap);
        if (casharapInventory != null) {
            //累加指定业务类型的数据
            casharapInventory.setBalance(casharapInventory.getBalance().add(cashArap.getArapAmount()));
            casharapInventory.setBusinessDate(cashArap.getArapDate());
            result = calculateRevenueDao.updateCasharapInventory(casharapInventory);
        }else{
            //查询最近日期的指定基金指定业务的现金应收应付库存数据
            CasharapInventory addCasharapInventory = calculateRevenueDao.findCARAPIByLatestDateWithFundIdAndBusinessType(paramMap);
            if (null == addCasharapInventory) {
                addCasharapInventory = new CasharapInventory();
                addCasharapInventory.setFundId(cashArap.getFundId());
                addCasharapInventory.setFundNo(cashArap.getFundNo());
                addCasharapInventory.setFundName(cashArap.getFundName());
                addCasharapInventory.setAccountId(cashArap.getAccountId());
                addCasharapInventory.setAccountNo(cashArap.getAccountNo());
                addCasharapInventory.setAccountName(cashArap.getAccountName());
                //设置初始值,避免累加出现空指针
                addCasharapInventory.setBalance(new BigDecimal("0"));
                addCasharapInventory.setBusinessType(cashArap.getArapType());
                addCasharapInventory.setFlag(cashArap.getArapFlag());
            }
            //根据现金应收应付数据对应设置新添加现金应收应付库存数据
            addCasharapInventory.setCachArapInventoryId(IDUtil.getUuid());
            addCasharapInventory.setCachArapInventoryNo(NoUtils.getNo("XJYSYFKC"));
            //累加最近日期的余额
            addCasharapInventory.setBalance(addCasharapInventory.getBalance().add(cashArap.getArapAmount()));
            addCasharapInventory.setBusinessDate(new Date());
            //往数据库中新增现金应收应付库存数据
            result= calculateRevenueDao.addCasharapInventory(addCasharapInventory);
        }
        return result;
    }



    @Override
    public List<CashInventory> findCashInventoryByCondition(CashInventoryPaging cashInventoryPaging) {
        cashInventoryPaging.setPage((cashInventoryPaging.getPage()-1)*cashInventoryPaging.getLimit());
        return calculateRevenueDao.findAllCIByConditionForNotInterest(cashInventoryPaging);
    }

    @Override
    public Long findCountCashInventoryByCondition(CashInventoryPaging cashInventoryPaging) {
        return calculateRevenueDao.findCountByConditionForNotInterest(cashInventoryPaging);
    }

    @Override
    public boolean doCashInterest(String cashInventoryIds) {
        //分割现金库存id字符串
        String[] ids = cashInventoryIds.split(",");
        //声明添加利息结果变量
        boolean addInterestResult = false;
        //声明添加现金应收应付库存结果变量
        boolean PCIBTFResult = false;
        //声明修改现金计息状态结果变量
        boolean updateInterestStatusResult = false;
        //遍历现金库存id查询现金库存
        for (String cashInventoryId : ids) {
            CashInventory cashInventory = cashInventoryDao.fundCashInventoryById(cashInventoryId);
            //获取基金对象
            Fund fund = fundDao.findFundById(cashInventory.getFundId());
            //获取基金表中的计费有效天数
            int billingDays = fund.getBillingDays();
            //计息 现金库存余额*利率0.35÷计费有效天数
            BigDecimal interest = cashInventory.getCashBalance().multiply(new BigDecimal("0.35")).divide(new BigDecimal(billingDays),4,BigDecimal.ROUND_HALF_UP);
            //设置现金应收应付对象
            CashArap cashArap = new CashArap();
            cashArap.setCashArapId(IDUtil.getUuid());
            cashArap.setCashArapNo(NoUtils.getNo("XJYSYF"));
            cashArap.setFundId(cashInventory.getFundId());
            cashArap.setFundNo(cashInventory.getFundNo());
            cashArap.setFundName(cashInventory.getFundName());
            cashArap.setAccountId(cashInventory.getAccountId());
            cashArap.setAccountNo(cashInventory.getAccountNo());
            cashArap.setAccountName(cashInventory.getAccountName());
            cashArap.setArapDate(new Date());
            //往现金应收应付余额中设置存款利息
            cashArap.setArapAmount(interest);
            //设置应收应付标识:1应收
            cashArap.setArapFlag(1);
            //设置应收应付类型为存款利息2
            cashArap.setArapType(2);
            //往现金应收应付表中插入存款利息
            addInterestResult = calculateRevenueDao.addCashArap(cashArap);
            //调用处理现金应收应付库存业务方法操作托管费
            PCIBTFResult = processingCasharapInventoryBusiness(cashArap);
            //修改现金库存计息状态
            updateInterestStatusResult = calculateRevenueDao.updateInterestStatus(cashInventory.getCashInventoryId());
        }
        return addInterestResult && PCIBTFResult && updateInterestStatusResult;
    }
}
