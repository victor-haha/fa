package com.yidu.deposit.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 类的描述:现金库存POJO
 * @author 李昊林
 * @date 2020-09-11
 */
public class CashInventory implements Serializable{

	private static final long serialVersionUID =  1996055074908653612L;
	/**
	 * 现金库存Id
	 */
	private String cashInventoryId;

	/**
	 * 现金库存编号
	 */
	private String cashInventoryNo;

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
	 * 账户Id
	 */
	private String accountId;

	/**
	 * 账号
	 */
	private String accountNo;

	/**
	 * 账户名
	 */
	private String accountName;

	/**
	 * 现金余额
	 */
	private BigDecimal cashBalance;

	/**
	 * 统计日期
	 */
	private Date statisticalDate;

	/**
	 * 描述
	 */
	private String description;


	public CashInventory() {

	}


	public String getCashInventoryId() {
		return cashInventoryId;
	}

	public void setCashInventoryId(String cashInventoryId) {
		this.cashInventoryId = cashInventoryId;
	}

	public String getCashInventoryNo() {
		return cashInventoryNo;
	}

	public void setCashInventoryNo(String cashInventoryNo) {
		this.cashInventoryNo = cashInventoryNo;
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(BigDecimal cashBalance) {
		this.cashBalance = cashBalance;
	}

	public Date getStatisticalDate() {
		return statisticalDate;
	}

	public void setStatisticalDate(Date statisticalDate) {
		this.statisticalDate = statisticalDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "CashInventory{" +
				"cashInventoryId='" + cashInventoryId + '\'' +
				",cashInventoryNo='" + cashInventoryNo + '\'' +
				",fundId='" + fundId + '\'' +
				",fundNo='" + fundNo + '\'' +
				",fundName='" + fundName + '\'' +
				",accountId='" + accountId + '\'' +
				",accountNo='" + accountNo + '\'' +
				",accountName='" + accountName + '\'' +
				",cashBalance='" + cashBalance + '\'' +
				",statisticalDate='" + statisticalDate + '\'' +
				",description='" + description + '\'' +
				'}';
	}

}
