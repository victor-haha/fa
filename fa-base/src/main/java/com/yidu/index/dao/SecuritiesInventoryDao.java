package com.yidu.index.dao;

import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;

import java.util.List;

/**
 * 类的描述:证券库存持久层接口类
 *
 * @author wh
 * @since 2020/9/14 9:09
 */
public interface SecuritiesInventoryDao {
    /**
     * 通过搜索词 查询所有
     * @param securitiesInventoryPaging 搜索词对象
     * @return 证券库存集合
     */
    List<SecuritiesInventory> findAll(SecuritiesInventoryPaging securitiesInventoryPaging);

    /**
     *  通过搜索词 查询所有的条数
     * @param securitiesInventoryPaging  搜索词对象
     * @return 条数
     */
    Long findCount(SecuritiesInventoryPaging securitiesInventoryPaging);

}
