package com.yidu.fund.paging;

/**
 * 类的描述：收益计提分页字段
 * @Author 李昊林
 * @Date 2020/9/15 18:13
 */
public class CalculateRevenuePaging {
    private int page;
    private int limit;
    private String fundInventoryNo;
    private String fundNo;
    private String fundName;
    private String statisticalDateStart;
    private String statisticalDateEnd;
    private String cashInventoryNo;
    private String accountNo;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = (page-1)* limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getFundInventoryNo() {
        return fundInventoryNo;
    }

    public void setFundInventoryNo(String fundInventoryNo) {
        this.fundInventoryNo = fundInventoryNo;
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
        this.fundName = "%"+fundName+"%";
    }

    public String getStatisticalDateStart() {
        return statisticalDateStart;
    }

    public void setStatisticalDateStart(String statisticalDateStart) {
        this.statisticalDateStart = statisticalDateStart;
    }

    public String getStatisticalDateEnd() {
        return statisticalDateEnd;
    }

    public void setStatisticalDateEnd(String statisticalDateEnd) {
        this.statisticalDateEnd = statisticalDateEnd;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
