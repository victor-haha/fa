package com.yidu.fund.paging;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/3 9:06
 */
public class FundPaging {
    private String fundNo;
    private String trusteeBank;
    private String managementFeeMin;
    private String managementFeeMax;
    private String managerId;
    private String usable;
    private Integer page;
    private Integer limit;

    public String getFundNo() {
        return fundNo;
    }

    public void setFundNo(String fundNo) {
        this.fundNo = fundNo;
    }

    public String getTrusteeBank() {
        return trusteeBank;
    }

    public void setTrusteeBank(String trusteeBank) {
        this.trusteeBank = trusteeBank;
    }

    public String getManagementFeeMin() {
        return managementFeeMin;
    }

    public void setManagementFeeMin(String managementFeeMin) {
        this.managementFeeMin = managementFeeMin;
    }

    public String getManagementFeeMax() {
        return managementFeeMax;
    }

    public void setManagementFeeMax(String managementFeeMax) {
        this.managementFeeMax = managementFeeMax;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getUsable() {
        return usable;
    }

    public void setUsable(String usable) {
        this.usable = usable;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
