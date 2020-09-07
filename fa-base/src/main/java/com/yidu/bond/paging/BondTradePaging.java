package com.yidu.bond.paging;

/**
 * 类的描述:债券交易数据搜索分页字段
 *
 * @author wh
 * @since 2020/9/6 13:02
 */
public class BondTradePaging {
    /**
     * 债券交易编号
     */
    private String bondTradeNo;
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 债券名称
     */
    private String bondName;
    /**
     * 基金经理
     */
    private String managerName;
    /**
     * 券商名
     */
    private String brokerName;
    /**
     * 交易状态 已结算1  未结算 2
     */
    private String tradeStatus;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页条数
     */
    private Integer limit;

    public String getBondTradeNo() {
        return bondTradeNo;
    }

    public void setBondTradeNo(String bondTradeNo) {
        this.bondTradeNo = bondTradeNo;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
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
