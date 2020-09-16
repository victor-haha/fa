package com.yidu.stock.domain;


import java.util.Date;

/**
 * 类的描述：股票行情表
 * @author 江北
 * @version 1.0
 * since 2020/09/10
 */
public class stockQuotation {
    /**
     * 股票行情Id
     */
    private String stockQuotationId;
    /**
     * 股票Id
     */
    private String stockId;
    /**
     * 股票名
     */
    private String stockName;
    /**
     * 股票代码|上市代码
     */
    private String stockCode;
    /**
     * 股票分类标记
     */
    private String classCode;
    /**
     * 涨幅
     */
    private double increase;
    /**
     * 涨跌
     */
    private double raisAndFall;
    /**
     * 开盘价
     */
    private double openingPrice;
    /**
     * 收盘价
     */
    private double closingPrice;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 备注
     */
    private String description;

    public String getStockQuotationId() {
        return stockQuotationId;
    }

    public void setStockQuotationId(String stockQuotationId) {
        this.stockQuotationId = stockQuotationId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }

    public double getRaisAndFall() {
        return raisAndFall;
    }

    public void setRaisAndFall(double raisAndFall) {
        this.raisAndFall = raisAndFall;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "stockQuotation{" +
                "stockQuotationId='" + stockQuotationId + '\'' +
                ", stockId='" + stockId + '\'' +
                ", stockName='" + stockName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", classCode='" + classCode + '\'' +
                ", increase=" + increase +
                ", raisAndFall=" + raisAndFall +
                ", openingPrice=" + openingPrice +
                ", closingPrice=" + closingPrice +
                ", tradeDate=" + tradeDate +
                ", description='" + description + '\'' +
                '}';
    }
}
