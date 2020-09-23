package com.yidu.deposit.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 类的描述:现金应收应付表POJO
 * @author 李昊林
 * @date 2020-09-18
 */
public class CashArap implements Serializable{

	private static final long serialVersionUID =  5386675746983339640L;
	/**
	 * 现金应收应付Id
	 */
	private String cashArapId;

	/**
	 * 现金应收应付编号
	 */
	private String cashArapNo;

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
	 * 应收应付金额
	 */
	private BigDecimal arapAmount;

	/**
	 * 应收应付日期
	 */
	private Date arapDate;

	/**
	 * 应收应付标识
	 */
	private int arapFlag;

	/**
	 * 应收应付类型
	 */
	private int arapType;


	public CashArap() {

	}


	public String getCashArapId() {
		return cashArapId;
	}

	public void setCashArapId(String cashArapId) {
		this.cashArapId = cashArapId;
	}

	public String getCashArapNo() {
		return cashArapNo;
	}

	public void setCashArapNo(String cashArapNo) {
		this.cashArapNo = cashArapNo;
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

	public BigDecimal getArapAmount() {
		return arapAmount;
	}

	public void setArapAmount(BigDecimal arapAmount) {
		this.arapAmount = arapAmount;
	}

	public Date getArapDate() {
		return arapDate;
	}

	public void setArapDate(Date arapDate) {
		this.arapDate = arapDate;
	}

	public int getArapFlag() {
		return arapFlag;
	}

	public void setArapFlag(int arapFlag) {
		this.arapFlag = arapFlag;
	}

	public int getArapType() {
		return arapType;
	}

	public void setArapType(int arapType) {
		this.arapType = arapType;
	}



	@Override
	public String toString() {
		return "CashArap{" +
				"cashArapId='" + cashArapId + '\'' +
				",cashArapNo='" + cashArapNo + '\'' +
				",fundId='" + fundId + '\'' +
				",fundNo='" + fundNo + '\'' +
				",fundName='" + fundName + '\'' +
				",accountId='" + accountId + '\'' +
				",accountNo='" + accountNo + '\'' +
				",accountName='" + accountName + '\'' +
				",arapAmount='" + arapAmount + '\'' +
				",arapDate='" + arapDate + '\'' +
				",arapFlag='" + arapFlag + '\'' +
				",arapType='" + arapType + '\'' +
				'}';
	}

}
