package com.yidu.account.service.impl;

import com.yidu.account.dao.AccountDao;
import com.yidu.account.domain.Account;
import com.yidu.account.service.AccountBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类的描述：...
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/03
 */
@Service
public class AccountBizImpl implements AccountBiz {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Long countAccountByCondition(Map<String, Object> paramMap) {
        return accountDao.countAccountByCondition(paramMap);
    }

    @Override
    public List<Account> findAccountByConditionWithPaging(Map<String, Object> paramMap) {
        return accountDao.findAccountByConditionWithPaging(paramMap);
    }

    @Override
    public int addAccount(Account account) {
        return accountDao.addAccount(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountDao.updateAccount(account);
    }

    @Override
    public boolean updateAccountStatus(String accountIds, String usable) {
        String[] ids = accountIds.split(",");
        //定义修改状态的参数Map集合
        Map<String,Object> paramMap = new HashMap<>();
        //定义修改结果
        boolean result = false;
        paramMap.put("usable",usable);
        for (String accountId : ids) {
            paramMap.put("accountId",accountId);
            result = accountDao.updateAccountStatus(paramMap);
            if (!result) throw new RuntimeException("");
        }
        return result;
    }
}
