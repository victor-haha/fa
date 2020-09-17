package com.yidu.fund.domain;

import com.yidu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 类的描述:基金交易POJO
 * @author 李昊林
 * @date 2020-09-08
 */
public class FundTrade implements Serializable{

	private static final long serialVersionUID =  1690506313736029235L;
	/**
	 * 基金交易Id
	 */
	private String fundTradeId;

	/**
	 * 基金交易编号,交易编号：ZJJY-yyyy-MM-dd-xxxxx 
	 */
	private String fundTradeNo;

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
	 * 基金交易类型,1：申购、2：认购、3：赎回
	 */
	private String fundTradeType;

	/**
	 * 账户ID,参考账户表，基金对应的托管账户
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
	 * 交易标识,1.流入（申购、认购资金流入到基金账户）、2.流出（赎回时资金从基金账户流出）
	 */
	private BigInteger tradeFlag;

	/**
	 * 交易价格
	 */
	private BigDecimal tradePrice;

	/**
	 * 交易时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tradeDate;
	/**
	 * 格式化交易时间
	 */
	private String tradeDateStr;

	/**
	 * 交易数量,单位:份
	 */
	private BigInteger share;

	/**
	 * 交易额(总),交易额 = 交易价*交易数量（份额）
	 */
	private BigDecimal turnover;

	/**
	 * 费用,申购|赎回|认购费
	 */
	private BigDecimal fee;

	/**
	 * 总金额,交易额+各种税费
	 */
	private BigDecimal total;

	/**
	 * 交易状态,交易状态 已结算1  未结算 0
	 */
	private BigInteger tradeStatus;


	public FundTrade() {

	}


	public String getFundTradeId() {
		return fundTradeId;
	}

	public void setFundTradeId(String fundTradeId) {
		this.fundTradeId = fundTradeId;
	}

	public String getFundTradeNo() {
		return fundTradeNo;
	}

	public void setFundTradeNo(String fundTradeNo) {
		this.fundTradeNo = fundTradeNo;
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

	public String getFundTradeType() {
		return fundTradeType;
	}

	public void setFundTradeType(String fundTradeType) {
		this.fundTradeType = fundTradeType;
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

	public BigInteger getTradeFlag() {
		return tradeFlag;
	}

	public void setTradeFlag(BigInteger tradeFlag) {
		this.tradeFlag = tradeFlag;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
		this.tradeDateStr = DateUtils.dataToString(tradeDate,"yyyy-MM-dd");
	}

	public BigInteger getShare() {
		return share;
	}

	public void setShare(BigInteger share) {
		this.share = share;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigInteger getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(BigInteger tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

    public String getTradeDateStr() {
        return tradeDateStr;
    }

    @Override
	public String toString() {
		return "FundTrade{" +
				"fundTradeId='" + fundTradeId + '\'' +
				",fundTradeNo='" + fundTradeNo + '\'' +
				",fundId='" + fundId + '\'' +
				",fundNo='" + fundNo + '\'' +
				",fundName='" + fundName + '\'' +
				",fundTradeType='" + fundTradeType + '\'' +
				",accountId='" + accountId + '\'' +
				",accountNo='" + accountNo + '\'' +
				",accountName='" + accountName + '\'' +
				",tradeFlag='" + tradeFlag + '\'' +
				",tradePrice='" + tradePrice + '\'' +
				",tradeDate='" + tradeDate + '\'' +
				",share='" + share + '\'' +
				",turnover='" + turnover + '\'' +
				",fee='" + fee + '\'' +
				",total='" + total + '\'' +
				",tradeStatus='" + tradeStatus + '\'' +
				'}';
	}

}
