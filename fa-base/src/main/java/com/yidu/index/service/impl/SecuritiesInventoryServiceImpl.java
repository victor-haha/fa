package com.yidu.index.service.impl;

import com.yidu.index.dao.SecuritiesInventoryDao;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;
import com.yidu.index.service.SecuritiesInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类的描述:证券库存业务逻辑层实现类
 *
 * @author wh
 * @since 2020/9/14 9:10
 */
@Service
public class SecuritiesInventoryServiceImpl implements SecuritiesInventoryService {
    @Autowired
    private SecuritiesInventoryDao securitiesInventoryDao;

    @Override
    public List<SecuritiesInventory> findAll(SecuritiesInventoryPaging securitiesInventoryPaging) {
        return securitiesInventoryDao.findAll(securitiesInventoryPaging);
    }

    @Override
    public Long findCount(SecuritiesInventoryPaging securitiesInventoryPaging) {
        return securitiesInventoryDao.findCount(securitiesInventoryPaging);
    }
}
