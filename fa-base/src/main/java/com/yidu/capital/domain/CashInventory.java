package com.yidu.capital.domain;

import com.yidu.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 现金库存表
 * POJO_Description: CashInventory
 * @author wh
 * @since 2020-09-10
 */
public class CashInventory implements Serializable{

	private static final long serialVersionUID =  2722386750596432148L;
	/**
	 * 现金库存Id UUID生成
	 */
	private String cachInventoryId;

	/**
	 * 现金库存编号 现金库存编号：XJKC-yyyy-MM-dd-hhmmss
	 */
	private String cachInventoryNo;

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
	 * 现金余额 当前基金账户表的余额
	 */
	private double cashBalance;

	/**
	 * 统计日期，交易结算后进行统计的日期
	 */
	private Date statisticalDate;
	/**
	 * 统计日期字符串格式
	 */
	private String statisticalDateStr;

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

	public CashInventory() {

	}


	public String getCachInventoryId() {
		return cachInventoryId;
	}

	public void setCachInventoryId(String cachInventoryId) {
		this.cachInventoryId = cachInventoryId;
	}

	public String getCachInventoryNo() {
		return cachInventoryNo;
	}

	public void setCachInventoryNo(String cachInventoryNo) {
		this.cachInventoryNo = cachInventoryNo;
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

	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public Date getStatisticalDate() {
		return statisticalDate;
	}

	public void setStatisticalDate(Date statisticalDate) {
		this.statisticalDate = statisticalDate;
		if(null != statisticalDate){
			this.statisticalDateStr = DateUtils.dataToString(statisticalDate,"yyyy-MM-dd HH:mm:ss");
		}
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

	public String getStatisticalDateStr() {
		return statisticalDateStr;
	}

	@Override
	public String toString() {
		return "CashInventory{" +
				"cachInventoryId='" + cachInventoryId + '\'' +
				", cachInventoryNo='" + cachInventoryNo + '\'' +
				", fundId='" + fundId + '\'' +
				", fundNo='" + fundNo + '\'' +
				", fundName='" + fundName + '\'' +
				", accountId='" + accountId + '\'' +
				", accountNo='" + accountNo + '\'' +
				", accountName='" + accountName + '\'' +
				", cashBalance=" + cashBalance +
				", statisticalDate=" + statisticalDate +
				", statisticalDateStr='" + statisticalDateStr + '\'' +
				", description='" + description + '\'' +
				", remark1='" + remark1 + '\'' +
				", remark2='" + remark2 + '\'' +
				", remark3='" + remark3 + '\'' +
				'}';
	}
}
