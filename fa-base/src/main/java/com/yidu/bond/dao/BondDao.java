package com.yidu.bond.dao;

import com.yidu.bond.domain.Bond;
import com.yidu.bond.paging.BondPaging;

import java.util.List;

/**
 * 类的描述:债券 数据操作类
 *
 * @author wh
 * @since 2020/9/3 10:10
 */
public interface BondDao {
    /**
     * 查询所有债券对象
     * @return 债券对象集合
     */
    List<Bond> findAll(BondPaging bondPaging);

    /**
     * 条件查询的数据条数
     * @return 数据条数
     */
    Long findCount(BondPaging bondPaging);

}
