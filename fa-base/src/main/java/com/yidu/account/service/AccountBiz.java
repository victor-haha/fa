package com.yidu.account.service;

import com.yidu.account.domain.Account;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：...
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/23
 */
public interface AccountBiz {
    /**
     * 1.根据条件查询满足条件的现金账户数量
     * @param paramMap 查询条件的Map集合
     * @return 成功返回满足条件的现金账户，否则返回0
     */
    Long countAccountByCondition(Map<String,Object> paramMap);

    /**
     * 2.根据条件查询满足条件的现金账户
     * @param paramMap 查询条件的Map集合
     * @return 成功返回Account集合，否则返回null
     */
    List<Account> findAccountByConditionWithPaging(Map<String,Object> paramMap);

    /**
     * 3.添加现金账户
     * @param account 要添加的现金账户对象
     * @return 成功返回1，否则返回0
     */
    int addAccount(Account account);

    /**
     * 4.修改现金账户
     * @param account 要修改的现金账户
     * @return 成功返回1，否则返回0
     */
    int updateAccount(Account account);

    /**
     * 5.冻结或还原现金账户
     * @param accountIds 多个现金账户id拼接
     * @param usable 现金账户状态
     * @return 成功返回true，否则返回false
     */
    boolean updateAccountStatus(String accountIds,String usable);
}
