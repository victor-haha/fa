package com.yidu.deposit.dao;

import com.yidu.deposit.domain.DepositTrade;
import com.yidu.index.domain.SecuritiesArap;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.domain.SecuritiesarapInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：存款计息持久层接口
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/21
 */
public interface DepositInterestAccrualDao {
    /**
     * 1.1.查询满足条件的证券库存数
     * @param securitiesInventoryPaging 包含条件的POJO类
     * @return 成功返回数量，赋值返回0
     */
    long countDepositInterestAccrualByCondition(SecuritiesInventoryPaging securitiesInventoryPaging);

    /**
     * 1.2.查询满足条件的证券库存
     * @param securitiesInventoryPaging 包含条件的POJO类
     * @return 成功返回一个包含数据的集合，否则返回null
     */
    List<SecuritiesInventory> findSecuritiesInventoryByCondition(SecuritiesInventoryPaging securitiesInventoryPaging);

    /**
     * 2.1.1修改证券库存中银行存款的计息状态
     * @param paramMap 包含条件的map
     * @return 成功返回true，否则返回false
     */
    boolean updateDepositInterestAccrualStatus(Map<String,Object> paramMap);

    /**
     * 2.1.2根据证券库存Id查询数据
     * @param securitiesInventoryId 证券库存id
     * @return 成功返回对应数据，否则返回null
     */
    SecuritiesInventory findSecuritiesInventoryById (String securitiesInventoryId);

    /**
     * 2.2.1.根据银行交易数据ID查询交易数据
     * @param depositId 银行交易数据id
     * @return 银行交易数据
     */
    DepositTrade findDepositTradeById(String depositId);

    /**
     * 2.2.2.添加证券应收应付表数据
     * @param securitiesArap 证券应收应付表数据
     * @return 成功返回true，否则返回false
     */
    boolean addDepositInterestAccrual(SecuritiesArap securitiesArap);

    /**
     * 2.3.1根据条件查询证券应收应付库存表中数据
     * @param paramMap 包含条件的map
     * @return 成功返回证券应收应付库存对象，否则返回null
     */
    SecuritiesarapInventory findSecuritiesarapInventoryByCondition(Map<String,Object> paramMap);

    /**
     * 2.3.2添加证券应收应付库存数据
     * @param securitiesarapInventory 证券应收应付库存数据
     * @return 成功返回true，否则返回false
     */
    boolean addSecuritiesarapInventory(SecuritiesarapInventory securitiesarapInventory);

    /**
     * 2.3.3修改证券应收应付库存数据
     * @param paramMap 包含条件的map
     * @return 成功返回true，否则返回false
     */
    boolean updateSecuritiesarapInventory(Map<String,Object> paramMap);

    /**
     * 2.3.4查询证券应收应付最近的一条数据
     * @param securitiesId 证券id
     * @param fundId 基金id
     * @param accountId 账户id
     * @return 证券应收应付库存数据
     */
    SecuritiesarapInventory findSecuritiesarapInventoryById(@Param("securitiesId") String securitiesId, @Param("fundId") String fundId, @Param("accountId") String accountId);
}
