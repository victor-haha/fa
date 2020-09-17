package com.yidu.bond.paging;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

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
    private BigDecimal startActualIssuance;
    /**
     * 发行量上限值（亿元）
     */
    private BigDecimal endActualIssuance;
    /**
     * 期限上限值(年)
     */
    private BigDecimal startTerm;
    /**
     * 期限下限值（年）
     */
    private BigDecimal endTerm;
    /**
     * 票面利率下限值
     */
    private BigDecimal startCouponRate;
    /**
     * 票面利率上限值
     */
    private BigDecimal endCouponRate;
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

    public BigDecimal getStartActualIssuance() {
        return startActualIssuance;
    }

    public void setStartActualIssuance(BigDecimal startActualIssuance) {
        this.startActualIssuance = startActualIssuance;
    }

    public BigDecimal getEndActualIssuance() {
        return endActualIssuance;
    }

    public void setEndActualIssuance(BigDecimal endActualIssuance) {
        this.endActualIssuance = endActualIssuance;
    }

    public BigDecimal getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(BigDecimal startTerm) {
        this.startTerm = startTerm;
    }

    public BigDecimal getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(BigDecimal endTerm) {
        this.endTerm = endTerm;
    }

    public BigDecimal getStartCouponRate() {
        return startCouponRate;
    }

    public void setStartCouponRate(BigDecimal startCouponRate) {
        this.startCouponRate = startCouponRate;
    }

    public BigDecimal getEndCouponRate() {
        return endCouponRate;
    }

    public void setEndCouponRate(BigDecimal endCouponRate) {
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
