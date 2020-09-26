package com.yidu.deposit.domain;

import com.yidu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行存款交易数据表
 * POJO_Description: DepositTrade
 * @author wh
 * @since 2020-09-10
 */
public class DepositTrade implements Serializable{

	private static final long serialVersionUID =  4115413120046558465L;
	/**
	 * 存款Id
	 */
	private String depositId;

	/**
	 * 存款流水号,用工具类生成交易编号，交易编号：DQCK-yyyy-MM-dd-xxxxx
	 */
	private String depositNo;

	/**
	 * 基金Id,参考基金表
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
	 * 流出账户Id(托管账户Id)
	 */
	private String outAccountId;

	/**
	 * 流出账户账号
	 */
	private String outAccountNo;

	/**
	 * 流出账户名
	 */
	private String outAccountName;

	/**
	 * 流入账户Id,流入现金账户
	 */
	private String inAccountId;

	/**
	 * 流入账户账号
	 */
	private String inAccountNo;

	/**
	 * 流入账户名
	 */
	private String inAccountName;

	/**
	 * 交易方式,1:定存、2定存收回
	 */
	private Integer tradeType;

	/**
	 * 交易标识,1.流入、2.流出  存定期存款，资金调拨流出；定期存款到期收回，资金调拨流入
	 */
	private Integer tradeFlag;

	/**
	 * 存款时间,业务时间	--开始存款业务的时间
	 */
	@DateTimeFormat( pattern ="yyyy-MM-dd")
	private Date businessDate;
	private String businessDateStr;
	/**
	 * 存款类型,业务类型		 --1定期三天，2定期7天、3定期1月等
	 */
	private Integer businessType;

	/**
	 * 存款金额,存款金额	default 0	,--存款的金额数目
	 */
	private BigDecimal money;

	/**
	 * 存款利率,定存利率
	 */
	private BigDecimal interestRate;

	/**
	 * 到期时间,存款业务到期时间
	 */
	@DateTimeFormat( pattern ="yyyy-MM-dd")
	private Date endDate;
	private String endDateStr;
	/**
	 * 存款状态,交易状态 已结算1  未结算 0
	 */
	private Integer tradeStatus;

	/**
	 * 备注
	 */
	private String description;


	public DepositTrade() {

	}


	public String getDepositId() {
		return depositId;
	}

	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}

	public String getDepositNo() {
		return depositNo;
	}

	public void setDepositNo(String depositNo) {
		this.depositNo = depositNo;
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

	public String getOutAccountId() {
		return outAccountId;
	}

	public void setOutAccountId(String outAccountId) {
		this.outAccountId = outAccountId;
	}

	public String getOutAccountNo() {
		return outAccountNo;
	}

	public void setOutAccountNo(String outAccountNo) {
		this.outAccountNo = outAccountNo;
	}

	public String getOutAccountName() {
		return outAccountName;
	}

	public void setOutAccountName(String outAccountName) {
		this.outAccountName = outAccountName;
	}

	public String getInAccountId() {
		return inAccountId;
	}

	public void setInAccountId(String inAccountId) {
		this.inAccountId = inAccountId;
	}

	public String getInAccountNo() {
		return inAccountNo;
	}

	public void setInAccountNo(String inAccountNo) {
		this.inAccountNo = inAccountNo;
	}

	public String getInAccountName() {
		return inAccountName;
	}

	public void setInAccountName(String inAccountName) {
		this.inAccountName = inAccountName;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getTradeFlag() {
		return tradeFlag;
	}

	public void setTradeFlag(Integer tradeFlag) {
		this.tradeFlag = tradeFlag;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
		this.businessDateStr = DateUtils.dataToString(businessDate,"yyyy-MM-dd");
	}

	public String getBusinessDateStr() {
		return businessDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		this.endDateStr = DateUtils.dataToString(endDate,"yyyy-MM-dd");
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return " DepositTrade{" +
				"depositId='" + depositId + '\'' +
				"depositNo='" + depositNo + '\'' +
				"fundId='" + fundId + '\'' +
				"fundCode='" + fundNo + '\'' +
				"fundName='" + fundName + '\'' +
				"outAccountId='" + outAccountId + '\'' +
				"outAccountNo='" + outAccountNo + '\'' +
				"outAccountName='" + outAccountName + '\'' +
				"inAccountId='" + inAccountId + '\'' +
				"inAccountNo='" + inAccountNo + '\'' +
				"inAccountName='" + inAccountName + '\'' +
				"tradeType='" + tradeType + '\'' +
				"tradeFlag='" + tradeFlag + '\'' +
				"businessDate='" + businessDate + '\'' +
				"businessType='" + businessType + '\'' +
				"money='" + money + '\'' +
				"interestRate='" + interestRate + '\'' +
				"endDate='" + endDate + '\'' +
				"tradeStatus='" + tradeStatus + '\'' +
				"description='" + description + '\'' +
				'}';
	}

}
