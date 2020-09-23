package com.yidu.index.domain;

import com.yidu.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 证劵库存表
 * POJO_Description: SecuritiesInventory
 * @author wh
 * @since 2020-09-09
 */
public class SecuritiesInventory implements Serializable{

	private static final long serialVersionUID =  2066767366243467631L;
	/**
	 * 证劵库存Id securitiesInventoryId
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
	 * 单位成本,股票|债券|银行理财产品的单份价值
	 */
	private BigDecimal price;

	/**
	 * 持有份额
	 */
	private BigInteger share;

	/**
	 * 总金额  = 单位净值 * 份额
	 */
	private BigDecimal turnover;

	/**
	 * 证劵类型，1股票   2债券   3 银行存款
	 */
	private int securitiesType;
	/**
	 * 证劵类型字符串格式
	 */
	private String securitiesTypeStr;


	/**
	 * 统计日期
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public int getSecuritiesType() {
		return securitiesType;
	}

	public void setSecuritiesType(int securitiesType) {
		this.securitiesType = securitiesType;
		if(securitiesType == 1){
			this.securitiesTypeStr = "股票";
		}else if(securitiesType == 2){
			this.securitiesTypeStr = "债券";
		}else if(securitiesType == 3){
			this.securitiesTypeStr = "银行存款";
		}
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

	public String getStatisticalDateStr() {
		return statisticalDateStr;
	}

	public String getSecuritiesTypeStr() {
		return securitiesTypeStr;
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
				"fundNo='" + fundNo + '\'' +
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
