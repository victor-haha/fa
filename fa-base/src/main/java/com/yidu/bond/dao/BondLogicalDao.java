package com.yidu.bond.dao;

import com.yidu.bond.domain.BondTrade;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.capital.domain.CapitalTransfer;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
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

    /**
     *  通过bondTradeId查询交易记录
     * @param bondTradeId 债券交易id
     * @return 债券交易对象
     */
    BondTrade findBondTradeById(String bondTradeId);

    /**
     * 根据基金id 和 债券id 查询证券库存数据 (用于判断是否有同一支基金的同一支债券)
     * @param fundId 基金id
     * @param bondId 债券id
     * @return 券库存对象
     */
    SecuritiesInventory findBondInventory(@Param("fundId") String fundId, @Param("bondId") String bondId);

    /**
     * 查询到账户信息，及单位成本，赋值到证券库存对象中
     * @param fundId 基金id
     * @return 券库存对象
     */
    SecuritiesInventory findSIByFundId(@Param("fundId") String fundId, @Param("bondTradeId") String bondTradeId);

    /**
     * 添加证券库存数据
     * @param securitiesInventory 债券库存对象
     * @return 是否插入成功， 1：成功，0：失败
     */
    int addSecuritiesInventory(SecuritiesInventory securitiesInventory);

    /**
     * 修改证券（债券）库存数据
     * @param securitiesInventory 债券库存对象
     * @return 是否修改成功， 1：成功，0：失败
     */
    int updateSecuritiesInventory(SecuritiesInventory securitiesInventory);

    /**
     * 按债券交易中的fundId和bondId,联表查询出现金对应的现金库存数据对象
     * @param fundId 基金id
     * @param bondId 债券id
     * @return 现金库存对象
     */
    CashInventory findCashInventory(@Param("fundId") String fundId,@Param("securitiesId") String bondId);

    /**
     * 将对应的现金账户更新
     * @param cashInventory 现金库存对象
     * @return 是否更新成功 1：成功，0：失败
     */
    int updateCashInventory(CashInventory cashInventory);

    /**
     * 添加今天新的现金库存数据
     * @param cashInventory 现金库存对象
     * @return 是否添加成功 1：成功，0：失败
     */
    int addCashInventory(CashInventory cashInventory);
    /**
     * 查询需要计息的债券库存
     * @param securitiesInventoryPaging 搜索词条
     * @return layui格式的集合数据
     */
    List<SecuritiesInventory> findInterestAccrual(SecuritiesInventoryPaging securitiesInventoryPaging);
    /**
     * 查询需要计息的债券库存数量
     * @param securitiesInventoryPaging 搜索词条
     * @return layui格式的集合数据
     */
    Long findInterestAccrualCount(SecuritiesInventoryPaging securitiesInventoryPaging);
}
