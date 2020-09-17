package com.yidu.bond.domain;

import com.yidu.utils.DateUtils;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 债券交易数据表
 * POJO_Description: BondTrade
 * @author wh
 * @since 2020-09-06
 */
@Data
public class BondTrade implements Serializable{

	private static final long serialVersionUID =  228366830405396394L;
	/**
	 * 债券交易Id
	 */
	private String bondTradeId;

	/**
	 * 债券交易编号
	 */
	private String bondTradeNo;

	/**
	 * 基金Id
	 */
	private String fundId;

	/**
	 * 基金代码，冗余字段
	 */
	private String fundNo;

	/**
	 * 基金名称，冗余字段
	 */
	private String fundName;

	/**
	 * 债券Id
	 */
	private String bondId;

	/**
	 * 债券代码，冗余字段
	 */
	private String bondCode;

	/**
	 * 债券名称，冗余字段
	 */
	private String bondName;

	/**
	 * 基金经理Id
	 */
	private String managerId;

	/**
	 * 基金经理，冗余字段
	 */
	private String managerName;

	/**
	 * 券商Id
	 */
	private String brokerId;

	/**
	 * 券商名，冗余字段
	 */
	private String brokerName;

	/**
	 * 交易方式，1买入，2卖出
	 */
	private int tradeType;

	/**
	 * 交易标识1.流入、2.流出   买入股票，资金调拨流出；卖出股票，资金调拨流入
	 */
	private int tradeFlag;


	/**
	 * 交易价格(单价),买入或卖出时股票单价
	 */
	private BigDecimal tradePrice;

	/**
	 * 交易日期
	 */
	private Date tradeDate;

	/**
	 * 交易数量(份额)，单位：份
	 */
	private BigInteger share;

	/**
	 * 交易额（总），交易额 = 交易价*交易数量（份额）
	 */
	private BigDecimal turnover;

	/**
	 * 印花费（国家），印花税 = 印花税率*交易额
	 */
	private BigDecimal stampTax;

	/**
	 * 征管费（国家），征管费 = 征管费率*交易额
	 */
	private BigDecimal managementFees;

	/**
	 * 过户费（交易所），过户费 = 过户费率*交易额
	 */
	private BigDecimal transferFee;

	/**
	 * 佣金费用（券商），佣金= 席位费率*交易额
	 */
	private BigDecimal commission;

	/**
	 * 经手费（交易所），经手费=经手费率*交易额
	 */
	private BigDecimal brokerage;

	/**
	 * 总金额，交易额+各种税费
	 */
	private BigDecimal total;

	/**
	 * 债券利息，冗余字段，参考债券表的票面利率couponRate
	 */
	private BigDecimal couponRate;

	/**
	 * 交易状态 已结算1  未结算 0
	 */
	private int tradeStatus;


	/**
	 * 备注
	 */
	private String description;

    /**
     * 交易日期 字符串格式
     */
    private String tradeDateStr;
    /**
     * 交易状态字符串格式 已结算1  未结算 0
     */
    private String tradeStatusStr;
    /**
     * 交易标识字符串格式  1.流入、2.流出   买入股票，资金调拨流出；卖出股票，资金调拨流入
     */
    private String tradeFlagStr;

    /**
     * 交易方式字符串格式，1买入，2卖出
     */
    private String tradeTypeStr;

	public BondTrade() {

	}


	public String getBondTradeId() {
		return bondTradeId;
	}

	public void setBondTradeId(String bondTradeId) {
		this.bondTradeId = bondTradeId;
	}

	public String getBondTradeNo() {
		return bondTradeNo;
	}

	public void setBondTradeNo(String bondTradeNo) {
		this.bondTradeNo = bondTradeNo;
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

	public void setfundNo(String fundNo) {
		this.fundNo = fundNo;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getBondId() {
		return bondId;
	}

	public void setBondId(String bondId) {
		this.bondId = bondId;
	}

	public String getBondCode() {
		return bondCode;
	}

	public void setBondCode(String bondCode) {
		this.bondCode = bondCode;
	}

	public String getBondName() {
		return bondName;
	}

	public void setBondName(String bondName) {
		this.bondName = bondName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
		if(tradeType != 0){
			this.tradeTypeStr = tradeType==1?"买入":"卖出";
		}
	}

	public int getTradeFlag() {
		return tradeFlag;
	}

	public void setTradeFlag(int tradeFlag) {
		this.tradeFlag = tradeFlag;
		if(tradeFlag != 0){
			this.tradeFlagStr = tradeFlag==1?"流入":"流出";
		}
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
		if(null != tradeDate){
			this.tradeDateStr = DateUtils.dataToString(tradeDate,"yyyy-MM-dd HH:mm:ss");
		}
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

	public BigDecimal getStampTax() {
		return stampTax;
	}

	public void setStampTax(BigDecimal stampTax) {
		this.stampTax = stampTax;
	}

	public BigDecimal getManagementFees() {
		return managementFees;
	}

	public void setManagementFees(BigDecimal managementFees) {
		this.managementFees = managementFees;
	}

	public BigDecimal getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(BigDecimal transferFee) {
		this.transferFee = transferFee;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(BigDecimal brokerage) {
		this.brokerage = brokerage;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(BigDecimal couponRate) {
		this.couponRate = couponRate;
	}

	public int getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(int tradeStatus) {
		this.tradeStatus = tradeStatus;
		if(tradeStatus == 0 || tradeStatus == 1){
			this.tradeStatusStr = tradeStatus==1?"已结算":"未结算";
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTradeDateStr() {
		return tradeDateStr;
	}

	public String getTradeTypeStr() {
		return tradeTypeStr;
	}

	public String getTradeFlagStr() {
		return tradeFlagStr;
	}

	public String getTradeStatusStr() {
		return tradeStatusStr;
	}

}
