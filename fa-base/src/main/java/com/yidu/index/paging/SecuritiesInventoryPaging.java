package com.yidu.index.paging;

import java.math.BigInteger;
import java.util.Date;

/**
 * 类的描述:证券库存搜索字段实体对象
 *
 * @author wh
 * @since 2020/9/14 21:49
 */
public class SecuritiesInventoryPaging {
    /**
     * 证券编号
     */
    private String securitiesNo;

    /**
     * 证券名
     */
    private String securitiesName;
    /**
     * 基金代码
     */
    private String fundNo;

    /**
     * 基金名
     */
    private String fundName;
    /**
     * 证劵类型，1股票   2债券   3 银行存款
     */
    private int securitiesType;
    /**
     * 统计日期，搜索起始日期
     */
    private Date startStatisticalDate;
    /**
     * 统计日期，搜索终止日期
     */
    private Date endStatisticalDate;

    private int page;

    private int limit;

    public String getSecuritiesNo() {
        return securitiesNo;
    }

    public void setSecuritiesNo(String securitiesNo) {
        this.securitiesNo = securitiesNo;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
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

    public int getSecuritiesType() {
        return securitiesType;
    }

    public void setSecuritiesType(int securitiesType) {
        this.securitiesType = securitiesType;
    }

    public Date getStartStatisticalDate() {
        return startStatisticalDate;
    }

    public void setStartStatisticalDate(Date startStatisticalDate) {
        this.startStatisticalDate = startStatisticalDate;
    }

    public Date getEndStatisticalDate() {
        return endStatisticalDate;
    }

    public void setEndStatisticalDate(Date endStatisticalDate) {
        this.endStatisticalDate = endStatisticalDate;
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
