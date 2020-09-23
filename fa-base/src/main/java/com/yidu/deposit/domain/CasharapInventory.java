package com.yidu.deposit.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 类的描述:现金应收应付库存POJO
 * @author 李昊林
 * @date 2020-09-18
 */
public class CasharapInventory implements Serializable{

	private static final long serialVersionUID =  8583412743049350036L;
	/**
	 * 现金应收应付库存Id
	 */
	private String cachArapInventoryId;

	/**
	 * 现金应收应付库存编号
	 */
	private String cachArapInventoryNo;

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
	 * 余额
	 */
	private BigDecimal balance;

	/**
	 * 业务日期
	 */
	private Date businessDate;

	/**
	 * 业务类型
	 */
	private int businessType;

	/**
	 * 应收应付标识
	 */
	private int flag;

	/**
	 * 描述
	 */
	private String description;


	public CasharapInventory() {

	}


	public String getCachArapInventoryId() {
		return cachArapInventoryId;
	}

	public void setCachArapInventoryId(String cachArapInventoryId) {
		this.cachArapInventoryId = cachArapInventoryId;
	}

	public String getCachArapInventoryNo() {
		return cachArapInventoryNo;
	}

	public void setCachArapInventoryNo(String cachArapInventoryNo) {
		this.cachArapInventoryNo = cachArapInventoryNo;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "CasharapInventory{" +
				"cachArapInventoryId='" + cachArapInventoryId + '\'' +
				",cachArapInventoryNo='" + cachArapInventoryNo + '\'' +
				",fundId='" + fundId + '\'' +
				",fundNo='" + fundNo + '\'' +
				",fundName='" + fundName + '\'' +
				",accountId='" + accountId + '\'' +
				",accountNo='" + accountNo + '\'' +
				",accountName='" + accountName + '\'' +
				",balance='" + balance + '\'' +
				",businessDate='" + businessDate + '\'' +
				",businessType='" + businessType + '\'' +
				",flag='" + flag + '\'' +
				",description='" + description + '\'' +
				'}';
	}

}
