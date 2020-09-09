package com.xxx.xxx;


import java.io.Serializable;
import java.sql.Date;

/**
 * 证劵库存表
 * POJO_Description: SecuritiesInventory
 * @author wh
 * @since 2020-09-09
 */
public class SecuritiesInventory implements Serializable{

	private static final long serialVersionUID =  2066767366243467631L;
	/**
	 * 证劵库存Id
	 */
	private String securitiesInventoryId;

	/**
	 * 证劵库存编号ZQKC-yyyy-MM-dd-xxxxx 
	 */
	private String sechuritiesInventoryNo;

	/**
	 * 证券Id参考的是 股票|债券|银行存款表的主键
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
	private String fundCode;

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
	 * 单位成本,股票|债券|银行理财产品的单份价值
	 */
	private double price;

	/**
	 * 持有份额
	 */
	private int share;

	/**
	 * 总金额  = 单位净值 * 份额
	 */
	private double turnover;

	/**
	 * 证劵类型，1股票   2债券   3 银行存款
	 */
	private int securitiesType;

	/**
	 * 统计日期
	 */
	private Date statisticalDate;

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

	public SecuritiesInventory() {

	}


	public String getSecuritiesInventoryId() {
		return securitiesInventoryId;
	}

	public void setSecuritiesInventoryId(String securitiesInventoryId) {
		this.securitiesInventoryId = securitiesInventoryId;
	}

	public String getSechuritiesInventoryNo() {
		return sechuritiesInventoryNo;
	}

	public void setSechuritiesInventoryNo(String sechuritiesInventoryNo) {
		this.sechuritiesInventoryNo = sechuritiesInventoryNo;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public int getSecuritiesType() {
		return securitiesType;
	}

	public void setSecuritiesType(int securitiesType) {
		this.securitiesType = securitiesType;
	}

	public Date getStatisticalDate() {
		return statisticalDate;
	}

	public void setStatisticalDate(Date statisticalDate) {
		this.statisticalDate = statisticalDate;
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
		return " SecuritiesInventory{" +
				"securitiesInventoryId='" + securitiesInventoryId + '\'' +
				"sechuritiesInventoryNo='" + sechuritiesInventoryNo + '\'' +
				"securitiesId='" + securitiesId + '\'' +
				"securitiesNo='" + securitiesNo + '\'' +
				"securitiesName='" + securitiesName + '\'' +
				"fundId='" + fundId + '\'' +
				"fundCode='" + fundCode + '\'' +
				"fundName='" + fundName + '\'' +
				"accountId='" + accountId + '\'' +
				"accountNo='" + accountNo + '\'' +
				"accountName='" + accountName + '\'' +
				"price='" + price + '\'' +
				"share='" + share + '\'' +
				"turnover='" + turnover + '\'' +
				"securitiesType='" + securitiesType + '\'' +
				"statisticalDate='" + statisticalDate + '\'' +
				"description='" + description + '\'' +
				"remark1='" + remark1 + '\'' +
				"remark2='" + remark2 + '\'' +
				"remark3='" + remark3 + '\'' +
				'}';
	}

}
