package com.yidu.bond.service;

import com.yidu.bond.paging.BondPaging;
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
}
