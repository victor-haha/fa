package com.yidu.deposit.domain;


import com.yidu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 类的描述:基金库存POJO
 * @author 李昊林
 * @date 2020-09-10
 */
public class FundInventory implements Serializable{

	private static final long serialVersionUID =  6217461549345720449L;
	/**
	 * 基金库存Id
	 */
	private String fundInventoryId;

	/**
	 * 基金库存编号
	 */
	private String fundInventoryNo;

	/**
	 * 基金Id
	 */
	private String fundId;

	/**
	 * 基金代码
	 */
	private String fundNo;

	/**
	 * 基金名
	 */
	private String fundName;

	/**
	 * 份额
	 */
	private BigInteger share;

	/**
	 * 余额
	 */
	private BigDecimal balance;

	/**
	 * 统计日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date statisticalDate;
	/**
	 * 格式化统计日期
	 */
	private String statisticalDateStr;

	/**
	 * 描述
	 */
	private String description;


	public FundInventory() {

	}


	public String getFundInventoryId() {
		return fundInventoryId;
	}

	public void setFundInventoryId(String fundInventoryId) {
		this.fundInventoryId = fundInventoryId;
	}

	public String getFundInventoryNo() {
		return fundInventoryNo;
	}

	public void setFundInventoryNo(String fundInventoryNo) {
		this.fundInventoryNo = fundInventoryNo;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
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
		this.fundName = fundName;
	}

	public BigInteger getShare() {
		return share;
	}

	public void setShare(BigInteger share) {
		this.share = share;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getStatisticalDate() {
		return statisticalDate;
	}

	public void setStatisticalDate(Date statisticalDate) {
		this.statisticalDate = statisticalDate;
		this.statisticalDateStr = DateUtils.dataToString(statisticalDate,"yyyy-MM-dd");

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatisticalDateStr() {
		return statisticalDateStr;
	}

	@Override
	public String toString() {
		return "FundInventory{" +
				"fundInventoryId='" + fundInventoryId + '\'' +
				",fundInventoryNo='" + fundInventoryNo + '\'' +
				",fundId='" + fundId + '\'' +
				",fundNo='" + fundNo + '\'' +
				",fundName='" + fundName + '\'' +
				",share='" + share + '\'' +
				",balance='" + balance + '\'' +
				",statisticalDate='" + statisticalDate + '\'' +
				",description='" + description + '\'' +
				'}';
	}

}
