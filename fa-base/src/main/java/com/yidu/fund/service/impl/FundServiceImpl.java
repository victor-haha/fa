package com.yidu.fund.service.impl;

import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.format.LayuiFormat;
import com.yidu.fund.dao.FundDao;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.paging.FundPaging;
import com.yidu.fund.service.FundService;
import com.yidu.manage.domain.Account;
import com.yidu.manage.domain.FundCompany;
import com.yidu.user.dao.UserDao;
import com.yidu.user.domain.User;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/3 15:05
 */
@Service
public class FundServiceImpl implements FundService {
    @Autowired
    private FundDao fundDao;
    @Autowired
    private LayuiFormat layuiFormat;
    @Autowired
    private UserDao userDao;

    @Override
    public LayuiFormat findAllFundWithPage(FundPaging fundPaging) {
        fundPaging.setPage((fundPaging.getPage()-1)*fundPaging.getLimit());
        List<Fund> fundList = fundDao.findAllFundWithPage(fundPaging);
        layuiFormat.setCode(0);
        layuiFormat.setMsg("OK");
        layuiFormat.setData(fundList);
        fundPaging.setLimit(null);
        fundPaging.setPage(null);
        Long count = fundDao.countFundByCondition(fundPaging);
        layuiFormat.setCount(count);
        return layuiFormat;
    }

    @Override
    public LayuiFormat findFundByCondition(FundPaging fundPaging) {
        //计算分页起始行
        fundPaging.setPage((fundPaging.getPage()-1)*fundPaging.getLimit());
        List<Fund> fundList = fundDao.findFundByCondition(fundPaging);
        if(fundList.isEmpty()){
            layuiFormat.setCode(0);
            layuiFormat.setMsg("fail");
            layuiFormat.setCount(0L);
            layuiFormat.setData(null);
        }else {
            layuiFormat.setCode(0);
            layuiFormat.setMsg("OK");
            layuiFormat.setData(fundList);
            fundPaging.setLimit(null);
            fundPaging.setPage(null);
            Long count = fundDao.countFundByCondition(fundPaging);
            layuiFormat.setCount(count);
        }
        return layuiFormat;
    }

    @Override
    public Long countFundByCondition(FundPaging fundPaging) {
        return fundDao.countFundByCondition(fundPaging);
    }


    @Override
    public int updateFund(Fund fund) {
       if(fundDao.updateFund(fund)){
           System.out.println("业务层:" + fund.getFundCompanyId() +"  " +fund.getManagerId());
           return 1;
       }
        return 0;
    }

    @Override
    public boolean updateFundStatus(String fundId,String usable) {
        String[] ids = fundId.split(",");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("usable",usable);
        boolean result = false;
        for (String id : ids) {
            paramMap.put("fundId",id);
            result = fundDao.updateFundStatus(paramMap);
            if(!result) throw  new RuntimeException("修改失败,请检查数据");
        }
        return result;
    }
    @Override
    public Map<String,Object> findUserAndFundCompany(){
        List<User> userList = userDao.findAllUser();
        List<FundCompany> fundCompanies = fundDao.findAllFundCompany();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("users",userList);
        resultMap.put("fundCompanies",fundCompanies);
        return resultMap;
    }

    @Override
    public Map<String,Object> addFund(Fund fund) {
        //调用Id工具类生成并设置基金Id
        fund.setFundId(IDUtil.getUuid());
        //设置可用状态为禁用
        fund.setUsable("N");
        //添加基金到数据库
        boolean fundFlag = fundDao.addFund(fund);
        //调用添加基金库存到数据库的方法
        boolean fundInventoryFlag = addFundInventory(fund);
        Map<String,Object> resultMap = new HashMap<>();
        if (fundFlag && fundInventoryFlag) {
            resultMap.put("result",1);
        }else{
            resultMap.put("result",0);
            throw new RuntimeException("保存失败,请检测数据");
        }
        return resultMap;
    }

    @Override
    public boolean addFundInventory(Fund fund) {
        //创建基金库存对象
        FundInventory fundInventory = new FundInventory();
        //根据基金对象设置库存信息
        fundInventory.setFundInventoryId(IDUtil.getUuid());
        fundInventory.setFundInventoryNo(NoUtils.getNo("JJKC"));
        fundInventory.setFundId(fund.getFundId());
        fundInventory.setFundNo(fund.getFundNo());
        fundInventory.setFundName(fund.getFundName());
        fundInventory.setShare(fund.getFundScale().divide(fund.getNav()).toBigInteger());
        fundInventory.setBalance(fund.getFundScale().multiply(fund.getNav()));
        fundInventory.setStatisticalDate(new Date());
        fundInventory.setDescription(fund.getDescription());
        //调用添加现金库存到数据库的方法
        boolean cashInventoryFlag = addCachInventory(fundInventory);
        //添加基金库存到数据库
        boolean fundInventoryFlag = fundDao.addFundInventory(fundInventory);
        return cashInventoryFlag && fundInventoryFlag;
    }

    @Override
    public boolean addCachInventory(FundInventory fundInventory) {
        //创建现金库存对象
        CashInventory cashInventory = new CashInventory();
        //根据基金库存设置现金库存信息
        cashInventory.setCashInventoryId(IDUtil.getUuid());
        cashInventory.setCashInventoryNo(NoUtils.getNo("XJKC"));
        cashInventory.setFundId(fundInventory.getFundId());
        cashInventory.setFundNo(fundInventory.getFundNo());
        cashInventory.setFundName(fundInventory.getFundName());
        cashInventory.setCashBalance(fundInventory.getBalance());
        cashInventory.setStatisticalDate(fundInventory.getStatisticalDate());
        cashInventory.setDescription(fundInventory.getDescription());
        //根据账户信息设置现金库存中的账户信息
        Account account = fundDao.findAccountByFundId(fundInventory.getFundNo());
        if (account != null) {
            cashInventory.setAccountId(account.getAccountId());
            cashInventory.setAccountNo(account.getAccountNo());
            cashInventory.setAccountName(account.getAccountName());
        }else{
            throw new RuntimeException("没有对应的账户信息,请先开户!");
        }

        //将现金库存信息添加到数据库
        return fundDao.addCashInventory(cashInventory);
    }
}
