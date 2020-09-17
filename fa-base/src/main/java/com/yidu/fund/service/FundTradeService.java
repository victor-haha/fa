package com.yidu.fund.service;

import com.yidu.format.LayuiFormat;
import com.yidu.fund.domain.FundTrade;
import com.yidu.fund.paging.FundTradePaging;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/7 15:37
 */
public interface FundTradeService {

    /**
     * 根据条件查找基金交易数据
     * @param fundTradePaging 查询条件
     * @return 基金交易数据集合
     */
    List<FundTrade>  findFundTradeByCondition (FundTradePaging fundTradePaging);

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
    boolean addFundTrade(List<FundTrade> fundTrade);

    /**
     * 基金结算
     * @param fundTradeIds 基金交易Id字符串数
     * @param tradeStatus 交易状态
     * @return 结算成功返回true
     */
    boolean fundSettlement(String fundTradeIds,String tradeStatus);


}
