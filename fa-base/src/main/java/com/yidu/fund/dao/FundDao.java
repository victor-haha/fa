package com.yidu.fund.dao;

import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.paging.FundPaging;
import com.yidu.manage.domain.Account;
import com.yidu.manage.domain.FundCompany;

import javax.security.auth.login.AccountException;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/3 8:34
 */
public interface FundDao {
    /**
     * 查询所有基金信息并分页
     * @return 基金对象集合
     */
    List<Fund> findAllFundWithPage(FundPaging fundPaging);

    /**
     * 根据基金id查询基金信息
     * @param fundId 基金id
     * @return 基金对象
     */
    Fund findFundById(String fundId);

    /**
     * 根据条件查询基金信息
     * @param fundPaging 查询条件
     * @return 基金对象集合
     */
    List<Fund> findFundByCondition(FundPaging fundPaging);

    /**
     * 根据条件统计基金信息条数
     * @param fundPaging 查询条件
     * @return 基金对象
     */
    Long countFundByCondition(FundPaging fundPaging);

    /**
     * 添加基金信息
     * @param fund 基金对象
     * @return 成功添加返回true,否则返回false
     */
    boolean addFund(Fund fund);

    /**
     * 修改基金信息
     * @param fund 基金对象
     * @return 成功修改返回true,否则返回false
     */
    boolean updateFund(Fund fund);

    /**
     * 修改基金可用状态
     * @param paramMap 修改基金Id和状态信息
     * @return 成功修改返回true,否则返回false
     */
    boolean updateFundStatus(Map<String,Object> paramMap);

    /**
     * 添加基金库存信息
     * @param fundInventory 基金库存信息对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addFundInventory(FundInventory fundInventory);

    /**
     * 根据基金Id查询对应的账户信息
     * @param fundNo 基金编号
     * @return 账户对象
     */
    Account findAccountByFundId(String fundNo);

    /**
     * 查询所有基金公司
     * @return 基金公司对象
     */
    List<FundCompany> findAllFundCompany();

    /**
     * 添加现金库存信息
     * @param cashInventory 现金库存对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addCashInventory(CashInventory cashInventory);
}
