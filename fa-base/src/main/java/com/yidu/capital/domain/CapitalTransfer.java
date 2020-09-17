package com.yidu.capital.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 资金调拨表
 * POJO_Description: CapitalTransfer
 * @author wh
 * @since 2020-09-08
 */
public class CapitalTransfer implements Serializable{

	private static final long serialVersionUID =  9170496776657570411L;
	/**
	 * 资金调拨
	 */
	private String capitalTransferId;

	/**
	 * 资金调拨编号 ZJDB-yyyy-MM-dd-xxxxx 
	 */
	private String capitalTransferNo;

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
	 * 调拨金额
	 */
	private BigDecimal transferAmount;

	/**
	 * 调拨日期
	 */
	private Date transferDate;

	/**
	 * 调拨标识,1.流入 2.流出
	 */
	private BigInteger transferFlag;

	/**
	 * 调拨类型,1申购调拨、2认购调拨、3赎回调拨、4清算调拨、5权益调拨
	 */
	private String transferType;


	public CapitalTransfer() {

	}


	public String getCapitalTransferId() {
		return capitalTransferId;
	}

	public void setCapitalTransferId(String capitalTransferId) {
		this.capitalTransferId = capitalTransferId;
	}

	public String getCapitalTransferNo() {
		return capitalTransferNo;
	}

	public void setCapitalTransferNo(String capitalTransferNo) {
		this.capitalTransferNo = capitalTransferNo;
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

	public BigDecimal getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public BigInteger getTransferFlag() {
		return transferFlag;
	}

	public void setTransferFlag(BigInteger transferFlag) {
		this.transferFlag = transferFlag;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}



	@Override
	public String toString() {
		return " CapitalTransfer{" +
				"capitalTransferId='" + capitalTransferId + '\'' +
				"capitalTransferNo='" + capitalTransferNo + '\'' +
				"fundId='" + fundId + '\'' +
				"fundNo='" + fundNo + '\'' +
				"fundName='" + fundName + '\'' +
				"accountId='" + accountId + '\'' +
				"accountNo='" + accountNo + '\'' +
				"accountName='" + accountName + '\'' +
				"transferAmount='" + transferAmount + '\'' +
				"transferDate='" + transferDate + '\'' +
				"transferFlag='" + transferFlag + '\'' +
				"transferType='" + transferType + '\'' +
				'}';
	}

}
