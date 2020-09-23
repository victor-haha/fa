package com.yidu.account.domain;

import com.yidu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金账户表
 * POJO_Description: Account
 * @author wh
 * @since 2020-09-03
 */
public class Account implements Serializable{

	private static final long serialVersionUID =  5272645263686060606L;
	/**
	 * 账户Id
	 */
	private String accountId;

	/**
	 * 账号
	 */
	private String accountNo;

	/**
	 * 账户名,比如：Xxx基金托管账户、Xxx基金存款账户
	 */
	private String accountName;

	/**
	 * 存款类型,托管账户的存款类型是活期
	 */
	private String depositType;

	/**
	 * 存款利率,年利率
	 */
	private BigDecimal depositRate;

	/**
	 * 开户银行
	 */
	private String bankName;

	/**
	 * 开户时间
	 */
	@DateTimeFormat( pattern ="yyyy-MM-dd HH:mm:ss")
	private Date openDate;
	/**
	 * 开户时间字符串格式
	 */
	private String openDateStr;

	/**
	 * 所属基金Id,参考基金表主键
	 */
	private String fundId;

	/**
	 * 所属基金名
	 */
	private String fundName;

	/**
	 * 备注
	 */
	private String description;

	/**
	 * 状态，用于逻辑删除
	 */
	private String usable;

	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}


	public Account() {

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

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public BigDecimal getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(BigDecimal depositRate) {
		this.depositRate = depositRate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
		this.openDateStr = DateUtils.dataToString(openDate,"yyyy-MM-dd HH:mm:ss");
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpenDateStr() {
		return openDateStr;
	}

	@Override
	public String toString() {
		return "Account{" +
				"accountId=" + accountId +
				", accountNo='" + accountNo + '\'' +
				", accountName='" + accountName + '\'' +
				", depositType='" + depositType + '\'' +
				", depositRate=" + depositRate +
				", bankName='" + bankName + '\'' +
				", openDate=" + openDate +
				", openDateStr='" + openDateStr + '\'' +
				", fundId='" + fundId + '\'' +
				", fundName='" + fundName + '\'' +
				", description='" + description + '\'' +
				", usable='" + usable + '\'' +
				'}';
	}

}
