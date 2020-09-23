package com.yidu.index.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

/**
 * 证劵应收应付库存表
 * POJO_Description: FundarapInventory
 * @author wh
 * @since 2020-09-16
 */
public class FundarapInventory implements Serializable{

	private static final long serialVersionUID =  8905609655057538453L;
	/**
	 * 现金库存Id
	 */
	private String fundArapInventoryId;

	/**
	 * 现金库存编号
	 */
	private String fundArapInventoryNo;

	/**
	 * 证券Id
	 */
	private String securitiesId;

	/**
	 * 证券编号
	 */
	private String securitiesNo;

	/**
	 * 证券名
	 */
	private String securitiesName;

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
	private BigInteger businessType;

	/**
	 * 应收应付标识
	 */
	private int flag;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 备用字段
	 */
	private String remark1;

	/**
	 * 备用字段
	 */
	private String remark2;

	/**
	 * 备用字段
	 */
	private String remark3;

	public FundarapInventory() {

	}


	public String getFundArapInventoryId() {
		return fundArapInventoryId;
	}

	public void setFundArapInventoryId(String fundArapInventoryId) {
		this.fundArapInventoryId = fundArapInventoryId;
	}

	public String getFundArapInventoryNo() {
		return fundArapInventoryNo;
	}

	public void setFundArapInventoryNo(String fundArapInventoryNo) {
		this.fundArapInventoryNo = fundArapInventoryNo;
	}

	public String getSecuritiesId() {
		return securitiesId;
	}

	public void setSecuritiesId(String securitiesId) {
		this.securitiesId = securitiesId;
	}

	public String getSecuritiesNo() {
		return securitiesNo;
	}

	public void setSecuritiesNo(String securitiesNo) {
		this.securitiesNo = securitiesNo;
	}

	public String getSecuritiesName() {
		return securitiesName;
	}

	public void setSecuritiesName(String securitiesName) {
		this.securitiesName = securitiesName;
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

	public BigInteger getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BigInteger businessType) {
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

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}


	@Override
	public String toString() {
		return " FundarapInventory{" +
				"fundArapInventoryId='" + fundArapInventoryId + '\'' +
				"fundArapInventoryNo='" + fundArapInventoryNo + '\'' +
				"securitiesId='" + securitiesId + '\'' +
				"securitiesNo='" + securitiesNo + '\'' +
				"securitiesName='" + securitiesName + '\'' +
				"fundId='" + fundId + '\'' +
				"fundNo='" + fundNo + '\'' +
				"fundName='" + fundName + '\'' +
				"accountId='" + accountId + '\'' +
				"accountNo='" + accountNo + '\'' +
				"accountName='" + accountName + '\'' +
				"balance='" + balance + '\'' +
				"businessDate='" + businessDate + '\'' +
				"businessType='" + businessType + '\'' +
				"flag='" + flag + '\'' +
				"description='" + description + '\'' +
				"remark1='" + remark1 + '\'' +
				"remark2='" + remark2 + '\'' +
				"remark3='" + remark3 + '\'' +
				'}';
	}

}
