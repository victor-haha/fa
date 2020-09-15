package com.yidu.stock.domain;

import com.yidu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 类的描述：股票信息表
 * @author 江北
 * @version 1.0
 * since 2020/09/09
 */
public class Stock {
    /**
     * 股票Id
     */
    private String stockId;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票简称
     */
    private String stockShortName;
    /**
     * 上市日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;
    /**
     * 发行人
     */
    private String issuer;
    /**
     * 所属板块
     */
    private String plate;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 交易所
     */
    private String exchange;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间str格式
     */
//    private String issueDateStr;

    private String usable;

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

    public String getStockShortName() {
        return stockShortName;
    }

    public void setStockShortName(String stockShortName) {
        this.stockShortName = stockShortName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        /*if(null != this.issueDate){
            this.issueDateStr = DateUtils.dataToString(issueDate,"yyyy-MM-dd");
        }*/
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUsable() {
        return usable;
    }

    public void setUsable(String usable) {
        this.usable = usable;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId='" + stockId + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockShortName='" + stockShortName + '\'' +
                ", issueDate=" + issueDate +
                ", issuer='" + issuer + '\'' +
                ", plate='" + plate + '\'' +
                ", industry='" + industry + '\'' +
                ", exchange='" + exchange + '\'' +
                ", description='" + description + '\'' +
                ", usable='" + usable + '\'' +
                '}';
    }
}
