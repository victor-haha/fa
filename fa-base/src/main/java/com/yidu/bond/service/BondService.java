package com.yidu.bond.service;

import com.yidu.bond.domain.Bond;
import com.yidu.bond.paging.BondPaging;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.format.LayuiFormat;


/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/9/3 10:12
 */
public interface BondService {
    /**
     * 查询所有债券对象
     * @return 债券对象集合
     */
    LayuiFormat findAll(BondPaging bondPaging);
    /**
     * 添加债券
     * @param bond 债券对象
     * @return 1:添加成功，0：添加失败
     */
    int addBond(Bond bond);
    /**
     * 修改债券
     * @param bond 修改对象
     * @return 1:修改成功，0：修改失败
     */
    int updateBond(Bond bond);

    /**
     * 修改债券是否可用
     * @param usable 是否可用参数
     * @param bondIds 多个id，“,”分割
     * @return 是否都修改成功
     */
    int updateUsable(String usable, String bondIds);

    /**
     * 搜索查询所有债券交易数据并分页
     * @param bondTradePaging 搜索分页参数
     * @return  债券交易集合的layui格式数据
     */
    LayuiFormat findBondTrade(BondTradePaging bondTradePaging);


    /**
     *
     * @param bondTradeId 债券交易数据id
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    int updateTradeStatus(String bondTradeId, String tradeStatus);
}
