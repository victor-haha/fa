package com.yidu.bond.paging;

import org.springframework.stereotype.Component;

/**
 * 类的描述: 债券搜索分页字段
 *
 * @author wh
 * @since 2020/9/3 17:44
 */
@Component
public class BondPaging {
    /**
     * 债券代码
     */
    private String bondCode;
    /**
     * 债券名称
     */
    private String bondFullName;
    /**
     * 发行量下限值（亿元）
     */
    private Double startActualIssuance;
    /**
     * 发行量上限值（亿元）
     */
    private Double endActualIssuance;
    /**
     * 期限上限值(年)
     */
    private Double startTerm;
    /**
     * 期限下限值（年）
     */
    private Double endTerm;
    /**
     * 票面利率下限值
     */
    private Double startCouponRate;
    /**
     * 票面利率上限值
     */
    private Double endCouponRate;
    /**
     * 付息方式：付息方式可分为：按半年付息、按年付息、到期一次返本付息
     */
    private String paymentFrequency;
    /**
     * 第几页
     */
    private int page;
    /**
     * 每页条数
     */
    private int limit;

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public String getBondFullName() {
        return bondFullName;
    }

    public void setBondFullName(String bondFullName) {
        this.bondFullName = bondFullName;
    }

    public Double getStartActualIssuance() {
        return startActualIssuance;
    }

    public void setStartActualIssuance(Double startActualIssuance) {
        this.startActualIssuance = startActualIssuance;
    }

    public Double getEndActualIssuance() {
        return endActualIssuance;
    }

    public void setEndActualIssuance(Double endActualIssuance) {
        this.endActualIssuance = endActualIssuance;
    }

    public Double getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(Double startTerm) {
        this.startTerm = startTerm;
    }

    public Double getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(Double endTerm) {
        this.endTerm = endTerm;
    }

    public Double getStartCouponRate() {
        return startCouponRate;
    }

    public void setStartCouponRate(Double startCouponRate) {
        this.startCouponRate = startCouponRate;
    }

    public Double getEndCouponRate() {
        return endCouponRate;
    }

    public void setEndCouponRate(Double endCouponRate) {
        this.endCouponRate = endCouponRate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
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
