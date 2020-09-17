package com.yidu.capital.paging;

import java.math.BigInteger;

/**
 * 类的描述:现金库存搜索及分页所需参数分页
 *
 * @author wh
 * @since 2020/9/11 14:35
 */
public class CashInventoryPaging {
    /**
     * 现金库存编号
     */
    private String cashInventoryNo;
    /**
     * 基金编号
     */
    private String fundNo;
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 账户编号
     */
    private String accountNo;
    private int page;
    private int limit;

    public String getCachInventoryNo() {
        return cashInventoryNo;
    }

    public void setCachInventoryNo(String cachInventoryNo) {
        this.cashInventoryNo = cachInventoryNo;
    }

    public String getFundNo() {
        return fundNo;
    }

    public void setFundNo(String fundNo) {
        this.fundNo = fundNo;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

