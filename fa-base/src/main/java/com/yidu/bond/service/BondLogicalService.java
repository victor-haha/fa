package com.yidu.bond.service;

import com.yidu.bond.domain.BondTrade;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.format.LayuiFormat;
import com.yidu.index.paging.SecuritiesInventoryPaging;

import java.math.BigInteger;

/**
 * 类的描述:债券详细业务的逻辑处理，业务逻辑层接口
 *
 * @author wh
 * @since 2020/9/8 13:06
 */
public interface BondLogicalService {
    /**
     * 搜索查询所有债券交易数据并分页
     * @param bondTradePaging 搜索分页参数
     * @return  债券交易集合的layui格式数据
     */
    LayuiFormat findBondTrade(BondTradePaging bondTradePaging);


    /**
     *  结算操作
     * @param bondTradeId 债券交易数据id
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    int settlementsT(String bondTradeId, String tradeStatus);

    /**
     * 添加债券交易数据
     * @param bondTrade 债券交易数据对象
     */
    void addBondTrade(BondTrade bondTrade);

    /**
     * 债券计息处理
     * @param securitiesInventoryId 债券库存id
     * @return 1：计息成功，0：计息失败
     */
    int interestAccrual(String securitiesInventoryId);

    /**
     * 查询需要计息的债券库存
     * @param securitiesInventoryPaging 搜索词条
     * @return layui格式的集合数据
     */
    LayuiFormat findInterestAccrual(SecuritiesInventoryPaging securitiesInventoryPaging);
}
