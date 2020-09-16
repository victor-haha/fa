package com.yidu.stock.domain;

import java.util.Date;

/**
 * 类的描述：股票交易数据表
 * @author 江北
 * @version 1.0
 * since 2020/09/09
 */
public class StockTrade {
    /**
     * 股票交易Id
     */
    private String stockTradeId;
    /**
     * 股票交易编号
     */
    private String stockTradeNo;
    /**
     * 基金Id
     */
    private String fundId;
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 基金名
     */
    private String fundName;
    /**
     * 股票Id
     */
    private String stockId;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票名
     */
    private String stockName;
    /**
     * 基金经理Id
     */
    private String managerId;
    /**
     * 基金经理
     */
    private String managerName;
    /**
     * 券商Id
     */
    private String brokerId;
    /**
     * 券商名
     */
    private String brokerName;
    /**
     * 交易方式
     */
    private String tradeType;
    /**
     * 交易标识
     */
    private String tradeFlag;
    /**
     * 交易价格（单价）
     */
    private double tradePrice;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 交易数量(份额)
     */
    private int share;
    /**
     * 交易额(总)
     */
    private double turnover;
    /**
     * 印花税（国家）
     */
    private double stampTax;
    /**
     * 征管费（国家）
     */
    private double managementFees;
    /**
     * 过户费（交易所）
     */
    private double transferFee;
    /**
     * 佣金费用（券商）
     */
    private double commission;
    /**
     * 经手费（交易所）
     */
    private double brokerage;
    /**
     * 总金额
     */
    private double total;
    /**
     * 交易状态
     */
    private String tradeStatus;
    /**
     * 备注
     */
    private String description;

    public String getStockTradeId() {
        return stockTradeId;
    }

    public void setStockTradeId(String stockTradeId) {
        this.stockTradeId = stockTradeId;
    }

    public String getStockTradeNo() {
        return stockTradeNo;
    }

    public void setStockTradeNo(String stockTradeNo) {
        this.stockTradeNo = stockTradeNo;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeFlag() {
        return tradeFlag;
    }

    public void setTradeFlag(String tradeFlag) {
        this.tradeFlag = tradeFlag;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getStampTax() {
        return stampTax;
    }

    public void setStampTax(double stampTax) {
        this.stampTax = stampTax;
    }

    public double getManagementFees() {
        return managementFees;
    }

    public void setManagementFees(double managementFees) {
        this.managementFees = managementFees;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(double transferFee) {
        this.transferFee = transferFee;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(double brokerage) {
        this.brokerage = brokerage;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StockTrade{" +
                "stockTradeId='" + stockTradeId + '\'' +
                ", stockTradeNo='" + stockTradeNo + '\'' +
                ", fundId='" + fundId + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", fundName='" + fundName + '\'' +
                ", stockId='" + stockId + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", managerId='" + managerId + '\'' +
                ", managerName='" + managerName + '\'' +
                ", brokerId='" + brokerId + '\'' +
                ", brokerName='" + brokerName + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", tradeFlag='" + tradeFlag + '\'' +
                ", tradePrice=" + tradePrice +
                ", tradeDate=" + tradeDate +
                ", share=" + share +
                ", turnover=" + turnover +
                ", stampTax=" + stampTax +
                ", managementFees=" + managementFees +
                ", transferFee=" + transferFee +
                ", commission=" + commission +
                ", brokerage=" + brokerage +
                ", total=" + total +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
