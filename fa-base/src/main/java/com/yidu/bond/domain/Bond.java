package com.yidu.bond.domain;

import com.yidu.utils.DateUtils;

import java.io.Serializable;
import java.sql.Date;

/**
 * 证券-债券信息表
 * POJO_Description: Bond
 * @author wh
 * @since 2020-09-02
 */
public class Bond implements Serializable{

	private static final long serialVersionUID =  4320779201133770623L;
	/**
	 * 债券Id
	 */
	private int bondId;

	/**
	 * 债券代码
	 */
	private String bondCode;

	/**
	 * 债券简称
	 */
	private String bondShortName;

	/**
	 * 实际发行量(亿元)
	 */
	private double actualIssuance;

	/**
	 * 发行价格,发行价格高于票面价值是溢价发行，等于是平价发行、低于叫折价发行
	 */
	private double issuePrice;

	/**
	 * 票面价值,票面价值一般是100.00
	 */
	private double par;

	/**
	 * 期限(年),债券返本期限
	 */
	private int term;

	/**
	 * 付息方式,付息方式可分为：按半年付息、按年付息、到期一次返本付息
	 */
	private String paymentFrequency;

	/**
	 * 起息日期,计息起始日期
	 */
	private Date valueDate;
	/**
	 * 起息日期,计息起始日期（字符串表示）
	 */
	private String valueDateStr;

	/**
	 * 到期日期,计息结束日期
	 */
	private Date expireDate;
	/**
	 * 到期日期,计息结束日期（字符串表示）
	 */
	private String expireDateStr;

	/**
	 * 票面利率,债券利息与债券面值的比率，是发行人承诺以后一定时期支付给债券持有人报酬的计算标准
	 */
	private double couponRate;

	/**
	 * 债券全称,
	 */
	private String bondFullName;

	/**
	 * 备注
	 */
	private String description;



	public Bond() {

	}


	public int getBondId() {
		return bondId;
	}

	public void setBondId(int bondId) {
		this.bondId = bondId;
	}

	public String getBondCode() {
		return bondCode;
	}

	public void setBondCode(String bondCode) {
		this.bondCode = bondCode;
	}

	public String getBondShortName() {
		return bondShortName;
	}

	public void setBondShortName(String bondShortName) {
		this.bondShortName = bondShortName;
	}

	public double getActualIssuance() {
		return actualIssuance;
	}

	public void setActualIssuance(double actualIssuance) {
		this.actualIssuance = actualIssuance;
	}

	public double getIssuePrice() {
		return issuePrice;
	}

	public void setIssuePrice(double issuePrice) {
		this.issuePrice = issuePrice;
	}

	public double getPar() {
		return par;
	}

	public void setPar(double par) {
		this.par = par;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
		if(null != valueDate){
			this.valueDateStr = DateUtils.dataToString(valueDate,"yyyy-MM-dd");
		}
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
		if(null != expireDate){
			this.expireDateStr = DateUtils.dataToString(expireDate,"yyyy-MM-dd");
		}
	}

	public double getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(double couponRate) {
		this.couponRate = couponRate;
	}

	public String getBondFullName() {
		return bondFullName;
	}

	public void setBondFullName(String bondFullName) {
		this.bondFullName = bondFullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getValueDateStr() {
		return valueDateStr;
	}

	public String getExpireDateStr() {
		return expireDateStr;
	}

	@Override
	public String toString() {
		return "Bond{" +
				"bondId=" + bondId +
				", bondCode='" + bondCode + '\'' +
				", bondShortName='" + bondShortName + '\'' +
				", actualIssuance=" + actualIssuance +
				", issuePrice=" + issuePrice +
				", par=" + par +
				", term=" + term +
				", paymentFrequency='" + paymentFrequency + '\'' +
				", valueDate=" + valueDate +
				", valueDateStr='" + valueDateStr + '\'' +
				", expireDate=" + expireDate +
				", expireDateStr='" + expireDateStr + '\'' +
				", couponRate=" + couponRate +
				", bondFullName='" + bondFullName + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
