package com.yidu.deposit.service.impl;

import com.yidu.capital.paging.CashInventoryPaging;
import com.yidu.deposit.dao.CashInventoryDao;
import com.yidu.deposit.domain.CashInventory;
import com.yidu.deposit.service.CashInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/9/11 14:40
 */
@Service
public class CashInventoryServiceImpl implements CashInventoryService {

    @Autowired
    private CashInventoryDao cashInventoryDao;

    @Override
    public List<CashInventory> findAll(CashInventoryPaging cashInventoryPaging) {
        return cashInventoryDao.findAll(cashInventoryPaging);
    }

    @Override
    public Long findCashInventoryCount(CashInventoryPaging cashInventoryPaging) {
        return cashInventoryDao.findCashInventoryCount(cashInventoryPaging);
    }
}
