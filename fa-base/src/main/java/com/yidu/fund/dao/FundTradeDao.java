package com.yidu.fund.dao;

import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.domain.FundInventory;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.domain.FundTrade;
import com.yidu.fund.paging.FundTradePaging;
import com.yidu.manage.domain.Account;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/7 15:06
 */
public interface FundTradeDao {

    /**
     * 根据条件查找基金交易数据
     * @param fundTradePaging 查询条件
     * @return 基金交易数据集合
     */
    List<FundTrade> findFundTradeByCondition (FundTradePaging fundTradePaging);

    /**
     * 根据条件查找统计基金交易数据数量
     * @param fundTradePaging 查询条件
     * @return 数量
     */
    Long findCountFundTradeByCondition(FundTradePaging fundTradePaging);

    /**
     * 添加基金交易数据
     * @param fundTrade 基金交易数据对象
     * @return 成功添加返回true,否则返回false
     */
    boolean addFundTrade(FundTrade fundTrade);

    /**
     * 修改基金交易状态
     * @param paramMap 参数集合
     * @return 修改成功返回true
     */
    boolean updateTradeStatus(Map<String,Object> paramMap);

    /**
     * 根据基金交易Id查询基金交易数据
     * @param fundTradeId 基金交易Id
     * @return 查询成功返回基金交易对象,否则返回null
     */
    FundTrade findFundTradeById(String fundTradeId);

    /**
     * 添加资金调拨记录
     * @param capitalTransfer 资金调拨对象
     * @return 成功添加返回true,否则返回false
     */
    boolean addCapitalTransfer(CapitalTransfer capitalTransfer);

    /**
     * 根据基金库存id和统计时间查询基金库存
     * @param paramMap 查询参数集合
     * @return 成功查询返回基金库存对象,否则返回null
     */
    FundInventory findFundInventoryByIdAndStatisticalDate(Map<String,String> paramMap);

    /**
     * 添加基金库存信息
     * @param fundInventory 基金库存信息对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addFundInventory(FundInventory fundInventory);

    /**
     * 修改基金库存信息
     * @param fundInventory 基金库存信息对象
     * @return 修改成功返回true,否则返回false
     */
    boolean updateFundInventory(FundInventory fundInventory);

    /**
     * 根据基金id查询基金库存中指定基金的最后一次库存数据
     * @param fundId 基金Id
     * @return 基金库存对象
     */
    FundInventory findLastFIByFundId(String fundId);

    /**
     * 根据现金库存id和统计时间查询现金库存
     * @param paramMap 查询参数集合
     * @return 成功查询返回现金库存对象,否则返回null
     */
    CashInventory findCashInventoryByIdAndStatisticalDate(Map<String,String> paramMap);

    /**
     * 添加现金库存信息
     * @param cashInventory 现金库存对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addCashInventory(CashInventory cashInventory);

    /**
     * 修改现金库存信息
     * @param cashInventory 现金库存对象
     * @return 修改成功返回true,否则返回false
     */
    boolean updateCashInventory(CashInventory cashInventory);

    /**
     * 根据基金id查询现金库存中指定基金的最后一次库存数据
     * @param fundId 基金Id
     * @return 现金库存对象
     */
    CashInventory findLastCIByFundId(String fundId);
}
