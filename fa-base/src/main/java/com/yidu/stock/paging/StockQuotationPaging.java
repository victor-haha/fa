package com.yidu.stock.paging;

/**
 * 类的描述:股票行情条件查询分页字段
 * @author 江北
 * @since 2020/9/22
 */
public class StockQuotationPaging {
    /**
     * 股票交易编号
     */
    private String stockTradeNo;
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 股票名称
     */
    private String stockName;
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

    public String getStockTradeNo() {
        return stockTradeNo;
    }

    public void setStockTradeNo(String stockTradeNo) {
        this.stockTradeNo = stockTradeNo;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
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
