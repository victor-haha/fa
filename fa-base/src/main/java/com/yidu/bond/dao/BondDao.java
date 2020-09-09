package com.yidu.bond.dao;

import com.yidu.bond.domain.Bond;
import com.yidu.bond.domain.BondTrade;
import com.yidu.bond.paging.BondPaging;
import com.yidu.bond.paging.BondTradePaging;
import com.yidu.format.LayuiFormat;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 添加债券
     * @param bond 债券对象
     * @return 是否添加成功 1:添加成功，0:添加失败
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
     * @param bondId 修改的id
     * @return 是否都修改成功
     */
    int updateUsable(@Param("usable") String usable, @Param("bondId") String bondId);

}
