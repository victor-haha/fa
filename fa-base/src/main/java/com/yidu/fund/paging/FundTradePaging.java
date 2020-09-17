package com.yidu.fund.paging;

/**
 * 类的描述：基金交易数据查询条件类
 *
 * @Author 李昊林
 * @Date 2020/9/7 15:07
 */
public class FundTradePaging {
    private String fundTradeId;
    private String fundTradeNo;
    private String fundNo;
    private String fundName;
    private String fundTradeType;
    private Integer tradeFlag;
    private Integer totalMin;
    private Integer totalMax;
    private String tradeDateStart;
    private String tradeDateEnd;
    private String tradeStatus;
    private Integer page;
    private Integer limit;
    private String b;



    public String getFundTradeId() {
        return fundTradeId;
    }

    public void setFundTradeId(String fundTradeId) {
        this.fundTradeId = fundTradeId;
    }

    public String getFundTradeNo() {
        return fundTradeNo;
    }

    public void setFundTradeNo(String fundTradeNo) {
        this.fundTradeNo = fundTradeNo;
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

    public String getFundTradeType() {
        return fundTradeType;
    }

    public void setFundTradeType(String fundTradeType) {
        this.fundTradeType = fundTradeType;
    }

    public Integer getTradeFlag() {
        return tradeFlag;
    }

    public void setTradeFlag(Integer tradeFlag) {
        this.tradeFlag = tradeFlag;
    }

    public Integer getTotalMin() {
        return totalMin;
    }

    public void setTotalMin(Integer totalMin) {
        this.totalMin = totalMin;
    }

    public Integer getTotalMax() {
        return totalMax;
    }

    public void setTotalMax(Integer totalMax) {
        this.totalMax = totalMax;
    }

    public String getTradeDateStart() {
        return tradeDateStart;
    }

    public void setTradeDateStart(String tradeDateStart) {
        this.tradeDateStart = tradeDateStart;
    }

    public String getTradeDateEnd() {
        return tradeDateEnd;
    }

    public void setTradeDateEnd(String tradeDateEnd) {
        this.tradeDateEnd = tradeDateEnd;
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

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
        if (b != null&& !b.equals("")) {
            this.tradeStatus = b;
        }
    }
}
