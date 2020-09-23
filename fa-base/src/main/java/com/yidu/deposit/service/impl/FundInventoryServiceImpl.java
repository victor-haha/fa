package com.yidu.deposit.service.impl;


import com.yidu.deposit.domain.FundInventory;
import com.yidu.deposit.dao.FundInventoryDao;
import com.yidu.deposit.paging.FundInventoryPaging;
import com.yidu.deposit.service.FundInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类的描述：基金库存业务层实现类
 * @Author 李昊林
 * @Date 2020/9/15 18:29
 */
@Service
public class FundInventoryServiceImpl implements FundInventoryService {
    @Autowired
    private FundInventoryDao fundInventoryDao;
    @Override
    public List<FundInventory> findFundInventoryByCondition(FundInventoryPaging fundInventoryPaging) {
        System.out.println("fundInventoryPaging.getFundName() = " + fundInventoryPaging.getFundName());
        return fundInventoryDao.findFundInventoryByCondition(fundInventoryPaging);
    }

    @Override
    public Long findCountByCondition(FundInventoryPaging fundInventoryPaging) {
        return fundInventoryDao.findCountByCondition(fundInventoryPaging);
    }
}
