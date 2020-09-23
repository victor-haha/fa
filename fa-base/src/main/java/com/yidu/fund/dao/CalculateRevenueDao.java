package com.yidu.fund.dao;

import com.yidu.deposit.domain.CashArap;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.CasharapInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.deposit.paging.CashInventoryPaging;
import com.yidu.deposit.paging.FundInventoryPaging;
import com.yidu.manage.domain.Account;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：收益计提数据访问层
 * @Author 李昊林
 * @Date 2020/9/17 11:00
 */
public interface CalculateRevenueDao {
    /**
     * 按条件查询所有未计提基金库存
     * @param fundInventoryPaging 基金库存分页字段
     * @return 基金库存对象集合
     */
    List<FundInventory> findAllFIByConditionForNotAccrual(FundInventoryPaging fundInventoryPaging);
    /**
     * 按条件统计未计提基金库存数量
     * @param fundInventoryPaging 基金库存分页字段
     * @return 基金库存数量
     */
    Long findCountByConditionForNotAccrual(FundInventoryPaging fundInventoryPaging);

    /**
     * 根据基金库存id修改计提状态
     * @param fundInventoryId 基金库存id
     * @return 修改成功返回true,否则返回false
     */
    boolean updateAccrualStatus(String fundInventoryId);

    /**
     * 按条件查询所有未计息现金库存
     * @param cashInventoryPaging 现金库存分页字段
     * @return 现金库存对象集合
     */
    List<CashInventory> findAllCIByConditionForNotInterest(CashInventoryPaging cashInventoryPaging);
    /**
     * 按条件统计未计息现金库存数量
     * @param cashInventoryPaging 现金库存分页字段
     * @return 现金库存数量
     */
    Long findCountByConditionForNotInterest(CashInventoryPaging cashInventoryPaging);

    /**
     * 根据现金库存id修改计息状态
     * @param cashInventoryId 现金库存id
     * @return 修改成功返回true,否则返回false
     */
    boolean updateInterestStatus(String cashInventoryId);

    /**
     * 根据基金id查询账户信息
     * @param fundId 基金id
     * @return 账户对象
     */
    Account findAccountByFundId(String fundId);

    /**
     * 添加现金应收应付数据
     * @param cashArap 现金应收应付对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addCashArap(CashArap cashArap);

    /**
     * 根据基金id及业务类型查询现金应收应付库存中指定基金当天的库存数据
     * @param paramMap 查询参数map
     * @return 现金应收应付库存对象
     */
    CasharapInventory findCARAPIByFundIdAndBusinessType(Map<String,Object> paramMap);
    /**
     * 添加现金应收应付库存数据
     * @param casharapInventory 现金应收应付库存对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addCasharapInventory(CasharapInventory casharapInventory);

    /**
     * 修改现金应收应付库存数据
     * @param casharapInventory 现金应收应付库存对象
     * @return 修改成功返回true,否则返回false
     */
    boolean updateCasharapInventory(CasharapInventory casharapInventory);

    /**
     * 查询最近日期的指定基金指定业务的现金应收应付库存数据
     * @param paramMap 查询参数条件
     * @return 现金应收应付库存对象
     */
    CasharapInventory findCARAPIByLatestDateWithFundIdAndBusinessType(Map<String,Object> paramMap);



}
