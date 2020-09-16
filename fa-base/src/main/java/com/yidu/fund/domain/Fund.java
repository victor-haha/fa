package com.yidu.fund.domain;

import com.yidu.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 类的描述:基金实体类
 * @author 李昊林
 * @date 2020-09-03
 */
public class Fund implements Serializable{

	private static final long serialVersionUID =  2403125439650571085L;
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
	 * 所属基金管理公司Id,参考基金管理人表
	 */
	private String fundCompanyId;

	/**
	 * 所属基金管理公司
	 */
	private String comShortName;

	/**
	 * 托管银行
	 */
	private String trusteeBank;

	/**
	 * 基金规模,初始融资额
	 */
	private BigDecimal fundScale;

	/**
	 * 初始净值,初始单位净值一般为1.00
	 */
	private BigDecimal nav;

	/**
	 * 托管费,0.001 代表千分之一  交给托管银行的费用
	 */
	private BigDecimal trusteeFee;

	/**
	 * 管理费,0.001 代表千分之一  交给基金管理公司的费用
	 */
	private BigDecimal managementFee;

	/**
	 * 计费有效天数
	 */
	private int billingDays;

	/**
	 * 基金经理ID,参考用户表主键
	 */
	private String managerId;

	/**
	 * 基金经理名
	 */
	private String managerName;

	/**
	 * 成立时间
	 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date estDate;
	/**
	 * 格式化成立时间
	 */
	private String estDateStr;

	/**
	 * 是否可用
	 */
	private String usable;

	/**
	 * 备注
	 */
	private String description;


	public Fund() {

	}

	public Fund(String fundId,String fundNo,String fundName,String fundCompanyId,String comShortName,String trusteeBank,BigDecimal fundScale,BigDecimal nav,BigDecimal trusteeFee,BigDecimal managementFee,int billingDays,String managerId,String managerName,Date estDate,String usable,String description){
		this.fundId = fundId;
		this.fundNo = fundNo;
		this.fundName = fundName;
		this.fundCompanyId = fundCompanyId;
		this.comShortName = comShortName;
		this.trusteeBank = trusteeBank;
		this.fundScale = fundScale;
		this.nav = nav;
		this.trusteeFee = trusteeFee;
		this.managementFee = managementFee;
		this.billingDays = billingDays;
		this.managerId = managerId;
		this.managerName = managerName;
		this.estDate = estDate;
		this.usable = usable;
		this.description = description;
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

	public String getFundCompanyId() {
		return fundCompanyId;
	}

	public void setFundCompanyId(String fundCompanyId) {
		this.fundCompanyId = fundCompanyId;
	}

	public String getComShortName() {
		return comShortName;
	}

	public void setComShortName(String comShortName) {
		this.comShortName = comShortName;
	}

	public String getTrusteeBank() {
		return trusteeBank;
	}

	public void setTrusteeBank(String trusteeBank) {
		this.trusteeBank = trusteeBank;
	}

	public BigDecimal getFundScale() {
		return fundScale;
	}

	public void setFundScale(BigDecimal fundScale) {
		this.fundScale = fundScale;
	}

	public BigDecimal getNav() {
		return nav;
	}

	public void setNav(BigDecimal nav) {
		this.nav = nav;
	}

	public BigDecimal getTrusteeFee() {
		return trusteeFee;
	}

	public void setTrusteeFee(BigDecimal trusteeFee) {
		this.trusteeFee = trusteeFee;
	}

	public BigDecimal getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}

	public int getBillingDays() {
		return billingDays;
	}

	public void setBillingDays(int billingDays) {
		this.billingDays = billingDays;
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

	public Date getEstDate() {
		return estDate;
	}

	public void setEstDate(Date estDate) {
		this.estDate = estDate;
		if(null != estDate){
			this.estDateStr = DateUtils.dataToString(estDate,"yyyy-MM-dd");
		}


	}

	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEstDateStr() {
		return estDateStr;
	}

	@Override
	public String toString() {
		return "Fund{" +
				"fundId='" + fundId + '\'' +
				",fundNo='" + fundNo + '\'' +
				",fundName='" + fundName + '\'' +
				",fundCompanyId='" + fundCompanyId + '\'' +
				",comShortName='" + comShortName + '\'' +
				",trusteeBank='" + trusteeBank + '\'' +
				",fundScale='" + fundScale + '\'' +
				",nav='" + nav + '\'' +
				",trusteeFee='" + trusteeFee + '\'' +
				",managementFee='" + managementFee + '\'' +
				",billingDays='" + billingDays + '\'' +
				",managerId='" + managerId + '\'' +
				",managerName='" + managerName + '\'' +
				",estDate='" + estDate + '\'' +
				",usable='" + usable + '\'' +
				",description='" + description + '\'' +
				'}';
	}

}
