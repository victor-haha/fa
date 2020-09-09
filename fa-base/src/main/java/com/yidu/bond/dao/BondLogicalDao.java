package com.yidu.bond.dao;

import com.yidu.bond.domain.BondTrade;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.capital.domain.CapitalTransfer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类的描述:债券详细业务的逻辑处理,数据操作层接口
 *
 * @author wh
 * @since 2020/9/8 13:08
 */
public interface BondLogicalDao {
    /**
     * 搜索查询所有债券交易数据并分页
     * @param bondTradePaging 搜索分页参数
     * @return  债券交易集合的layui格式数据
     */
    List<BondTrade> findBondTrade(BondTradePaging bondTradePaging);

    /**
     * 搜索查询所有债券交易数据条数
     * @param bondTradePaging 搜索分页参数
     * @return 数据条数
     */
    Long findBondTradeCount(BondTradePaging bondTradePaging);

    /**
     * 修改债券交易状态
     * @param bondTradeId 债券交易数据id
     * @param tradeStatus 交易状态
     * @return 1:修改成功，0：修改失败
     */
    int updateTradeStatus(@Param("bondTradeId") String bondTradeId, @Param("tradeStatus") String tradeStatus);
    /**
     * 添加债券交易数据
     * @param bondTrade 债券交易数据对象
     */
    void addBondTrade(BondTrade bondTrade);

    /**
     *  按bondTradeId 联表t_capital_transfer,t_account,t_bond_trade 查询 ，返回资金调度对象
     * @param bondTradeId 债券交易id
     * @return 资金调度表对象
     */
    CapitalTransfer findCapitalTransferByBondTradeId(String bondTradeId);

    /**
     * 向资金调拨表中插入数据
     * @param capitalTransfer 资金调拨对象
     * @return 是否插入成功 1：成功，0：失败
     */
    int addCapitalTransfer(CapitalTransfer capitalTransfer);
}
