package com.yidu.bond.domain;

import com.yidu.utils.DateUtils;

import java.io.Serializable;
import java.sql.Date;

/**
 * 债券交易数据表
 * POJO_Description: BondTrade
 * @author wh
 * @since 2020-09-06
 */
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
	private String fundCode;

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
	private short tradeType;
	/**
	 * 交易方式字符串格式，1买入，2卖出
	 */
	private String tradeTypeStr;
	/**
	 * 交易标识1.流入、2.流出   买入股票，资金调拨流出；卖出股票，资金调拨流入
	 */
	private short tradeFlag;
	/**
	 * 交易标识字符串格式  1.流入、2.流出   买入股票，资金调拨流出；卖出股票，资金调拨流入
	 */
	private String tradeFlagStr;

	/**
	 * 交易价格(单价),买入或卖出时股票单价
	 */
	private double tradePrice;

	/**
	 * 交易日期
	 */
	private Date tradeDate;
	/**
	 * 交易日期 字符串格式
	 */
	private String tradeDateStr;
	/**
	 * 交易数量(份额)，单位：份
	 */
	private int share;

	/**
	 * 交易额（总），交易额 = 交易价*交易数量（份额）
	 */
	private double turnover;

	/**
	 * 印花费（国家），印花税 = 印花税率*交易额
	 */
	private double stampTax;

	/**
	 * 征管费（国家），征管费 = 征管费率*交易额
	 */
	private double managementFees;

	/**
	 * 过户费（交易所），过户费 = 过户费率*交易额
	 */
	private double transferFee;

	/**
	 * 佣金费用（券商），佣金= 席位费率*交易额
	 */
	private double commission;

	/**
	 * 经手费（交易所），经手费=经手费率*交易额
	 */
	private double brokerage;

	/**
	 * 总金额，交易额+各种税费
	 */
	private double total;

	/**
	 * 债券利息，冗余字段，参考债券表的票面利率couponRate
	 */
	private double couponRate;

	/**
	 * 交易状态 已结算1  未结算 2
	 */
	private short tradeStatus;
	/**
	 * 交易状态字符串格式 已结算1  未结算 0
	 */
	private String tradeStatusStr;

	/**
	 * 备注
	 */
	private String description;

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

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
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

	public void setTradeType(short tradeType) {
		this.tradeType = tradeType;
		if(tradeType != 0){
			this.tradeDateStr = tradeType==1?"买入":"卖出";
		}
	}

	public int getTradeFlag() {
		return tradeFlag;
	}

	public void setTradeFlag(short tradeFlag) {
		this.tradeFlag = tradeFlag;
		if(tradeFlag != 0){
			this.tradeFlagStr = tradeFlag==1?"流入":"流出";
		}
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
		if(null == tradeDate){
			this.tradeDateStr = DateUtils.dataToString(tradeDate,"yyyy-MM-dd HH:mm:ss");
		}
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getStampTax() {
		return stampTax;
	}

	public void setStampTax(double stampTax) {
		this.stampTax = stampTax;
	}

	public double getManagementFees() {
		return managementFees;
	}

	public void setManagementFees(double managementFees) {
		this.managementFees = managementFees;
	}

	public double getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(double transferFee) {
		this.transferFee = transferFee;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(double brokerage) {
		this.brokerage = brokerage;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(double couponRate) {
		this.couponRate = couponRate;
	}

	public int getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(short tradeStatus) {
		this.tradeStatus = tradeStatus;
		if(tradeStatus != 0){
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
